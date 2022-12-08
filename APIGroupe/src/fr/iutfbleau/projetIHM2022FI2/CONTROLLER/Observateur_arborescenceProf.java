package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import javax.swing.event.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Prof.ViewProf;


/**
 * Permet de récupérer des Informations sur l'arborescence des groupes: quelle groupe est sélectionné.
 */
public class Observateur_arborescenceProf implements TreeSelectionListener {

    public static MyGroupeProf  group_selected = null;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {
    determined_group(e.getPath().toString());
    ViewProf.gestionnaire.show(ViewProf.menu_etudiant,group_selected.getId()+"");
    }
/**
     * permet de déterminer l'id d'un groupe grâce à son chemin en Utilisant la Map : group_map.
     * @param p chemin de la fonction
     * @return 
     */
    public int determined_group( String p){
       group_selected = ViewProf.bd.brain.get(ViewProf.group_map.get(p));
        return group_selected.getId();
    }
}
