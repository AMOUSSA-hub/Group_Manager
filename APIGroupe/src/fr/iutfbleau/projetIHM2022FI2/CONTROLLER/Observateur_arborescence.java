package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import javax.swing.event.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.DashboardGroupe;


public class Observateur_arborescence implements TreeSelectionListener {

    public static String  group_selected = null;
    public static int id_selected_group;

    public void valueChanged(TreeSelectionEvent e) {

        

        DashboardGroupe.modification_groupe.setEnabled(true);
        DashboardGroupe.suppression_groupe.setEnabled(true);
       group_selected =  e.getPath().toString();

        DashboardGroupe.gestionnaire.show(DashboardGroupe.menu_etudiant,group_selected);
        
        



        

        




    }

    public int determined_id( String p){


        id_selected_group = DashboardGroupe.group_map.get(p);

        

        return id_selected_group;
    }


    
    
}
