package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;

public class pan_info_groupe extends JPanel {


    public pan_info_groupe( Groupe groupe ){
     

        setLayout(new GridLayout(3,1));

        add(new JLabel("Nom du groupe:" +groupe.getName()));
        add(new JLabel("minimum de membre : " + groupe.getMin()));
        add(new JLabel("capacité : "+ groupe.getSize()+"/"+groupe.getMax() ));
        

    }
    
}
