package ElectionManagementSystem;

import java.util.ArrayList;

public class Voter extends Person {

    private String nationalAssemblyConstituency;
    private String provincialAssemblyConstituency;
    private boolean hasVoted;

    public Voter(String name, String cnic, String nationalAssemblyConstituency, String provincialAssemblyConstituency) {
        super(name, cnic);
        this.nationalAssemblyConstituency = nationalAssemblyConstituency;
        this.provincialAssemblyConstituency = provincialAssemblyConstituency;
        this.hasVoted = false;
    }

    // Getters and Setters
    public String getNationalAssemblyConstituency() {
        return nationalAssemblyConstituency;
    }

    public void setNationalAssemblyConstituency(String nationalAssemblyConstituency) {
        this.nationalAssemblyConstituency = nationalAssemblyConstituency;
    }

    public String getProvincialAssemblyConstituency() {
        return provincialAssemblyConstituency;
    }

    public void setProvincialAssemblyConstituency(String provincialAssemblyConstituency) {
        this.provincialAssemblyConstituency = provincialAssemblyConstituency;
    }

    public boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    // Method to cast vote
    public void castVote(Candidate candidate) {
        if ("false".equals(this.hasVoted)) {
            candidate.incrementVotes();
            this.hasVoted = true;
        }
    }

    // Method to view election news (placeholder)
    public void viewElectionNews(ArrayList<ElectionNews> news) {
        for (ElectionNews election : news) {
            System.out.println(election);
        } 
    }
}
