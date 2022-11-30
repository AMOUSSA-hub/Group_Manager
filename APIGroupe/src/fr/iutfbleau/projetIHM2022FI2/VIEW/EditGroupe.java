package fr.iutfbleau.projetIHM2022FI2.VIEW;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;

import java.awt.*;


public class EditGroupe extends JDialog {

    public EditGroupe( MyGroupe groupe, JFrame owner){
        super(owner,true);
        setResizable(false);

        setLocationRelativeTo(owner);
        setBounds(200, 200,400, 150);
     
       
        JPanel header = new JPanel();
        JPanel middle = new JPanel();
        JPanel footer =  new JPanel();
        
        JTextField new_name = new JTextField("nouveau nom");
        new_name.setPreferredSize(new Dimension(100,20));
        
        JButton rename  = new JButton("renommer");
        rename.addActionListener(new Observateur_EG(groupe, new_name,this));
        JButton ajout_eleve = new JButton("ajouter  un eleve");
        JButton ajout_sous_groupe = new JButton("créer un sous groupe");
        
        header.add(new JLabel(groupe.getName()),BorderLayout.CENTER);
        middle.add(new_name);
        middle.add(rename);
        footer.add(ajout_eleve, BorderLayout.WEST);
        footer.add(ajout_sous_groupe,BorderLayout.EAST);


        add(header, BorderLayout.NORTH);
        add(middle,BorderLayout.CENTER);
        add(footer,BorderLayout.SOUTH);
        

        setVisible(true);

        
    }




    
}
