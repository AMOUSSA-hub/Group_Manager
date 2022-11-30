package fr.iutfbleau.projetIHM2022FI2.UTILS;

import java.awt.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

public class Chargement extends Thread  {

    public boolean done  = false;
    

    public void run() {

        JDialog fen = new JDialog(DashboardGroupe.menu_fen,true);
        fen.setSize(350, 100);
        fen.setLocationRelativeTo(DashboardGroupe.menu_fen);
        CardLayout gestionnaire = new CardLayout();
        fen.setLayout(gestionnaire);

        fen.add(new JLabel("Mise à jour des groupes"));
        fen.add(new JLabel("Mise à jour des groupes."));
        fen.add(new JLabel("Mise à jour des groupes.."));
        fen.add(new JLabel("Mise à jour des groupes..."));

        fen.setVisible(true);

        
        
        
    } 
    
}
