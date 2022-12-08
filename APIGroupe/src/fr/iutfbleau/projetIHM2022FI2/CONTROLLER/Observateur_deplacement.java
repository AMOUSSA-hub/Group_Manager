package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;

public class Observateur_deplacement implements ActionListener {


    public Etudiant etu;
    public Groupe Group_Dep;
    public Groupe Group_Arr;

    public Observateur_deplacement(Etudiant e ,Groupe D, Groupe A){

        this.etu = e;
        this.Group_Dep = D;
        this.Group_Arr = A;


    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("valider")){

            DashboardGroupe.demandes.applyChangement(new MyChangement(Group_Dep,this.etu,Group_Arr) );


            


        }


    }
    
}
