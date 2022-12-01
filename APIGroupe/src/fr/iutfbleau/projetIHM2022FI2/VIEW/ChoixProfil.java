package fr.iutfbleau.projetIHM2022FI2.VIEW;

import fr.iutfbleau.projetIHM2022FI2.VIEW.*;
import javax.swing.*;
import java.awt.*;

public class ChoixProfil extends JFrame {
	public ChoixProfil() {
		setLocation(10,10);
        	setSize(new Dimension(250,250));
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panneau = new JPanel();
		JButton etudiant = new JButton("Etudiant");
		JButton prof = new JButton("Professeur");
		JButton admin = new JButton("Admin");

		panneau.add(etudiant);
		panneau.add(prof);
		panneau.add(admin);
		add(panneau);

		setVisible(true);
	}

	public static void main(String[] args) {
		new ChoixProfil();
	}
}
