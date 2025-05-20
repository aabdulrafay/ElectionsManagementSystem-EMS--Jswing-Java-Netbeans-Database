package ElectionManagementSystem;

public class Administrator extends Person{
    private String id;
    private String password;
    
    public Administrator (String id, String password) {
        super("Admin", "NILL");
        this.id = id;
        this.password = password;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}