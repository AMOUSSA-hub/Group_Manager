package fr.iutfbleau.projetIHM2022FI2.VIEW;

import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;

public class ChoixProfil extends JFrame {
	public static JFrame fenetre = new JFrame("Choix du Profil");
	public static JButton valider = new JButton("Valider");
	public static JComboBox<String> choix = new JComboBox<String>();
	public static JComboBox<String> choixEtudiant = new JComboBox<String>();
	public static JTextField mdp = new JTextField("mot de passe");
	public static JPanel panneau = new JPanel();
	public static JPanel panneau1 = new JPanel();
	public static JButton retour = new JButton("Retour");
 
	public ChoixProfil() {
		ObservateurChoixProfil listener = new ObservateurChoixProfil();
		String nom = new String();
		String prenom = new String();
		int id = 0;
		String complet = new String();

		fenetre.setLocation(700,200);
        fenetre.setSize(new Dimension(300,300));
		fenetre.setLayout(new GridLayout(2,1));
        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		((FlowLayout) panneau.getLayout()).setAlignment(FlowLayout.CENTER);
		((FlowLayout) panneau1.getLayout()).setAlignment(FlowLayout.CENTER);

		choix.addItem("");
		choix.addItem("Etudiant");
		choix.addItem("Professeur");
		choix.addItem("Administrateur");
		panneau1.add(choix);

		Utils.open_connection();
		try {
			PreparedStatement req = Utils.con.prepareStatement("Select * from Etudiant");
            ResultSet res = req.executeQuery();

            while(res.next()){
				id = res.getInt(1);
				prenom = res.getString(2);
				nom = res.getString(3);
				complet = nom + " " + prenom;
				choixEtudiant.addItem(complet);
            }
        } catch (SQLException  se) {
            System.err.println("errreur Sql at ChoixProfil()"+se);
		}
		Utils.close_connection();

		choix.addActionListener(listener);
		valider.addActionListener(listener);
		retour.addActionListener(listener);

		fenetre.add(panneau);
		fenetre.add(panneau1);

		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		ChoixProfil choix = new ChoixProfil();
	}
}
