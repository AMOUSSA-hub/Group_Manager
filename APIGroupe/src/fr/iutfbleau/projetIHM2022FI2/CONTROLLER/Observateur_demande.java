package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

import java.awt.event.*;

import javax.swing.JDialog;

public class Observateur_demande implements ActionListener {

    private Changement c;
    private JDialog fen;

    public Observateur_demande (Changement ch, JDialog f){
        this.c =ch;
        this.fen=f;

    }

        
    
    
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("voir explication")){


        }

        if(e.getActionCommand().equals("accepter la demande")){

            fen.dispose();
            DashboardGroupe.demandes.applyChangement(c);
            DashboardGroupe.refresh_pan_group();


        }


    }

    
}
