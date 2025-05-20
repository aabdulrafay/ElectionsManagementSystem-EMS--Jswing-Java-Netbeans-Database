package ElectionManagementSystem;

import java.sql.*;
import java.util.ArrayList;

public class ElectionDAO {

    private Connection conn;

    // Constructor to open connection
    public ElectionDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertElectionSummary(int registered, int voted, int notVoted,
                                  String startDate, String startTime,
                                  String endDate, String endTime, String turnout) {
    String query = "INSERT INTO election_summary (registered_voters, voted_voters, not_voted_voters, start_date, start_time, end_date, end_time, overall_turnout) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {
        pst.setInt(1, registered);
        pst.setInt(2, voted);
        pst.setInt(3, notVoted);
        pst.setString(4, startDate);
        pst.setString(5, startTime);
        pst.setString(6, endDate);
        pst.setString(7, endTime);
        pst.setString(8, turnout);
        pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}




