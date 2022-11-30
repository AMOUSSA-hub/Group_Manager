package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.MyGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.CreationGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.Creation_Etudiant;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.EditGroupe;


public class Observateur_MEG implements ActionListener {

    private JFrame fen_menu_edit;

   public Observateur_MEG(JFrame fen){
        fen_menu_edit = fen;

    }

    public Observateur_MEG(){}


    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("créer un groupe")){

            System.out.println("demande de création de groupe ");
            new CreationGroupe(fen_menu_edit);


        }

        if(e.getActionCommand().equals("supprimer")){

            System.out.println("demande de suppresion du groupe : " +Observateur_arborescence.group_selected.getName());
            

        }

        if(e.getActionCommand().equals("modifier")){



            new EditGroupe(Observateur_arborescence.group_selected,fen_menu_edit);
           
            

        }

        if(e.getActionCommand().equals("ajouter etudiant")){



            new Creation_Etudiant(fen_menu_edit);
           
            

        }



    }
    
}
