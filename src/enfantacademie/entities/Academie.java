/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfantacademie.entities;

/**
 *
 * @author My Pc
 */
public class Academie {
   private int id;
   private String name ;
   private  int nbr_seance ;

    public Academie() {
    }

    public Academie(int id, String name, int nbr_seance) {
        this.id = id;
        this.name = name;
        this.nbr_seance = nbr_seance;
    }

    public Academie(String name, int nbr_seance) {
        this.name = name;
        this.nbr_seance = nbr_seance;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Academie{" + "id=" + id + ", name=" + name + ", nbr_seance=" + nbr_seance + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbr_seance() {
        return nbr_seance;
    }

    public void setNbr_seance(int nbr_seance) {
        this.nbr_seance = nbr_seance;
    }
   
}
