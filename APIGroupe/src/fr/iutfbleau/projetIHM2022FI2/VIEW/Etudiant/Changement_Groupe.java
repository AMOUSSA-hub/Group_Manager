package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;


public class Changement_Groupe extends JDialog {

    public  JDialog fen;
    private String Nom;
    
    public Changement_Groupe(JFrame owner){
        super(owner,true);
        fen = this;
        setSize(500,700);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(7,1));

        JTextField form_id = new JTextField();
        JComboBox<String> form_groupe_depart = new JComboBox<String>();
        JComboBox<String> form_groupe_arrivee = new JComboBox<String>();

        try{
            PreparedStatement req = Utils.con.prepareStatement("Select Nom from Groupe g Join Contient c On g.id = c.idGroupe where IdEtudiant = ? ");
                req.setInt(1,1);
                ResultSet res = req.executeQuery();
                res.next();
                while (res.next()) {
                    Nom = res.getString(1);
                    form_groupe_depart.addItem(Nom);
                }

        } catch (SQLException se) {
                System.err.println("errreur Sql at MyEtudiant()"+se);
        }

        try{
            PreparedStatement req = Utils.con.prepareStatement("Select Unique Nom from Groupe g Join Contient c On g.id = c.idGroupe where IdEtudiant != ? ");
                req.setInt(1,1);
                ResultSet res = req.executeQuery();
                while (res.next()) {
                    Nom = res.getString(1);
                    form_groupe_arrivee.addItem(Nom);
                }

        } catch (SQLException se) {
                System.err.println("errreur Sql at MyEtudiant()"+se);
        }


        JButton valider  = new JButton("Faire la demande");
        /*valider.addActionListener(new ObservateurCreation(form_nom, form_prenom, fen));*/

        add(new JLabel("Identifiant étudiant"),BorderLayout.NORTH);
        add(form_id,BorderLayout.CENTER);

        add(new JLabel("Groupe de départ"),BorderLayout.NORTH);
        add(form_groupe_depart,BorderLayout.CENTER);

        add(new JLabel("Groupe d'arrivée"),BorderLayout.NORTH);
        add(form_groupe_arrivee,BorderLayout.CENTER);

        add(valider, BorderLayout.SOUTH);

        setVisible(true);


    }


}
