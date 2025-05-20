package ElectionManagementSystem;
public class Person {

    private String name;
    private String cnic;

    
     public Person(String name, String cnic) {
        this.name = name;
        this.cnic = cnic;
    }
    
    
    
    //getters and setters
    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
