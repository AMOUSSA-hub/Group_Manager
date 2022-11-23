package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.VIEW.CreationGroupe;

public class Observateur_CG implements ActionListener {

    private JTextField nom_groupe;
    private JSpinner min_mem;
    private JSpinner max_mem;
    private JRadioButton no_min;
    private JRadioButton no_max;

    public Observateur_CG(JTextField nom_g,JSpinner min,JSpinner max){

        nom_groupe = nom_g;
        min_mem = min;
        max_mem = max;
        



    }


    public void actionPerformed(ActionEvent e){

       
        
            if(e.getActionCommand().equals("créer le groupe")  ){
                if(nom_groupe.getText().replaceAll("\\s", "").length() != 0){

                    

                    System.out.println("nom du groupe :"+nom_groupe.getText().replaceAll("\\s", "") +"\n nombre min de membre :" + ((Number)min_mem.getValue()).intValue() + "\n nombre max de membre: " +((Number)max_mem.getValue()).intValue());
                    
                  CreationGroupe.fen_crea.dispose();  
                
                
                }
            }




        


    }
    


}
