package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;



import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.Choix_deplacement;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;
/**
 * gère l'interaction entre L'utilisateur et l'interface dans la vue DashboardGroupe : spécialement menu Etudiant
*/
 
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
                DashboardGroupe.refresh_pan_etudiant();
               
                }
            
                
                System.out.println(e.getActionCommand());

    }



    if (((JButton)e.getSource()).getText().equals("Déplacer")){ 

            new Choix_deplacement(DashboardGroupe.menu_fen, etudiant_selected);



    }

    }




    
    
}
