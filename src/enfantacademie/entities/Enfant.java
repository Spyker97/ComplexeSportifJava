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
public class Enfant {
    private int id ;
    private String name ;
    private int age ;
    private int poids ;
    private int taille ;
    private int cin_parent ;
    private int academie_id ;

    public Enfant() {
    }

    public Enfant(int id, String name, int age, int poids, int taille, int cin_parent, int academie_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.cin_parent = cin_parent;
        this.academie_id = academie_id;
    }

    public Enfant(String name, int age, int poids, int taille, int cin_parent, int academie_id) {
        this.name = name;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.cin_parent = cin_parent;
        this.academie_id = academie_id;
    }
 public Enfant(String name, int age, int poids, int taille, int cin_parent) {
        this.name = name;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
        this.cin_parent = cin_parent;
       
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

    public int getCin_parent() {
        return cin_parent;
    }

    public void setCin_parent(int cin_parent) {
        this.cin_parent = cin_parent;
    }

    public int getAcademie_id() {
        return academie_id;
    }

    public void setAcademie_id(int academie_id) {
        this.academie_id = academie_id;
    }

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", name=" + name + ", age=" + age + ", poids=" + poids + ", taille=" + taille + ", cin_parent=" + cin_parent + ", academie_id=" + academie_id + '}';
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
