package ElectionManagementSystem;

public class ResultTabulationOverall {

    private String StartElectionsDate;
    private String StartElectionsTime;
    private String EndElectionsDate;
    private String EndElectionsTime;
    private int Voted;
    private int NotVoted;
    private float TurnoutOverall;

    // Constructor
    public ResultTabulationOverall(String startElectionsDate, String startElectionsTime, String endElectionsDate, String endElectionsTime) {
        this.StartElectionsDate = startElectionsDate;
        this.StartElectionsTime = startElectionsTime;
        this.EndElectionsDate = endElectionsDate;
        this.EndElectionsTime = endElectionsTime;
        this.Voted = 0;        // Default value
        this.NotVoted = 0;     // Default value
        this.TurnoutOverall = 0.0f; // Default value
    }
    
    
    // Getter and Setter for StartElectionsDate
    public String getStartElectionsDate() {
        return StartElectionsDate;
    }

    public void setStartElectionsDate(String startElectionsDate) {
        StartElectionsDate = startElectionsDate;
    }

    // Getter and Setter for StartElectionsTime
    public String getStartElectionsTime() {
        return StartElectionsTime;
    }

    public void setStartElectionsTime(String startElectionsTime) {
        StartElectionsTime = startElectionsTime;
    }

    // Getter and Setter for EndElectionsDate
    public String getEndElectionsDate() {
        return EndElectionsDate;
    }

    public void setEndElectionsDate(String endElectionsDate) {
        EndElectionsDate = endElectionsDate;
    }

    // Getter and Setter for EndElectionsTime
    public String getEndElectionsTime() {
        return EndElectionsTime;
    }

    public void setEndElectionsTime(String endElectionsTime) {
        EndElectionsTime = endElectionsTime;
    }

    // Getter and Setter for TotalVoters
    public int getTotalVoters() {
        return main.voters.size();
    }


    // Getter and Setter for Voted
    public int getVoted() {
        return Voted;
    }

    public void setVoted(int voted) {
        Voted = voted;
    }

    // Getter and Setter for NotVoted
    public int getNotVoted() {
        return NotVoted;
    }

    public void setNotVoted(int notVoted) {
        NotVoted = notVoted;
    }

    // Getter and Setter for TurnoutOverall
    public float getTurnoutOverall() {
        return TurnoutOverall;
    }

    public void setTurnoutOverall(float turnoutOverall) {
        TurnoutOverall = turnoutOverall;
    }
    
}
