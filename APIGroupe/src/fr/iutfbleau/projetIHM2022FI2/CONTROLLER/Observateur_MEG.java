package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.MyGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.CreationGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.EditGroupe;


public class Observateur_MEG implements ActionListener {

    private JFrame fen_menu_edit;

   public Observateur_MEG(JFrame fen){
        fen_menu_edit = fen;

    }

    public Observateur_MEG(){}


    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("créer")){

            System.out.println("demande de création de groupe ");
            new CreationGroupe(fen_menu_edit);


        }

        if(e.getActionCommand().equals("supprimer")){

            System.out.println("demande de suppresion du groupe : " +new MyGroupe(Observateur_arborescence.id_selected_group).getName());
            

        }

        if(e.getActionCommand().equals("modifier")){



            new EditGroupe(Observateur_arborescence.group_selected,fen_menu_edit);
            System.out.println(Observateur_arborescence.group_selected);
            

        }


    }
    
}
