package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.Menu_Changement;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.explication_demande;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.Changement_Groupe;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import javax.swing.JDialog;

/**
 * met en palce la création d'une demande de changmenent
 */

public class Observateur_creation_demande implements ActionListener {

    private JDialog fen;
    private int id;

    public Observateur_creation_demande (JDialog f, int id) {
        this.id = id;
        this.fen = f;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("Faire la demande")) {
            String pereDepart = Changement_Groupe.form_groupe_depart.getSelectedItem().toString();
            String pereArrivee = Changement_Groupe.form_groupe_arrivee.getSelectedItem().toString();
            Utils.open_connection();
            try {

                PreparedStatement req = Utils.con.prepareStatement("Select Nom from Groupe where id = (Select idPere from Groupe where Nom = ?)");
                req.setString(1, pereDepart);
                ResultSet res = req.executeQuery();

                PreparedStatement req1 = Utils.con.prepareStatement("Select Nom from Groupe where id = (Select idPere from Groupe where Nom = ?)");
                req1.setString(1, pereArrivee);
                ResultSet res1 = req1.executeQuery();

                res.next();
                res1.next();
                if (res.getString(1).equals(res1.getString(1))) {
                    if (Changement_Groupe.explication.getText().isEmpty() == true) {
                        JOptionPane.showMessageDialog(Changement_Groupe.fen, "Veuillez entrer une explication");
                    }
                    else {
                        PreparedStatement req2 = Utils.con.prepareStatement("Insert Into Changement (IdEtudiant, idGroupeDepart, idGroupeArrive, Type, Explications) Values (?, ?, ?, ?, ?)");
                        req2.setInt(1, id);

                        PreparedStatement req3 = Utils.con.prepareStatement("Select id From Groupe where Nom = ?");
                        req3.setString(1, pereDepart);
                        ResultSet res3 = req3.executeQuery();
                        res3.next();
                        req2.setInt(2, res3.getInt(1));

                        PreparedStatement req4 = Utils.con.prepareStatement("Select id From Groupe where Nom = ?");
                        req4.setString(1, pereArrivee);
                        ResultSet res4 = req4.executeQuery();
                        res4.next();
                        req2.setInt(3, res4.getInt(1));

                        PreparedStatement req5 = Utils.con.prepareStatement("Select Count(IdEtudiant) From Contient where idGroupe = (Select id from Groupe where Nom = ?)");
                        req5.setString(1, pereDepart);
                        ResultSet res5 = req5.executeQuery();

                        PreparedStatement req6 = Utils.con.prepareStatement("Select Count(IdEtudiant) From Contient where idGroupe = (Select id from Groupe where Nom = ?)");
                        req6.setString(1, pereArrivee);
                        ResultSet res6 = req6.executeQuery();

                        res5.next();
                        res6.next();
                        if (res5.getInt(1) > res6.getInt(1)) {
                            req2.setInt(4, 1);
                        }
                        else {
                            req2.setInt(4, 2);
                        }
                        req2.setString(5, Changement_Groupe.explication.getText());

                        ResultSet res2 = req2.executeQuery();

                        fen.dispose();
                        JOptionPane.showMessageDialog(Changement_Groupe.fen, "Demande envoyée");
                        
                    }
                }
                else {
                    JOptionPane.showMessageDialog(Changement_Groupe.fen, "Ces deux groupes n'ont pas le même groupe parent");
                }
            } catch (SQLException  se) {
                
            }
        }        
    }
}
