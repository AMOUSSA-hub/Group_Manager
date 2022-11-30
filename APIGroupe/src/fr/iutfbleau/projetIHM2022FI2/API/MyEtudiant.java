package fr.iutfbleau.projetIHM2022FI2.API;
import java.sql.*;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;

public class MyEtudiant implements Etudiant {

    private int id;
    private String nom;
    private String prenom;


 /**
     * permet de créer un objet représentant un étudiant déjà présent dans la base de données 
     * 
     */
    public MyEtudiant(int id_etudiant){

        Utils.open_connection();

        try{

        PreparedStatement req = Utils.con.prepareStatement("Select * from Etudiant where Id = ? ");
            req.setInt(1,id_etudiant);
            ResultSet res = req.executeQuery();
            res.next();

            id = res.getInt(1);
            prenom = res.getString(2);
            nom = res.getString(3);

            Utils.open_connection();

        } catch (SQLException se) {
            System.err.println("errreur Sql at MyEtudiant()"+se);

        }
        





    }
    /**
     * permet de créer un étudiant et de l'ajouter à la bd 
     * 
     */

    public MyEtudiant(String nom, String prenom){
        
        this.nom = nom;
        this.prenom = prenom;
        Utils.open_connection();
        try{
        PreparedStatement req = Utils.con.prepareStatement("INSERT INTO Etudiant (Etudiant.NomEtudiant, Etudiant.Prenom) VALUES (?,?) ");
        req.setString(1, nom);
        req.setString(2, prenom);
        req.executeUpdate();
        
       

        req =  Utils.con.prepareStatement("select MAX(Etudiant.id) from Etudiant where NomEtudiant = ? AND Prenom = ? ");
        req.setString(1, nom);
        req.setString(2, prenom);
        ResultSet res = req.executeQuery();
        res.next();
        id = res.getInt(1);
        
        

        

   } catch (SQLException  se) {
            System.err.println("errreur Sql at EtudiantNP():"+se);

        }

        Utils.close_connection();
    }

     /**
     * permet de récupérer l'identifiant de l'étudiant.
     * @return l'identifiant.
     */
    public int getId(){
        return id;
    };

    /**
     * permet de récupérer le nom de l'étudiant 
     * @return le nom de l'étudiant.
     */
    public String getNom(){
        return nom;
    };

    /**
     * permet de récupérer
     * @return le prénom de l'étudiant
     */
    public String getPrenom(){
        return prenom;
    };

    /**
     * @see MonPrint
     * NB. On n'utilise le mécanisme des méthodes par défaut pour donner du code dans une interface. C'est un petit peu laid et à contre-emploi mais pratique ici.
     */ 
    public  String monPrint() {
        return String.format("Nom " + getNom() + " Prenom " + getPrenom() + " (id="+getId()+")");
    }
    
}
