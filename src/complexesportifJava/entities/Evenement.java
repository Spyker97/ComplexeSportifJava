    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.entities;

import java.sql.Date;

/**
 *
 * @author Nidhal
 */
public class Evenement {
    private int id;
    private int type_id_id;
    private String  titre;
    private Date date_start;
    private Date date_end;
    private int  nombre_par;
    private String description;    
    private String photo;

    public Evenement(int id, int type_id_id, String titre, Date date_start, Date date_end, int nombre_par, String description, String photo) {
        this.id = id;
        this.type_id_id = type_id_id;
        this.titre = titre;
        this.date_start = date_start;
        this.date_end = date_end;
        this.nombre_par = nombre_par;
        this.description = description;
        this.photo = photo;
    }
  public Evenement(  int id, String titre, Date date_start, Date date_end, int nombre_par, String description) {
        
       this.id = id;
        this.titre = titre;
        this.date_start = date_start;
        this.date_end = date_end;
        this.nombre_par = nombre_par;
        this.description = description;
      
    }
    public Evenement( int id, int type_id_id, String titre, Date date_start, Date date_end, int nombre_par, String description) {
        
        this.type_id_id = type_id_id;
        this.titre = titre;
        this.date_start = date_start;
        this.date_end = date_end;
        this.nombre_par = nombre_par;
        this.description = description;
      
    }
        public Evenement( int id, int type_id_id, String titre,  int nombre_par, String description) {
        
        this.type_id_id = type_id_id;
        this.titre = titre;
     
        this.nombre_par = nombre_par;
        this.description = description;
      
    }
    
    
      public Evenement(  int id, String titre,int nombre_par, String description) {
        
    this.id = id;
        this.titre = titre;
     
        this.nombre_par = nombre_par;
        this.description = description;
      
    }

    public Evenement() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id_id() {
        return type_id_id;
    }

    public void setType_id_id(int type_id_id) {
        this.type_id_id = type_id_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getNombre_par() {
        return nombre_par;
    }

    public void setNombre_par(int nombre_par) {
        this.nombre_par = nombre_par;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", type_id_id=" + type_id_id + ", titre=" + titre + ", date_start=" + date_start + ", date_end=" + date_end + ", nombre_par=" + nombre_par + ", description=" + description + ", photo=" + photo + '}';
    }
    
     
  
   
    
    
    
    
    
}
