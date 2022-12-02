package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MNP.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ViewEtudiant extends JFrame {

	public static JFrame fenetre = new JFrame("Etudiant");

	public ViewEtudiant() {
		fenetre.setLocation(50, 50);
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);	

		fenetre.setVisible(true);
	}
}
