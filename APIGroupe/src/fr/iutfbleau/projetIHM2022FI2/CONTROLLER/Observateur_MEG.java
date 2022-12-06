package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;

public class Observateur_MEG implements ActionListener {

    private JFrame fen_menu_edit;
    
    public Observateur_MEG(JFrame fen){
        fen_menu_edit = fen;
    }

    public Observateur_MEG(){
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("créer un sous-groupe libre")){
            System.out.println("demande de création de groupe de Type free ");
            new CreationGroupe(fen_menu_edit);
        }

        if(e.getActionCommand().equals("supprimer le groupe")){
            int reply = JOptionPane.showConfirmDialog(DashboardGroupe.menu_fen, "Voulez vous vraiment supprimer le sous groupe "+Observateur_arborescence.group_selected.getPath(),"supprimer étudiant", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(DashboardGroupe.menu_fen, "Suppresion");
                DashboardGroupe.bd.deleteGroupe(Observateur_arborescence.group_selected);
                DashboardGroupe.loadPanGroup();
                }
        }

        if(e.getActionCommand().equals("modifier")){
            new EditGroupe(Observateur_arborescence.group_selected,fen_menu_edit);
<<<<<<< HEAD
            System.out.println(Observateur_arborescence.group_selected.getType());
           
            

=======
>>>>>>> 418bb6bc621c4ec94a7fd2de3dbea96c4c4f2199
        }

        if(e.getActionCommand().equals("créer un etudiant")){
            new Creation_Etudiant(fen_menu_edit);
        }

        if(e.getActionCommand().equals("Changer de groupe")){
            new Changement_Groupe(fen_menu_edit);
        }
    }
    
}
