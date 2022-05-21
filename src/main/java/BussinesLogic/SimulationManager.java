package BussinesLogic;

import GUI.GUI;
import Model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SimulationManager implements Runnable {
    private PoliticaSelectie ps;
    private int durata;
    private int minTimpVenire;
    private int maxTimpVenire;
    private int minTimpProcesare;
    private int maxTimpProcesare;
    private int nrCozi;
    private int nrClienti;
    private Scheduler scheduler;
    private ArrayList<Task> list = new ArrayList<>();
    private int peek;
    private double averageTimpAsteptare;
    private double averageTimpPrelucrare;
    private File file;
    private GUI GUI;
    public SimulationManager()
    {
        this.GUI= new GUI();
        this.GUI.main();
    }
    public SimulationManager(GUI gui,PoliticaSelectie ps, int durata, int minTimpVenire, int maxTimpVenire, int minTimpProcesare, int maxTimpProcesare, int nrCozi, int nrClienti) {
        this.GUI=gui;
        this.ps = ps;
        this.durata = durata;
        this.minTimpVenire = minTimpVenire;
        this.maxTimpVenire = maxTimpVenire;
        this.minTimpProcesare = minTimpProcesare;
        this.maxTimpProcesare = maxTimpProcesare;
        this.nrCozi = nrCozi;
        this.nrClienti = nrClienti;
        this.scheduler= new Scheduler(nrCozi ,ps);
        this.averageTimpPrelucrare=AverageTimpProcesare();
        this.list=creation();
    }
    public int randomInt(int max,int min)
    {
        Random rand = new Random();
        return rand.nextInt(max-min)+min;
    }
    public ArrayList<Task> creation()
    {
        ArrayList<Task> list= new ArrayList<>();
        for(int i=1;i<= this.nrClienti;i++)
        {
            System.out.println(i);
            Task t= new Task(i, randomInt(this.maxTimpVenire,this.minTimpVenire),randomInt(this.maxTimpProcesare,this.minTimpProcesare));
            System.out.println(t.toString());
            list.add(t);
        }
        Collections.sort(list,new SortTask());
        return list;
    }
    public static class SortTask implements Comparator<Task>
    {
        @Override
        public int compare(Task cl1, Task cl2)
        {
            return cl1.getAjuns()-cl2.getAjuns();
        }
    }
    public double AverageTimpProcesare()
    {
        double time=0;
        for(Task cl: this.list)
        {
            time+=cl.getProcesat();
        }
        return time/ this.list.size();
    }
    public String Update(int timp, String lista, String cozi) throws IOException {
        String s="";
        FileWriter fw= new FileWriter("Log.txt",true);
        s+="Secunda: " +timp+ "\n"+"Clienti: " +lista+ "\n"+cozi;
        fw.append("Secunda: " +timp+ "\n" );
        fw.append("Clienti: " +lista+ "\n");
        fw.append(cozi);
        fw.close();
        return s;
    }
    public String Print(int peak, double x , double y) throws IOException {
        FileWriter fw= new FileWriter("Log.txt",true);
        String s="peak ora: "+ peak+"\n"+"average asteptare: "+x+"\n"+"average procesare: "+y+"\n";
        fw.append("peak ora: "+ peak+"\n");
        fw.append("average asteptare: "+x+"\n");
        fw.append("average procesare: "+y+"\n");
        return s;
    }
    public String print2()
    {
        String s="";
        for(Task t:this.list)
        {
            s+=t.toString()+" ";
        }
        return s;
    }
    @Override
    public void run() {
        int timp=0;
        String s;
        int WaitMax=0;
        this.averageTimpPrelucrare= this.AverageTimpProcesare();
        while(timp<this.durata)
        {
            ArrayList<Task> tasks= new ArrayList<>();
            for(Task e:this.list)
            {
                if(e.getAjuns()==timp)
                {
                    scheduler.addTask(e);
                    tasks.add(e);
                }
            }
            this.list.removeAll(tasks);
            try {
                s=Update(timp,print2(),scheduler.pritare());
                this.GUI.setAfisare(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            timp++;
            this.averageTimpAsteptare+=scheduler.getAvregeTimpAteptare();
            if(scheduler.getTimpAteptare()>WaitMax)
            {
                this.peek=timp;
                WaitMax=scheduler.getTimpAteptare();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("secunda: "+timp);
        }
        this.averageTimpAsteptare=this.averageTimpAsteptare/this.durata;
        try {
            Print(this.peek,this.averageTimpAsteptare,this.averageTimpPrelucrare);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.GUI.setAfisare("GATA! S-A INCHIS PRAVALIA"+"\n"+Print(this.peek,this.averageTimpAsteptare,this.averageTimpPrelucrare));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
