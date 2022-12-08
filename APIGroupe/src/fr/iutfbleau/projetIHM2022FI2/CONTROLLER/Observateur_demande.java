package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.Menu_Changement;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.explication_demande;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.Changement_Groupe;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import javax.swing.JDialog;
/**
 * gère l'interaction entre L'utilisateur et l'interface dans la vue Menu_Changement
*/
public class Observateur_demande implements ActionListener {

    private Changement c;
    private JDialog fen;

    public Observateur_demande (Changement ch, JDialog f){
        this.c =ch;
        this.fen=f;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("voir explication")){

            new explication_demande(fen, c);
        }

        if(e.getActionCommand().equals("accepter")){
            fen.dispose();
            DashboardGroupe.demandes.applyChangement(c);
            DashboardGroupe.refresh_pan_group();
        }

        if(e.getActionCommand().equals("refuser")){
            DashboardGroupe.demandes.deleteChangement(c);
            fen.dispose();
            new Menu_Changement(DashboardGroupe.menu_fen);
        }

    }

    
}
