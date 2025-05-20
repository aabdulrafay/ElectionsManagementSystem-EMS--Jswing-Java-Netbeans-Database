package ElectionManagementSystem;

public class ElectionControl {

    private boolean ElectionsStatus;
    private String ElectionStartDate;
    private String ElectionStartTime;
    private String ElectionEndDate;
    private String ElectionEndTime;

    // Constructor
    public ElectionControl(boolean electionsStatus) {
        this.ElectionsStatus = electionsStatus;
        this.ElectionStartDate = "";
        this.ElectionStartTime = "";
        this.ElectionEndDate = "";
        this.ElectionEndTime = "";
    }
       
    public boolean isElectionsStatus() {
        return ElectionsStatus;
    }

    public void setElectionsStatus(boolean electionsStatus) {
        this.ElectionsStatus = electionsStatus;
    }

    public String getElectionStartDate() {
        return ElectionStartDate;
    }

    public void setElectionStartDate(String electionStartDate) {
        this.ElectionStartDate = electionStartDate;
    }

    public String getElectionStartTime() {
        return ElectionStartTime;
    }

    public void setElectionStartTime(String electionStartTime) {
        this.ElectionStartTime = electionStartTime;
    }

    public String getElectionEndDate() {
        return ElectionEndDate;
    }

    public void setElectionEndDate(String electionEndDate) {
        this.ElectionEndDate = electionEndDate;
    }

    public String getElectionEndTime() {
        return ElectionEndTime;
    }

    public void setElectionEndTime(String electionEndTime) {
        this.ElectionEndTime = electionEndTime;
    }
}