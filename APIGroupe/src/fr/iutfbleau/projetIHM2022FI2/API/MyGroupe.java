package fr.iutfbleau.projetIHM2022FI2.API;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;
import java.util.*;

import java.sql.*;


public class MyGroupe implements Groupe {

    private int id;
    private String name;
    private int idfather;
    private int min;
    private int max;
    private TypeGroupe type;
    private int size;
    



    public MyGroupe(int id_groupe){

        Utils.open_connection();

        try{

                PreparedStatement req = Utils.con.prepareStatement("Select * from Groupe where Id = ? ");
            req.setString(1,Integer.toString(id_groupe));
            req.executeUpdate();
            ResultSet res = req.executeQuery();
            res.next();
            id = res.getInt(1);
            name = res.getString(2);
            idfather = res.getInt(3);
            min = res.getInt(4);
            max = res.getInt(5);
            
            switch(res.getString(6)){


                case "Tous les étudiants":
                    type = TypeGroupe.ROOT;

                case "partition":
                type = TypeGroupe.PARTITION;

                case "libre":
                type = TypeGroupe.FREE;
            }

            

            size = res.getInt(7);

            Utils.close_connection();


            } catch (Exception se) {
                System.err.println("errreur Sql"+se);

            }

   

    }

    public boolean addEtudiant(Etudiant e){

        return true;
    }

    public boolean removeEtudiant(Etudiant e){
        return true;
    }

    public boolean addSousGroupe(Groupe g){
        return true;
    }


    public boolean removeSousGroupe(Groupe g){

        return true;
    };


    public int getId(){

        return id ;
    };

    public String getName(){

        return name;
    };

    public int getMin(){

        return  min;
    };

    public int getMax(){

        return max; 
    };

    public int getSize(){

        return size;
    };


    public TypeGroupe getType(){

        return type;
    };


    public Groupe getPointPoint(){

        return this;
    };

    public Set<Etudiant> getEtudiants(){

        Set<Etudiant> all_etudiant = new HashSet<>();

        
        Utils.open_connection();

        try{

            PreparedStatement req = Utils.con.prepareStatement("Select Contient.IdEtudiant from Contient WHERE Contient.idGroupe = ? ");
            req.setString(1,Integer.toString(id));
            req.executeUpdate();
            ResultSet res = req.executeQuery();

            while(res.next()){

                all_etudiant.add(new MyEtudiant(res.getInt(1)));
            }

            Utils.close_connection();

        } catch (Exception se) {
            System.err.println("errreur Sql"+se);

        }
            




        return all_etudiant;

    };

    public String monPrint() {

        return "name";
    }

    public static void main(String[] args) {
        MyGroupe g  = new MyGroupe(1);
        Set<Etudiant> list_etu =  g.getEtudiants();
        Iterator<Etudiant> iterator = list_etu.iterator();

        while(iterator.hasNext()){

            Etudiant a = iterator.next();

            System.out.println(a.getNom() +" " + a.getPrenom());
        }

        




    }







    
}
