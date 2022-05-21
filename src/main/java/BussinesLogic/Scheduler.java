package BussinesLogic;
import Model.Server;
import Model.Task;
import java.util.ArrayList;
public class Scheduler {
    private ArrayList<Server> servers;
    private Strategy manevra;
    public Scheduler(int maxSrv, PoliticaSelectie ps)
    {
        this.servers=new ArrayList<>();
        for(int i=1;i<=maxSrv;i++)
        {
            Server queue= new Server(i);
            Thread qThread = new Thread(queue);
            qThread.start();
            this.servers.add(queue);
        }
        selectieManevra(ps);
    }
    public void selectieManevra (PoliticaSelectie ps)
    {
        if(ps== PoliticaSelectie.SHORTEST_QUEUE)
        {
            this.manevra= new ShortestQueueStrategy();
        }
        else if(ps== PoliticaSelectie.SHORTEST_TIME)
            this.manevra= new ShortestTimeStrategy();
    }
    public void addTask(Task t)
    {
        this.manevra.addTask(this.servers,t);
    }
    public String pritare()
    {
        String s="";
        for(Server s2: servers)
        {
            s+=s2.toString();
            s=s+"\n";
        }
        return s;
    }
    public int getTimpAteptare()
    {
        int time=0;
        for(Server s:servers)
        {
            time+=s.getTimpAsteptare();
        }
        return time;
    }
    public double getAvregeTimpAteptare()
    {
        if(this.servers.size()!=0)
        {
            return getTimpAteptare()/servers.size();
        }
        else
            return 0;
    }


}
