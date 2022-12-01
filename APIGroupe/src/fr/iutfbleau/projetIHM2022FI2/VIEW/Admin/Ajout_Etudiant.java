package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;


import java.awt.*;
import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;

public class Ajout_Etudiant extends JDialog {

    public Ajout_Etudiant (JFrame owner, MyGroupe group){

        super(owner,true);
        setSize(300,400);
        setLocationRelativeTo(owner);

        Set<Etudiant> promo =   DashboardGroupe.bd.brain.get(1).getEtudiants();
        Iterator<Etudiant> iterator = promo.iterator();
        
        Set<Etudiant> etudiant = group.getEtudiants();

        while(iterator.hasNext()){

            Etudiant a = iterator.next();

            if(!etudiant.contains(a)){

            add(new JLabel(a.getNom()+ a.getPrenom()));
            
            }


        }



        


        


        setVisible(true);

    }





    
}
