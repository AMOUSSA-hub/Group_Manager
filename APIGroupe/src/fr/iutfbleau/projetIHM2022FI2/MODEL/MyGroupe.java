package fr.iutfbleau.projetIHM2022FI2.MODEL;


import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;

import java.util.*;
import java.sql.*;


public class MyGroupe implements Groupe {

    private int id;
    private String name;
    private int id_father;
    private  MyGroupe father;
    private int min;
    private int max;
    private TypeGroupe type;
    private int size = 0;
    private String path;
    private Set<Etudiant> membre_groupe ;
    private Set<Groupe> sous_groupes;
    



    public MyGroupe(int id_groupe){

        


        try{

                PreparedStatement req = Utils.con.prepareStatement("Select * from Groupe where Id = ? ");
            req.setInt(1,id_groupe);
            ResultSet res = req.executeQuery();
            while (res.next()){
                id = res.getInt(1);
                name = res.getString(2);
                id_father = res.getInt(3)  ;
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
        }

            

            

    
                       
        
                 
            
            
            


            } catch (SQLException  se) {
                System.err.println("errreur Sql at MyGroupe()"+se);

            }

        this.membre_groupe  = new HashSet<>();
        this.sous_groupes = new HashSet<>();

            

   }

   public MyGroupe(Groupe pere, String name, int min, int max){
    Objects.requireNonNull(name,"On ne peut pas créer un groupe dont le nom est null");

    


   }

    
   
   
   
   
   
   
   public boolean addEtudiant(Etudiant e){

        membre_groupe.add(e);
        size++;



    try{

        PreparedStatement req = Utils.con.prepareStatement("Insert into Contient (Contient.idGroupe,Contient.IdEtudiant) VALUES(?,?) ");
            req.setInt(1,id);
            req.setInt(2, e.getId());
            req.executeUpdate();


       } catch (SQLException  se) {
                System.err.println("errreur Sql at addEtudiant():"+se);

            }


        


        return true;
    }

    
    
    public boolean removeEtudiant(Etudiant e){

        membre_groupe.remove(e);
        size--;


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
                    System.err.println("errreur Sql at removeSousGroupe():"+se);
    
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

        if(father == null)
        father = new MyGroupe(id_father);

        return father;
    };

    
    
    public Set<Groupe> getSousGroupes(){

        if(sous_groupes.isEmpty()){
        
    try{
        
       

        PreparedStatement req = Utils.con.prepareStatement("Select Groupe.id,Groupe.Nom from Groupe where Groupe.idPere = ?");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();

            while(res.next()){

                sous_groupes.add(DashboardGroupe.bd.brain.get(res.getInt(1)) );
            }

   
        } catch (SQLException e) {
            System.err.println("errreur Sql at getSousgroupes()"+e);
            
          }


        }


            return sous_groupes;





    }

    public Set<Etudiant> getEtudiants(){

        
    if(membre_groupe.isEmpty()){
        

        try{

            PreparedStatement req = Utils.con.prepareStatement("Select Contient.IdEtudiant from Contient WHERE Contient.idGroupe = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();

            while(res.next()){
                size++;
                membre_groupe.add(new MyEtudiant(res.getInt(1)));
            }

           

        } catch (SQLException se) {
            System.err.println("errreur Sql at getEtudiants()"+se);

        }
    
    
    }

        return membre_groupe;

    }

    public void setPath (String path){

        this.path = path;
    }

    public String getPath(){

        return path;
    }

    public String monPrint() {

        return "name";
    }

    public boolean editName(String new_name){


        

        try{

            PreparedStatement req = Utils.con.prepareStatement("UPDATE Groupe SET Groupe.nom = ? WHERE Groupe.id = ? ");
                req.setString(1, new_name);
                req.setInt(2, id);
              int check =   req.executeUpdate();

              if(check !=0){

                System.out.println("commande traité avec succès");
                this.name = new_name;
                System.out.println("nouveau nom : "+name);
                return true;
              }

              
              
    
            
           } catch (SQLException  se) {
                    System.err.println("errreur Sql at editName():"+se);
    
                }



        return false;

    
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
