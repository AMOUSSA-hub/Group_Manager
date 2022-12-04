package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import javax.swing.event.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;


public class Observateur_arborescence implements TreeSelectionListener {

    public static MyGroupe  group_selected = null;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {

        

    determined_group(e.getPath().toString());
        System.out.println(group_selected.getName());
        System.out.println(group_selected.getId());
        if(group_selected != null){
                
            if(e.getPath().getLastPathComponent().toString().equals("Promotion")){

                DashboardGroupe.modification_groupe.setEnabled(false);
            
            }
             else{   
                DashboardGroupe.modification_groupe.setEnabled(true);
                if(group_selected.getSousGroupes().isEmpty()){
                DashboardGroupe.suppression_groupe.setEnabled(true);
                }
                else{
                    DashboardGroupe.suppression_groupe.setEnabled(false);
                }
                
                
            }
            DashboardGroupe.creation_groupe.setEnabled(true);
                
        }
        
       
       System.out.println(group_selected.getSize());
        DashboardGroupe.gestionnaire.show(DashboardGroupe.menu_etudiant,group_selected.getId()+"");
        DashboardGroupe.diapo_info_groupe.show(DashboardGroupe.info_group,group_selected.getId()+"");
        
        
        

        


    }

    public int determined_group( String p){


         
       group_selected = DashboardGroupe.bd.brain.get(DashboardGroupe.group_map.get(p));


    

        

        return group_selected.getId();
    }


    
    
}
