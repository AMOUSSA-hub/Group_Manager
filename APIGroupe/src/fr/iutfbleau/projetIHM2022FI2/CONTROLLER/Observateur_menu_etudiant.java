package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;



import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

public class Observateur_menu_etudiant implements ActionListener {


    private Etudiant etudiant_selected;

    public Observateur_menu_etudiant (Etudiant etu){

        etudiant_selected = etu;


    }

    @Override
    
    public void actionPerformed(ActionEvent e){


       if (((JButton)e.getSource()).getText().equals("Supprimer")){ 
       

                int reply = JOptionPane.showConfirmDialog(DashboardGroupe.menu_fen, "Voulez vous vraiment supprimer "+etudiant_selected.getNom()+" "+etudiant_selected.getPrenom()+" du groupe "+Observateur_arborescence.group_selected.getName(),"supprimer étudiant", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(DashboardGroupe.menu_fen, "Suppresion");
                DashboardGroupe.bd.dropFromGroupe(Observateur_arborescence.group_selected, etudiant_selected);

                DashboardGroupe.loadPanGroup();
                }
            
                
                System.out.println(e.getActionCommand());

    }



    if (((JButton)e.getSource()).getText().equals("Déplacer")){ 



    }

    }




    
    
}
