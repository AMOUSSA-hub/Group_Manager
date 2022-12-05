package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import javax.swing.event.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.ViewEtudiant;


public class Observateur_arborescenceEtudiant implements TreeSelectionListener {

    public static MyGroupeEtudiant  group_selected = null;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        
        determined_group(e.getPath().toString());
        System.out.println("nom du groupe: "+group_selected.getName()+group_selected.getId()+"\n"+"nom de son père: "+group_selected.getPointPoint().getName()+group_selected.getPointPoint().getId());
        ViewEtudiant.gestionnaire.show(ViewEtudiant.menu_etudiant,group_selected.getId()+"");
    }

    public int determined_group( String p){
       group_selected = ViewEtudiant.bd.brain.get(ViewEtudiant.group_map.get(p));
        return group_selected.getId();
    }
}
