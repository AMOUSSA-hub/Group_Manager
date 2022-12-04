package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import javax.swing.*;


import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;

public class Observateur_EG implements ActionListener {

    private MyGroupe group_selected;
    private JTextField form_new_name;
    private JDialog fen_edit;

    public Observateur_EG(MyGroupe group, JTextField name, JDialog edit_fen ){
        this.group_selected = group;
        this.form_new_name = name;
        this.fen_edit = edit_fen;

        

    }

    public Observateur_EG (JDialog edit_fen,MyGroupe group ){

        this.fen_edit = edit_fen;
        this.group_selected = group;

    }

    @Override

    public void actionPerformed(ActionEvent e){

       

            
        if(e.getActionCommand().equals("renommer")){

            String new_name = form_new_name.getText();


            
                    if (new_name.replaceAll("\\s", "").length() != 0 && new_name.equals("nouveau nom") == false && !new_name.equals(group_selected.getName())){
                        System.out.println("renommer");
                        this.fen_edit.dispose();   
                        boolean result = this.group_selected.editName(new_name);

                        if(result == true){
                        DashboardGroupe.loadPanGroup();
                        }
                        else{
                            System.out.println("erreur groupe du même nom");
                            
                            JOptionPane.showMessageDialog(new JDialog(), "impossible de mettre ce nom: groupe du même nom déjà existant");
                        }

                    }
                
        }

        if(e.getActionCommand().equals("ajouter  des étudiants")){


            System.out.println("ajouter un élève");

            this.fen_edit.dispose();

            new Ajout_Etudiant(DashboardGroupe.menu_fen,this.group_selected);



        }

        if(e.getActionCommand().equals("Partitioner")){

            this.fen_edit.dispose();
            new Creation_Partition(DashboardGroupe.menu_fen);



        }




            

            



        


    }
    
}
