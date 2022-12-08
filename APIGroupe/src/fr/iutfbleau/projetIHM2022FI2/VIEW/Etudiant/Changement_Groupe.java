package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import javax.swing.*;
import java.awt.*;
import java.security.interfaces.RSAKey;
import java.util.*;
import java.sql.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;


public class Changement_Groupe extends JDialog {

    public static JDialog fen;
    public static JComboBox<String> form_groupe_depart = new JComboBox<>();
    public static JComboBox<String> form_groupe_arrivee = new JComboBox<>();
    public static JTextField explication = new JTextField();

    private String Nom;
    private int id;
    
    public Changement_Groupe(JFrame owner, int id){
        super(owner,true);
        fen = this;
        this.id = id;
        setSize(500,700);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(7,1));

        try{
            form_groupe_arrivee.removeAllItems();
            form_groupe_depart.removeAllItems();
            explication.setText("");
            PreparedStatement req = Utils.con.prepareStatement("Select Nom from Groupe Order By id Asc");
                ResultSet res = req.executeQuery();
                res.next();
                while (res.next()) {
                    try{
                        PreparedStatement req1 = Utils.con.prepareStatement("Select Nom from Groupe g Join Contient c On g.id = c.idGroupe where IdEtudiant = ? Order By g.id Asc");
                            req1.setInt(1,id);
                            ResultSet res1 = req1.executeQuery();
                            res1.next();
                            while (res1.next()) {
                                if (res1.getString(1).equals(res.getString(1))) {
                                    Nom = res1.getString(1);
                                    form_groupe_depart.addItem(Nom);
                                    res1.first();
                                    break;
                                }
                            }
                            if (res1.next() == false) {
                                Nom = res.getString(1);
                                form_groupe_arrivee.addItem(Nom);
                            }
            
                    } catch (SQLException se) {
                            System.err.println("errreur Sql at MyEtudiant()"+se);
                    }
                }

        } catch (SQLException se) {
                System.err.println("errreur Sql at MyEtudiant()"+se);
        }

        JButton valider  = new JButton("Faire la demande");
        valider.addActionListener(new Observateur_creation_demande(fen, id));
        /*valider.addActionListener(new ObservateurCreation(form_nom, form_prenom, fen));*/

        add(new JLabel("Groupe de départ"),BorderLayout.NORTH);
        add(form_groupe_depart,BorderLayout.CENTER);

        add(new JLabel("Groupe d'arrivée"),BorderLayout.NORTH);
        add(form_groupe_arrivee,BorderLayout.CENTER);

        add(new JLabel("Veuillez entrer ci-dessous une explication à ce changement"), BorderLayout.NORTH);
        add(explication, BorderLayout.CENTER);

        add(valider, BorderLayout.SOUTH);

        setVisible(true);


    }


}
