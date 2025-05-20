package ElectionManagementSystem;

import java.util.ArrayList;

public class main {

    // Initialize DAO object for voters
    public static VoterDAO voterDAO = new VoterDAO();
    public static NAConstituencyDAO naConstituencyDAO = new NAConstituencyDAO();
    public static PAConstituencyDAO paConstituencyDAO = new PAConstituencyDAO();
    public static CandidateDAO candidateDAO = new CandidateDAO();
    public static AdministratorDAO administratorDAO = new AdministratorDAO();
    public static ElectionNewsDAO newsDAO = new ElectionNewsDAO();
    public static ElectionControlDAO electionControlDAO = new ElectionControlDAO();
    public static ElectionDAO electionDAO = new ElectionDAO();
    
    
    // Declare ArrayList as a class member variable
    public static ResultTabulationOverall ResultsTabulation;
    public static ElectionControl electionscontrol = electionControlDAO.getElectionControl();
    public static ArrayList<ElectionNews> electionsnews;
    public static ArrayList<Voter> voters;
    public static ArrayList<Candidate> candidates;
    public static ArrayList<Constituency> constituencys;
    private static ArrayList<Administrator> admin;

    public static void main(String[] args) {

        //for results
        ResultsTabulation = new ResultTabulationOverall("Not Started", "Not Started", "Not Ended", "Not Ended");

        // Initialize the ArrayLists inside the main method
        //elections control object
        electionscontrol = new ElectionControl(false);

        //election news arrays of objects
        electionsnews = new ArrayList<>();
        electionsnews = newsDAO.getAllNews();
//        electionsnews.add(new ElectionNews("Dear Voter! cast your vote without any pressure."));

        //Voters Management
//        Voter v = new Voter("Rafay Khan", "2222222222222", "NA-58", "PP-21");
//        voterDAO.addVoter(v);
        voters = new ArrayList<>();
        voters = voterDAO.getAllVoters();

        //initilizing constituencies
        constituencys = new ArrayList<>();
        constituencys = paConstituencyDAO.getAllConstituencies();

        //initilizing candidates
        candidates = new ArrayList<>();
        candidates = candidateDAO.getAllCandidates();

        
        admin = new ArrayList<>();
        admin = administratorDAO.getAllAdmins();

        // Use invokeLater to ensure GUI creation is on the EDT
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    public static Administrator getAdmin() {
    if (admin != null && !admin.isEmpty()) {
        return admin.get(0);
    }
    return null;  // Or throw an exception if you'd rather enforce existence
}
}
