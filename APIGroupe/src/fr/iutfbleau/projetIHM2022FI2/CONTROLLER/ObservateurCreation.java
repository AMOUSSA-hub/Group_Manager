package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.*;

public class ObservateurCreation implements ActionListener {

    private JTextField nom_groupe;
    private JSpinner min_mem;
    private JSpinner max_mem;
    private JTextField form_nom;
    private JTextField form_prenom;
    private JDialog fen ;

    public ObservateurCreation(JTextField nom_g,JSpinner min,JSpinner max){

        nom_groupe = nom_g;
        min_mem = min;
        max_mem = max;
        



    }

    public ObservateurCreation(JTextField nom , JTextField prenom,JDialog fen ){
        this.form_nom = nom;
        this.form_prenom = prenom;
        this.fen = fen;
    }


    public void actionPerformed(ActionEvent e){

       
        
            if(e.getActionCommand().equals("créer le groupe")  ){
                if(nom_groupe.getText().replaceAll("\\s", "").length() != 0){

                    

                    System.out.println("nom du groupe :"+nom_groupe.getText().replaceAll("\\s", "") +"\n nombre min de membre :" + ((Number)min_mem.getValue()).intValue() + "\n nombre max de membre: " +((Number)max_mem.getValue()).intValue());
                    
                  CreationGroupe.fen_crea.dispose();  
                
                
                }
            }

            if(e.getActionCommand().equals("ajouter l'etudiant")  ){

                    if (form_prenom.getText().replaceAll("\\s", "").length() != 0 && form_nom.getText().replaceAll("\\s", "").length() != 0){


                    fen.dispose();
                   
                    DashboardGroupe.bd.brain.get(1).addEtudiant( new MyEtudiant(form_nom.getText(),form_prenom.getText()));
                    DashboardGroupe.loadPanGroup();
                    }else{
                        System.out.println("champ vide");
                    }
            }




        


    }
    


}
