package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;



import javax.swing.*;


public class ViewEtudiant extends JFrame {

	public static JFrame fenetre = new JFrame("Etudiant");

	public ViewEtudiant() {
		fenetre.setLocation(50, 50);
		fenetre.setSize(500,500);
		fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);	

		fenetre.setVisible(true);
	}
}
