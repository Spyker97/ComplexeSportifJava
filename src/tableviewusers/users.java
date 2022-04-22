package tableviewusers;

/**
 *
 * @author amir
 */
public class users {
    
    int id ;
    String username, Number, email , type;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return Number;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public users(int id, String username, String Number, String email, String type) {
        this.id = id;
        this.username = username;
        this.Number = Number;
        this.email = email;
        this.type = type;
    }
}