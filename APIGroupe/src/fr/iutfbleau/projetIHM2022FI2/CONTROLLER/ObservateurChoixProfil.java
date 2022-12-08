package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Prof.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import java.awt.event.*;
import java.sql.*;

public class ObservateurChoixProfil implements ActionListener {

	private MyAbstractGroupeFactory bd;
	
	public ObservateurChoixProfil() {
	}

	public void actionPerformed(ActionEvent clique) {
		if (ChoixProfil.choix.getSelectedItem().equals("Etudiant")) {
			ChoixProfil.panneau1.remove(ChoixProfil.choix);
			ChoixProfil.panneau1.remove(ChoixProfil.valider);
			ChoixProfil.panneau.add(ChoixProfil.retour);
			ChoixProfil.panneau1.add(ChoixProfil.choixEtudiant);
			ChoixProfil.panneau1.add(ChoixProfil.valider);
			ChoixProfil.panneau.revalidate();
			ChoixProfil.panneau1.revalidate();
			ChoixProfil.fenetre.repaint();
		}
		else if (ChoixProfil.choix.getSelectedItem().equals("Administrateur")) {
			ChoixProfil.panneau1.remove(ChoixProfil.choix);
			ChoixProfil.panneau1.remove(ChoixProfil.valider);
			ChoixProfil.panneau.add(ChoixProfil.retour);
			ChoixProfil.panneau1.add(ChoixProfil.mdp);
			ChoixProfil.panneau1.add(ChoixProfil.valider);
			ChoixProfil.panneau.revalidate();
			ChoixProfil.panneau1.revalidate();
			ChoixProfil.fenetre.repaint();
		}
		else if (ChoixProfil.choix.getSelectedItem().equals("Professeur")) {
			ChoixProfil.panneau1.remove(ChoixProfil.choix);
			ChoixProfil.panneau1.remove(ChoixProfil.valider);
			ChoixProfil.panneau.add(ChoixProfil.retour);
			ChoixProfil.panneau1.add(ChoixProfil.valider);
			ChoixProfil.panneau.revalidate();
			ChoixProfil.panneau1.revalidate();
			ChoixProfil.fenetre.repaint();
		}

		if (clique.getActionCommand().equals("Valider")) {
			if (ChoixProfil.choix.getSelectedItem().equals("Etudiant")) {
				ChoixProfil.fenetre.dispose();
				Utils.open_connection();
				String nom = ChoixProfil.choixEtudiant.getSelectedItem().toString();
				int id = 0;
				int id1 = 0;
				String nom1 = new String();
				String prenom1 = new String();
				String complet = new String();

				try {
					PreparedStatement req = Utils.con.prepareStatement("Select * from Etudiant");
					ResultSet res = req.executeQuery();
		
					while(res.next()){
						id1 = res.getInt(1);
						prenom1 = res.getString(2);
						nom1 = res.getString(3);
						complet = nom1 + " " + prenom1;
						if (complet.equals(nom)) {
							id = id1;
						}
					}
				} catch (SQLException  se) {
					System.err.println("errreur Sql at ChoixProfil()"+se);
				}

				new ViewEtudiant(nom, id);
			} 
			else if  (ChoixProfil.choix.getSelectedItem().equals("Administrateur")){
				ChoixProfil.fenetre.dispose();
				Utils.open_connection();
				this.bd = new MyAbstractGroupeFactory();
				new DashboardGroupe(bd);
			}
			else if (ChoixProfil.choix.getSelectedItem().equals("Professeur")) {
				ChoixProfil.fenetre.dispose();
				Utils.open_connection();
				new ViewProf();
			}

		} else if (clique.getActionCommand().equals("Retour")) {
			ChoixProfil.panneau.remove(ChoixProfil.retour);
			if (ChoixProfil.choix.getSelectedItem().equals("Etudiant")) {
				ChoixProfil.panneau1.remove(ChoixProfil.choixEtudiant);
				ChoixProfil.panneau1.remove(ChoixProfil.valider);
				ChoixProfil.panneau1.add(ChoixProfil.choix);
				ChoixProfil.panneau.revalidate();
				ChoixProfil.panneau1.revalidate();
				ChoixProfil.fenetre.repaint();
			}
			else if (ChoixProfil.choix.getSelectedItem().equals("Administrateur")) {
				ChoixProfil.panneau1.remove(ChoixProfil.mdp);
				ChoixProfil.panneau1.remove(ChoixProfil.valider);
				ChoixProfil.panneau1.add(ChoixProfil.choix);
				ChoixProfil.panneau.revalidate();
				ChoixProfil.panneau1.revalidate();
				ChoixProfil.fenetre.repaint();
			}
			else if (ChoixProfil.choix.getSelectedItem().equals("Professeur")) {
				ChoixProfil.panneau1.remove(ChoixProfil.valider);
				ChoixProfil.panneau1.add(ChoixProfil.choix);
				ChoixProfil.panneau.revalidate();
				ChoixProfil.panneau1.revalidate();
				ChoixProfil.fenetre.repaint();
			}
		}
	}
}
