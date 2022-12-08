package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.Observateur_arborescence;

import java.awt.BorderLayout;

import javax.swing.*;
import java.util.*;


public class Choix_deplacement extends JDialog {

    public Choix_deplacement(JFrame owner,Etudiant e){

        super(owner,true);
        setSize(300,100);
        setLocationRelativeTo(owner);
        JPanel center_pan = new JPanel();

        JComboBox<String> choix_group = new JComboBox<String>();

        
        Set<Groupe> list_group=   Observateur_arborescence.group_selected.getPointPoint().getSousGroupes();

        for(Groupe g : list_group ){

            if(!g.equals(Observateur_arborescence.group_selected)){
                choix_group.addItem(g.getName());
                

            }

        }

        
        JButton val = new JButton("valider");
        
        center_pan.add(new JLabel("Déplacer vers:"));
        center_pan.add(choix_group,BorderLayout.CENTER);
        add(center_pan);
        add(new JPanel().add(val),BorderLayout.SOUTH);

        setVisible(true);



    }



    
}
