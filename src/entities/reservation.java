/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class reservation {
    
     private int id;
    private int id_terrain;
    private int cin;
        private String discipline;

    public reservation() {
    }

    public reservation(int id, int id_terrain, int cin, String discipline) {
        this.id = id;
        this.id_terrain = id_terrain;
        this.cin = cin;
        this.discipline = discipline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public reservation(int id) {
        this.id = id;
    }

   
    
}
