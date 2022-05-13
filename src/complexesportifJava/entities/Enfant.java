/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.entities;

/**
 *
 * @author My Pc
 */
public class Enfant {
    private int id ;
    private String name ;
    private int age ;
    private int poids ;
    private int taille ;
    private int cinparent ;
    private int academie_id ;

    public Enfant() {
    }

    public Enfant(int id, String name, int age, int poids, int taille, int cinparent, int academie_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.cinparent = cinparent;
        this.academie_id = academie_id;
    }

    public Enfant(String name, int age, int poids, int taille, int cinparent, int academie_id) {
        this.name = name;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.cinparent = cinparent;
        this.academie_id = academie_id;
    }
 public Enfant(String name, int age, int poids, int taille, int cinparent) {
        this.name = name;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.cinparent = cinparent;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getCinparent() {
        return cinparent;
    }

    public void setCinparent(int cinparent) {
        this.cinparent = cinparent;
    }

    public int getAcademie_id() {
        return academie_id;
    }

    public void setAcademie_id(int academie_id) {
        this.academie_id = academie_id;
    }

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", name=" + name + ", age=" + age + ", poids=" + poids + ", taille=" + taille + ", cinparent=" + cinparent + ", academie_id=" + academie_id + '}';
    }
      @Override
    public boolean equals(Object obj) {
        if (obj instanceof Enfant) {
            Enfant j = (Enfant) obj;
            if (j.age == this.age) {

                return true;
            }
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
}
