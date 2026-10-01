package com.gestion_cabinet_medical.couche_presentation;

import java.awt.*;
import javax.swing.*;

public class Aide_pane extends JTabbedPane {
    private JPanel Tutoriel,FAQ,Signaler;

    public Aide_pane(){

        Tutoriel = new JPanel();
        FAQ = new JPanel();
        Signaler = new JPanel();

         this.addTab("Tutoriel", Tutoriel);
        
        this.addTab("FAQ", FAQ);

        this.addTab("Signaler", Signaler);



    }

}
