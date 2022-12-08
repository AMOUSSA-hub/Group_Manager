package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;
import fr.iutfbleau.projetIHM2022FI2.API.*;

import javax.swing.*;



public class explication_demande extends  JDialog {

    public explication_demande(JDialog owner, Changement c){
        super(owner,true);
        setSize(600,400);
        setLocationRelativeTo(owner);

        add(new JLabel(c.getExplication()));

        setVisible(true);


    }
    
}
