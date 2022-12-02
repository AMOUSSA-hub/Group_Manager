package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.MyAbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

public class Observateur_ajout_etudiant implements MouseListener {

private static Set<Etudiant> selection  = new HashSet<Etudiant>();
private Etudiant etudiant_clicked;
private  boolean selected = false;
public JButton valider;
public JDialog fen;


    public Observateur_ajout_etudiant (Etudiant e){

        etudiant_clicked = e ;

    }

    
    public Observateur_ajout_etudiant (JButton val,JDialog fen){

        
        valider = val;
        this.fen = fen;

    }





    @Override

    public void mouseClicked(MouseEvent e){

        if(e.getSource() == valider){

            if(selection.size()!=0){
                //fen.dispose();
                Iterator<Etudiant> iterator = selection.iterator();

                while(iterator.hasNext()){    

                DashboardGroupe.bd.addToGroupe(Observateur_arborescence.group_selected, iterator.next());
            
                }

                DashboardGroupe.loadPanGroup();
        }
        }

        

      }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {

          
       

        if (!selected){

            ((JButton)e.getSource()).setBackground(new Color(116, 208, 241));
            selection.add(etudiant_clicked);
            selected = true;
            System.out.println("+");
        }


        else {

            ((JButton)e.getSource()).setBackground(new Color(203, 201, 201));
            selection.remove(etudiant_clicked);
            selected =false;
            System.out.println("-");
        }

      
    
    
    }

    public void mouseReleased(MouseEvent e) {}
    
}
