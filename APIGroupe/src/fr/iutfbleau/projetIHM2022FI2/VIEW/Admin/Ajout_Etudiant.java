package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;


import java.util.*;
import java.awt.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;

public class Ajout_Etudiant extends JDialog {

    public Ajout_Etudiant (JFrame owner, MyGroupe group){

        super(owner,true);
        setSize(500,400);
        setLocationRelativeTo(owner);
        JPanel central_pan = new JPanel();
        JPanel footer_pan = new JPanel();

        JButton valider = new JButton("valider");
        JButton annuler = new JButton("annuler");
        
        valider.addMouseListener(new Observateur_ajout_etudiant(valider, this));
       

        Set<Etudiant> promo =   DashboardGroupe.bd.getPromotion().getEtudiants();
        Iterator<Etudiant> iterator = promo.iterator();
        
        Set<Etudiant> etudiant = group.getEtudiants();
        
        while(iterator.hasNext()){

            Etudiant a = iterator.next();
            boolean contain = false;
            for(Etudiant i : etudiant){
               
                if(a.getId() == i.getId()){
                    contain = true;

                    }
                }
                if(!contain ){
                    JButton bout = new JButton(a.getNom()+" "+ a.getPrenom());
                    bout.addMouseListener(new Observateur_ajout_etudiant(a));
                    bout.setBackground(new Color(203, 201, 201));

                    central_pan.add(bout);
                }
            
            
            


        }

        central_pan.setLayout(new GridLayout(3,etudiant.size()/3+1));


        footer_pan.setBackground(new Color(116, 208, 241));
        footer_pan.add(valider);
        footer_pan.add(annuler);

        add(central_pan);
        add(footer_pan,BorderLayout.SOUTH);



        


        


        setVisible(true);

    }





    
}
