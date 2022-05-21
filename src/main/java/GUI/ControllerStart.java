package GUI;

import BussinesLogic.PoliticaSelectie;
import BussinesLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BussinesLogic.PoliticaSelectie.SHORTEST_QUEUE;
import static BussinesLogic.PoliticaSelectie.SHORTEST_TIME;

public class ControllerStart implements ActionListener {
    private GUI GUI;

    public ControllerStart(GUI gui) {
        this.GUI=gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PoliticaSelectie ps;
        if(this.GUI.getStrategy().equals("SHORTEST_QUEUE"))
           ps= SHORTEST_QUEUE;
        else
            ps= SHORTEST_TIME;
        int maxClienti= this.GUI.getClienti();
        int maxCozi= this.GUI.getCozi2();
        int timp= this.GUI.getTimp2();
        int minTimpAjuns= this.GUI.getMinajuns2();
        int maxTimpAjuns= this.GUI.getMaxAjuns2();
        int minTimpPrelucrare= this.GUI.getMinprocesat2();
        int maxTimpPrelucrare= this.GUI.getMaxprocesat2();
        Thread t1= new Thread(new SimulationManager(this.GUI,ps,timp,minTimpAjuns,maxTimpAjuns,minTimpPrelucrare,maxTimpPrelucrare,maxCozi,maxClienti));
        t1.start();
    }
}
