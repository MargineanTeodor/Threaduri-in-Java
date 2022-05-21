package GUI;
import java.awt.*;
import javax.swing.*;

import static java.lang.Integer.parseInt;

public class GUI extends JPanel {
    private JLabel nrClienti;
    private JTextField clienti;
    private JTextField cozi2;
    private JLabel cozi;
    private JLabel timp;
    private JTextArea Afisare;
    private JTextField timp2;
    private JLabel minajuns;
    private JTextField minajuns2;
    private JTextField maxAjuns2;
    private JLabel maxAjuns;
    private JLabel MinProcesat;
    private JTextField minprocesat2;
    private JLabel MaxProcesat;
    private JTextField maxprocesat2;
    private JTextField strategy;
    private JLabel Strategie;
    private JButton start;

    public int getClienti() {
        return parseInt(clienti.getText());
    }

    public int getCozi2() {
        return parseInt(cozi2.getText());
    }

    public int getTimp2() {
        return parseInt(timp2.getText());
    }

    public int getMinajuns2() {
        return parseInt(minajuns2.getText());
    }

    public int getMaxAjuns2() {
        return parseInt(maxAjuns2.getText());
    }

    public int getMinprocesat2() {
        return parseInt(minprocesat2.getText());
    }

    public int getMaxprocesat2() {
        return parseInt(maxprocesat2.getText());
    }

    public String getStrategy() {
        return strategy.getText();
    }

    public void setAfisare(String s) {
        Afisare.setText(s);
    }

    public GUI() {
        nrClienti = new JLabel ("nrClienti:");
        clienti = new JTextField (1);
        cozi2 = new JTextField (1);
        cozi = new JLabel ("cozi:");
        timp = new JLabel ("timp:");
        Afisare = new JTextArea (1, 1);
        timp2 = new JTextField (1);
        minajuns = new JLabel ("MinAjuns:");
        minajuns2 = new JTextField (0);
        maxAjuns2 = new JTextField (1);
        maxAjuns = new JLabel ("MaxAjuns:");
        MinProcesat = new JLabel ("Minprocesat");
        minprocesat2 = new JTextField (1);
        MaxProcesat = new JLabel ("maxProcesat:");
        maxprocesat2 = new JTextField (0);
        strategy = new JTextField (1);
        Strategie = new JLabel ("Strategie:");
        start = new JButton("Start");
        setPreferredSize (new Dimension (500, 700));
        setLayout (null);
        add (nrClienti);
        add (clienti);
        add (cozi2);
        add (cozi);
        add (timp);
        add (Afisare);
        add (timp2);
        add (minajuns);
        add (minajuns2);
        add (maxAjuns2);
        add (maxAjuns);
        add (MinProcesat);
        add (minprocesat2);
        add (MaxProcesat);
        add (maxprocesat2);
        add (strategy);
        add (Strategie);
        add(start);
        ControllerStart x = new ControllerStart(this);
        start.addActionListener(x);
        nrClienti.setBounds (30, 5, 100, 25);
        clienti.setBounds (90, 5, 100, 25);
        cozi2.setBounds (90, 40, 100, 25);
        cozi.setBounds (30, 40, 100, 25);
        timp.setBounds (30, 80, 100, 25);
        Afisare.setBounds (25, 405, 425, 250);
        timp2.setBounds (90, 80, 100, 25);
        minajuns.setBounds (30, 115, 100, 25);
        minajuns2.setBounds (90, 115, 100, 25);
        maxAjuns2.setBounds (90, 150, 100, 25);
        maxAjuns.setBounds (30, 150, 100, 25);
        MinProcesat.setBounds (15, 185, 100, 25);
        minprocesat2.setBounds (90, 185, 100, 25);
        MaxProcesat.setBounds (5, 220, 100, 25);
        maxprocesat2.setBounds (90, 220, 100, 25);
        strategy.setBounds (90, 260, 100, 25);
        Strategie.setBounds (15, 260, 100, 25);
        start.setBounds(15,300,100,25);
    }
    public void main () {
        JFrame frame = new JFrame ("Tema 2.1:");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GUI());
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible (true);
    }
}