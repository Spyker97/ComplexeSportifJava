package gui;

/**
 *
 * @author khalil
 */
public class Cours {
    
    int id ;
    String Coach, Num_salle, type;

    public void setId(int id) {
        this.id = id;
    }

    public void setCoach(String Coach) {
        this.Coach = Coach;
    }

    public void setNum_salle(String Num_salle) {
        this.Num_salle = Num_salle;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getCoach() {
        return Coach;
    }

    public String getNum_salle() {
        return Num_salle;
    }


    public String getType() {
        return type;
    }

    public Cours(int id, String Coach, String Num_salle, String type) {
        this.id = id;
        this.Coach = Coach;
        this.Num_salle = Num_salle;
        this.type = type;
    }
}