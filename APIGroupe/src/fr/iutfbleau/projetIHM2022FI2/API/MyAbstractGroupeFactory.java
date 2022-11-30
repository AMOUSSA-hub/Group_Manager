package fr.iutfbleau.projetIHM2022FI2.API;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;
import java.util.*;
import java.sql.*;

public class MyAbstractGroupeFactory implements AbstractGroupeFactory {

    public HashMap<Integer,MyGroupe> brain = new HashMap<Integer,MyGroupe>();

    public MyAbstractGroupeFactory(){

        Utils.open_connection();

        try{

        PreparedStatement req = Utils.con.prepareStatement("Select id from Groupe ");
            ResultSet res = req.executeQuery();

            while(res.next()){
                brain.put(res.getInt(1), new MyGroupe(res.getInt(1)));
            }
        
        Utils.close_connection();

            } catch (SQLException  se) {
                System.err.println("errreur Sql at MyGroupe()"+se);

            }



    }

    
    public Groupe getPromotion(){
        
        return new MyGroupe(1);


    };



    public Boolean knows(Groupe g){
    
        return true;
    };


    public void deleteGroupe(Groupe g){};


    public void createGroupe(Groupe pere, String name, int min, int max){};

   
    public void createPartition(Groupe pere, String name, int n){};

    
    public void addToGroupe(Groupe g, Etudiant e){};

    
    public void dropFromGroupe(Groupe g, Etudiant e){};

     
    public Set<Etudiant> getEtudiants(String nomEtu){

     Set<Etudiant> student_found = new HashSet<Etudiant>();

     return student_found;

    };

    
    public Set<Groupe> getGroupesOfEtudiant(Etudiant etu){


        return new HashSet<Groupe>();
    };

    
    
}
