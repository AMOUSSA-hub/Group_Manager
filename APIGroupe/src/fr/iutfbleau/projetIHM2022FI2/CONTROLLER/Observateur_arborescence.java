package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import javax.swing.event.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;


public class Observateur_arborescence implements TreeSelectionListener {

    public static MyGroupe  group_selected = null;
    public static int id_group;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {

        

        determined_id(e.getPath().toString());
        System.out.println(group_selected.getName());
        DashboardGroupe.modification_groupe.setEnabled(true);
        DashboardGroupe.suppression_groupe.setEnabled(true);

        if(e.getPath().getLastPathComponent().toString().equals("Promotion")){

            DashboardGroupe.modification_groupe.setEnabled(false);
        DashboardGroupe.suppression_groupe.setEnabled(false);
        }
        
       
       System.out.println(group_selected.getSize());
        DashboardGroupe.gestionnaire.show(DashboardGroupe.menu_etudiant,group_selected.getId()+"");
        
        
        

        


    }

    public int determined_id( String p){


         
       group_selected = DashboardGroupe.bd.brain.get(DashboardGroupe.group_map.get(p));


    

        

        return group_selected.getId();
    }


    
    
}
