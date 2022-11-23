package fr.iutfbleau.projetIHM2022FI2.VIEW;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MNP.GroupeNP;
//import fr.iutfbleau.projetIHM2022FI2.UTILS.Icone;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.sql.*;



public class DashboardGroupe extends JFrame {

    public static  JButton modification_groupe = new JButton("modifier");
    public static JButton suppression_groupe = new JButton("supprimer");
    public static JPanel menu_etudiant = new JPanel();
 
    private static DefaultMutableTreeNode root;
    public static Map<String,Integer> group_map = new HashMap<String,Integer>();
    public static CardLayout gestionnaire = new CardLayout();
  
 

    public DashboardGroupe(){

        
        JPanel north_pan = new JPanel();
        JPanel south_pan = new JPanel();
        JButton creation_groupe = new JButton("créer");
        
        setLocation(10,10);
        setSize(Utils.fen_dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        north_pan.setPreferredSize(new Dimension(300,300));
        north_pan.setLayout(new GridLayout(1,2));
        
        menu_etudiant.setLayout(gestionnaire);
             

        
      
        display_group(new DefaultMutableTreeNode( new MyGroupe(1).getName()),1);
         JTree arbre = new JTree(root);
       arbre.setRootVisible(false);
       arbre.addTreeSelectionListener(new Observateur_arborescence());
  
        
        
        north_pan.add(arbre);
        north_pan.add(new JScrollPane(arbre));
        north_pan.add(menu_etudiant);
        

        modification_groupe.setEnabled(false);
        suppression_groupe.setEnabled(false);
        creation_groupe.addActionListener(new Observateur_MEG(this));
        modification_groupe.addActionListener(new Observateur_MEG(this));
        suppression_groupe.addActionListener(new Observateur_MEG());
          
       
        south_pan.setBackground(new Color(116, 208, 241));
        south_pan.add(creation_groupe,BorderLayout.CENTER);
        south_pan.add(modification_groupe,BorderLayout.CENTER);
        south_pan.add(suppression_groupe,BorderLayout.CENTER);

        add(north_pan);
        add(south_pan,BorderLayout.SOUTH);
             
        
        setVisible(true);

    }

    

    
    public static void display_etudiant (MyGroupe groupe_selected,String titre_diapo){

         JPanel diapositive_etudiant = new JPanel(); 
        int nbr_etudiant = 0;

        Set<Etudiant> list_etu =  groupe_selected.getEtudiants();
        Iterator<Etudiant> iterator = list_etu.iterator();

        while(iterator.hasNext()){

            Etudiant a = iterator.next();

            nbr_etudiant++;
                diapositive_etudiant.add(new JLabel(a.getNom()+ " "+  a.getPrenom() ));
            JButton info = new JButton("infos");
            diapositive_etudiant.add(info);
            diapositive_etudiant.add(new JButton("Supprimer"));
        }

              
            diapositive_etudiant.setLayout(new GridLayout(nbr_etudiant,3));
            

        menu_etudiant.add(diapositive_etudiant,titre_diapo);
        
    }

    
    
    
    
    public static void  display_group( DefaultMutableTreeNode node_départ,int id_groupe){


        
        DefaultMutableTreeNode parent = node_départ;
      
        String path = Utils.TreeNode_to_String(parent.getPath());
        group_map.put(path,id_groupe);
        display_etudiant(new MyGroupe(id_groupe),path );
     
      if(id_groupe == 1 ){
         root = parent;
      }

      Utils.open_connection();

      try {
        PreparedStatement req = Utils.con.prepareStatement("Select Groupe.id from Groupe where Groupe.idPere = ?");
            req.setString(1,Integer.toString(id_groupe));
            req.executeUpdate();
            ResultSet res = req.executeQuery();

                   
            while(res.next()){
               

                DefaultMutableTreeNode fils = new DefaultMutableTreeNode(new MyGroupe(res.getInt(1)).getName());
               
                parent.add(fils);
                
                 
                Thread.dumpStack();
               
                 display_group(fils,res.getInt(1));
                
                
            }
        
        
      } catch (Exception e) {
        System.err.println("errreur Sql"+e);
        
      }
     
      
      Utils.close_connection();

    }

    



    public static void main(String[] args) {
        new DashboardGroupe();
    }
    
}
