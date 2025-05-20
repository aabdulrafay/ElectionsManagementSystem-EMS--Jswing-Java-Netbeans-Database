package ElectionManagementSystem;

public class Constituency {

    private String NA;
    private String PA;
    private int totalvotersconstituencyPA;
    private int totalvotersconstituencyNA;

    // Parameterized constructor
    public Constituency(String NA, String PA) {
        this.NA = NA;
        this.PA = PA;
        this.totalvotersconstituencyNA = 0;
        this.totalvotersconstituencyPA = 0;
    }

    // Methods to add/remove voters and candidates
    public void addVoterPA() {
        totalvotersconstituencyPA++;
    }

    public void removeVoterPA() {
        totalvotersconstituencyPA--;
    }

    public void addVoterNA() {
        totalvotersconstituencyNA++;
    }

    public void removeVoterNA() {
        totalvotersconstituencyNA--;
    }

    // Getters
    public String getNA() {
        return NA;
    }

    public void setPA(String PA) {
        this.PA = PA;
    }

    // Setters
    public void setNA(String NA) {
        this.NA = NA;
    }

    public String getPA() {
        return PA;
    }

    public int getTotalVotersNA() {
        return totalvotersconstituencyNA;
    }
    
    public int getTotalVotersPA() {
        return totalvotersconstituencyPA;
    }
}
