package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import javax.swing.event.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.ViewEtudiant;


public class Observateur_arborescenceEtudiant implements TreeSelectionListener {

    public static MyGroupeEtudiant  group_selected = null;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        
        determined_group(e.getPath().toString());
        ViewEtudiant.gestionnaire.show(ViewEtudiant.menu_etudiant,group_selected.getId()+"");
    }

    public int determined_group( String p){
       group_selected = ViewEtudiant.bd.brain.get(ViewEtudiant.group_map.get(p));
        return group_selected.getId();
    }
}
