package ElectionManagementSystem;

public class Candidate extends Person {

    private String party;
    private String constituency; // NA or PA
    private int votes;
    private String Outcome;

    public Candidate(String name, String cnic, String party, String constituency) {
        super(name, cnic);
        this.party = party;
        this.constituency = constituency;
        this.votes = 0;
        this.Outcome = "Pending";
    }

    // Getters and Setters
    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public int getVotes() {
        return votes;
    }

    public void incrementVotes() {
        this.votes++;
    }
    
    public String getOutome() {
        return Outcome;
    }

    public void setOutcome(String outcome) {
        this.Outcome = outcome;
    }
}