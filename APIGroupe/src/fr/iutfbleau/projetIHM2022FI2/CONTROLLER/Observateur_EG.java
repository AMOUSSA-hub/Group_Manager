package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.*;
import java.lang.*;

import fr.iutfbleau.projetIHM2022FI2.API.MyGroupe;
import fr.iutfbleau.projetIHM2022FI2.UTILS.Chargement;
import fr.iutfbleau.projetIHM2022FI2.VIEW.DashboardGroupe;

public class Observateur_EG implements ActionListener {

    private MyGroupe group_selected;
    private JTextField form_new_name;
    private JDialog fen_edit;

    public Observateur_EG(MyGroupe group, JTextField name, JDialog fen ){
        this.group_selected = group;
        this.form_new_name = name;
        this.fen_edit = fen;

        

    }

    public void actionPerformed(ActionEvent e){

       String new_name = form_new_name.getText();

            
        
            if (new_name.replaceAll("\\s", "").length() != 0 && new_name.equals("nouveau nom") == false && !new_name.equals(group_selected.getName())){



                this.fen_edit.dispose();

                
               

            this.group_selected.editName(new_name);

            
           DashboardGroupe.loadPanGroup();
         
            

            }

            

            



        


    }
    
}
