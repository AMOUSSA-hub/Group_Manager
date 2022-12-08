package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import javax.swing.event.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Prof.ViewProf;


public class Observateur_arborescenceProf implements TreeSelectionListener {

    public static MyGroupeProf  group_selected = null;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {
    determined_group(e.getPath().toString());
    ViewProf.gestionnaire.show(ViewProf.menu_etudiant,group_selected.getId()+"");
    }

    public int determined_group( String p){
       group_selected = ViewProf.bd.brain.get(ViewProf.group_map.get(p));
        return group_selected.getId();
    }
}
