package fr.iutfbleau.projetIHM2022FI2.VIEW;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.ObservateurCreation;

import java.awt.*;

public class CreationGroupe extends JDialog {


    public static CreationGroupe fen_crea;

    public CreationGroupe(JFrame fen){
        super(fen,true);
        fen_crea = this;
        setBounds(300, 300,300, 400);
        setLocationRelativeTo(fen);
       
        setLayout(new GridLayout(7,1));
        

        
        JTextField nom_groupe =  new JTextField();
        
        
        JSpinner min_member = new JSpinner(new SpinnerNumberModel(1,1,null,1));
        JSpinner max_member = new JSpinner(new SpinnerNumberModel(1,1,null,1));
        
        

         JButton valider = new JButton("créer le groupe");
         valider.addActionListener(new ObservateurCreation(nom_groupe, min_member, max_member));


        add(new JLabel("Nom du groupe"),BorderLayout.CENTER);
        add(nom_groupe,BorderLayout.CENTER);
        add(new JLabel("Nombre minimum de membre"),BorderLayout.CENTER);
        add(min_member,BorderLayout.CENTER);
        
        add(new JLabel("Nombre maximum de membre"),BorderLayout.CENTER);
        add(max_member,BorderLayout.CENTER);
        add(valider,BorderLayout.SOUTH);









        setVisible(true);



    }

  




    
}
