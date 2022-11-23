package fr.iutfbleau.projetIHM2022FI2.API;
import java.sql.*;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;

public class MyEtudiant implements Etudiant {

    private int id;
    private String nom;
    private String prenom;


    public MyEtudiant(int id_etudiant){

        Utils.open_connection();

        try{

        PreparedStatement req = Utils.con.prepareStatement("Select * from Etudiant where Id = ? ");
            req.setString(1,Integer.toString(id_etudiant));
            req.executeUpdate();
            ResultSet res = req.executeQuery();
            res.next();

            id = res.getInt(1);
            prenom = res.getString(2);
            nom = res.getString(3);

            Utils.open_connection();

        } catch (Exception se) {
            System.err.println("errreur Sql"+se);

        }
        





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
