package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.*;
import java.awt.event.*;

public class ObservateurChoixProfil implements ActionListener {

	private ChoixProfil fenetre;
	
	public ObservateurChoixProfil(ChoixProfil fenetre) {
		this.fenetre = fenetre;
	}

	public void actionPerformed(ActionEvent clique) {
		if (fenetre.choix.getSelectedItem().equals("Etudiant")) {
			ChoixProfil.panneau1.remove(ChoixProfil.choix);
			ChoixProfil.panneau1.remove(ChoixProfil.valider);
			ChoixProfil.panneau.add(ChoixProfil.retour);
			ChoixProfil.panneau1.add(ChoixProfil.choixEtudiant);
			ChoixProfil.panneau1.add(ChoixProfil.valider);
			ChoixProfil.panneau.revalidate();
			ChoixProfil.panneau1.revalidate();
			fenetre.fenetre.repaint();
		}
		else if (fenetre.choix.getSelectedItem().equals("Administrateur")) {
			ChoixProfil.panneau1.remove(ChoixProfil.choix);
			ChoixProfil.panneau1.remove(ChoixProfil.valider);
			ChoixProfil.panneau.add(ChoixProfil.retour);
			ChoixProfil.panneau1.add(ChoixProfil.mdp);
			ChoixProfil.panneau1.add(ChoixProfil.valider);
			ChoixProfil.panneau.revalidate();
			ChoixProfil.panneau1.revalidate();
			fenetre.fenetre.repaint();
		}
		if (clique.getActionCommand().equals("Valider")) {
			if (fenetre.choix.getSelectedItem().equals("Etudiant")) {
				fenetre.fenetre.dispose();
				Utils.open_connection();
				new ViewEtudiant();
			} 
			else if  (fenetre.choix.getSelectedItem().equals("Administrateur")){
				fenetre.fenetre.dispose();
				Utils.open_connection();
				new DashboardGroupe();
			}
		} else if (clique.getActionCommand().equals("Retour")) {
			ChoixProfil.panneau.remove(ChoixProfil.retour);
			if (fenetre.choix.getSelectedItem().equals("Etudiant")) {
				ChoixProfil.panneau1.remove(ChoixProfil.choixEtudiant);
				ChoixProfil.panneau1.remove(ChoixProfil.valider);
				ChoixProfil.panneau1.add(ChoixProfil.choix);
				ChoixProfil.panneau1.add(ChoixProfil.valider);
				ChoixProfil.panneau.revalidate();
				ChoixProfil.panneau1.revalidate();
				fenetre.fenetre.repaint();
			}
			else if (fenetre.choix.getSelectedItem().equals("Administrateur")) {
				ChoixProfil.panneau1.remove(ChoixProfil.mdp);
				ChoixProfil.panneau1.remove(ChoixProfil.valider);
				ChoixProfil.panneau1.add(ChoixProfil.choix);
				ChoixProfil.panneau1.add(ChoixProfil.valider);
				ChoixProfil.panneau.revalidate();
				ChoixProfil.panneau1.revalidate();
				fenetre.fenetre.repaint();
			}
		}
	}
}
