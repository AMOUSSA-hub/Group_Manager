package fr.iutfbleau.projetIHM2022FI2.VIEW;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;

import javax.swing.*;
import java.awt.*;

public class ChoixProfil extends JFrame {
	public JFrame fenetre = new JFrame("Choix du Profil");
	public JComboBox<String> choix = new JComboBox<String>();

	public ChoixProfil() {
		ObservateurChoixProfil listener = new ObservateurChoixProfil(this);

		fenetre.setLocation(700,200);
        	fenetre.setSize(new Dimension(300,300));
		fenetre.setLayout(new GridLayout(2,1));
        	fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panneau = new JPanel();
		((FlowLayout) panneau.getLayout()).setAlignment(FlowLayout.CENTER);
		JPanel panneau1 = new JPanel();
		((FlowLayout) panneau1.getLayout()).setAlignment(FlowLayout.CENTER);

		choix.addItem("Etudiant");
		choix.addItem("Professeur");
		choix.addItem("Administrateur");
		panneau1.add(choix);

		JButton valider = new JButton("Valider");
		valider.addActionListener(listener);
		panneau1.add(valider);

		fenetre.add(panneau);
		fenetre.add(panneau1);

		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		ChoixProfil choix = new ChoixProfil();
	}
}
