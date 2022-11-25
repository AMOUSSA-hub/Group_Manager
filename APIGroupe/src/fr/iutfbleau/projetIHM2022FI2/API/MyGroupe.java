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
    private String path;
    private Set<Etudiant> all_etudiant = new HashSet<>();
    private Set<Groupe> all_sous_groupe = new HashSet<>();
    



    public MyGroupe(int id_groupe){


        Utils.open_connection();

        try{

                PreparedStatement req = Utils.con.prepareStatement("Select * from Groupe where Id = ? ");
            req.setInt(1,id_groupe);
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


             req = Utils.con.prepareStatement("SELECT COUNT(*) FROM Contient WHERE Contient.idGroupe = ?");
                req.setInt(1,id);
                 res = req.executeQuery();
    
                       
                res.next();
                size = res.getInt(1);
                 
            
            
            Utils.close_connection();
            


            } catch (SQLException  se) {
                System.err.println("errreur Sql at MyGroupe()"+se);

            }

            

   }

    
   
   
   
   
   
   
   public boolean addEtudiant(Etudiant e){

        Utils.open_connection();


    try{

        PreparedStatement req = Utils.con.prepareStatement("Insert into Contient (Contient.idGroupe,Contient.IdEtudiant) VALUES(?,?) ");
            req.setInt(1,id);
            req.setInt(2, e.getId());
            req.executeUpdate();


       } catch (SQLException  se) {
                System.err.println("errreur Sql at addEtudiant():"+se);

            }

        Utils.close_connection();



        return true;
    }

    
    
    public boolean removeEtudiant(Etudiant e){

        try{

            PreparedStatement req = Utils.con.prepareStatement("DELETE from Contient Where Contient.idGroupe = ? AND Contient.IdEtudiant = ? ");
                req.setInt(1,id);
                req.setInt(2, e.getId());
                req.executeUpdate();
    
    
           } catch (SQLException  se) {
                    System.err.println("errreur Sql at removeEtudiant():"+se);
    
                }



        
        return true;
    }

    public boolean addSousGroupe(Groupe g){

        try{

            PreparedStatement req = Utils.con.prepareStatement("UPDATE Groupe SET Groupe.idPere = ? WHERE Groupe.id = ? ");
                req.setInt(1,id);
                req.setInt(2, g.getId());
                req.executeUpdate();
    
    
           } catch (SQLException  se) {
                    System.err.println("errreur Sql at addSousGroupe():"+se);
    
                }





        return true;
    }


    public boolean removeSousGroupe(Groupe g){

        try{

            PreparedStatement req = Utils.con.prepareStatement("UPDATE Groupe SET Groupe.idPere = 1 WHERE Groupe.id = ? ");
                req.setInt(1, g.getId());
                req.executeUpdate();
    
    
           } catch (SQLException  se) {
                    System.err.println("errreur Sql at addSousGroupe():"+se);
    
                }




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

        return new MyGroupe(idfather);
    };

    
    
    public Set<Groupe> getSousGroupes(){

        Utils.open_connection();
        
    try{
        
       

        PreparedStatement req = Utils.con.prepareStatement("Select Groupe.id,Groupe.Nom from Groupe where Groupe.idPere = ?");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();

            while(res.next()){

                all_sous_groupe.add(new MyGroupe(res.getInt(1)));
            }

   
        } catch (SQLException e) {
            System.err.println("errreur Sql at getSousgroupe()"+e);
            
          }

          Utils.close_connection();


            return all_sous_groupe;





    }

    public Set<Etudiant> getEtudiants(){

        

        
        Utils.open_connection();

        try{

            PreparedStatement req = Utils.con.prepareStatement("Select Contient.IdEtudiant from Contient WHERE Contient.idGroupe = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();

            while(res.next()){

                all_etudiant.add(new MyEtudiant(res.getInt(1)));
            }

           

        } catch (SQLException se) {
            System.err.println("errreur Sql at getEtudiants()"+se);

        }
        Utils.close_connection();



        return all_etudiant;

    }

    public void setPath (String init_path){

        path = init_path;
    }

    public String getPath(){

        return path;
    }

    public String monPrint() {

        return "name";
    }
/* 
    public static void main(String[] args) {
       MyGroupe g  = new MyGroupe(1);
       Set<Etudiant> list_etu =  g.getEtudiants();
        Iterator<Etudiant> iterator = list_etu.iterator();

        while(iterator.hasNext()){

            Etudiant a = iterator.next();

            System.out.println(a.getNom() +" " + a.getPrenom());
        } 




    }
*/






    
}
