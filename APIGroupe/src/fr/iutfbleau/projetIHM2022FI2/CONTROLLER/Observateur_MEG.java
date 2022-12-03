package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;

public class Observateur_MEG implements ActionListener {

    private JFrame fen_menu_edit;

   public Observateur_MEG(JFrame fen){
        fen_menu_edit = fen;

    }

    public Observateur_MEG(){}


    @Override

    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("créer un sous-groupe libre")){

            System.out.println("demande de création de groupe de Type free ");
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
