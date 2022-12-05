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


		}
	}
}
