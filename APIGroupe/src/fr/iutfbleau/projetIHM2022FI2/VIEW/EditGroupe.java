package fr.iutfbleau.projetIHM2022FI2.VIEW;
import javax.swing.*;
import java.awt.*;


public class EditGroupe extends JDialog {

    public EditGroupe( String name_groupe, JFrame owner){
        super(owner,true);
        setResizable(false);

        setLocationRelativeTo(owner);
        setBounds(200, 200,400, 150);
     
       
        JPanel header = new JPanel();
        JPanel middle = new JPanel();
        JPanel footer =  new JPanel();
        
        JTextField new_name = new JTextField("nouveau nom");
        JButton rename  = new JButton("renommer");
        JButton ajout_eleve = new JButton("ajouter  un eleve");
        JButton ajout_sous_groupe = new JButton("ajouter un sous groupe");
        
        header.add(new JLabel(name_groupe),BorderLayout.CENTER);
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
