package ElectionManagementSystem;

import static ElectionManagementSystem.DBConnection.getConnection;
import java.sql.*;
import java.util.ArrayList;

public class CandidateDAO {

    private Connection conn;

    public CandidateDAO() {
        this.conn = DBConnection.getConnection();
    }

    // INSERT candidate into DB
    public boolean insertCandidate(String name, String cnic, String party, String seatType, Integer naId, Integer paId) {
        String query = "INSERT INTO Candidates (name, cnic, party, seat_type, na_id, pa_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, cnic);
            pst.setString(3, party);
            pst.setString(4, seatType);
            if (naId != null) {
                pst.setInt(5, naId);
            } else {
                pst.setNull(5, Types.INTEGER);
            }
            if (paId != null) {
                pst.setInt(6, paId);
            } else {
                pst.setNull(6, Types.INTEGER);
            }

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ all candidates with constituency codes
    public ArrayList<Candidate> getAllCandidates() {
        ArrayList<Candidate> list = new ArrayList<>();
        String query = """
            SELECT c.*, na.na_code, pa.pa_code
            FROM Candidates c
            LEFT JOIN NA_Constituencies na ON c.na_id = na.id
            LEFT JOIN PA_Constituencies pa ON c.pa_id = pa.id
        """;
        try (PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String cnic = rs.getString("cnic");
                String party = rs.getString("party");
                String seatType = rs.getString("seat_type");
                String code = seatType.equals("NA") ? rs.getString("na_code") : rs.getString("pa_code");

                Candidate c = new Candidate(name, cnic, party, code);
                c.setOutcome(rs.getString("outcome"));
                int votes = rs.getInt("votes");
                for (int i = 0; i < votes; i++) {
                    c.incrementVotes();
                }
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE candidate details by CNIC
 public boolean updateCandidate(String name, String cnic, String party, String seatType, int constituencyId) {
    boolean isUpdated = false;

    String query = "UPDATE Candidates SET name = ?, party = ?, seat_type = ?, na_id = ?, pa_id = ? WHERE cnic = ?";

    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, name);
        pst.setString(2, party);
        pst.setString(3, seatType);

        if (seatType.equals("NA")) {
            pst.setInt(4, constituencyId);
            pst.setNull(5, Types.INTEGER);
        } else if (seatType.equals("PA")) {
            pst.setNull(4, Types.INTEGER);
            pst.setInt(5, constituencyId);
        }

        pst.setString(6, cnic);

        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            isUpdated = true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isUpdated;
}


    // DELETE candidate by CNIC
    public boolean deleteCandidateByCNIC(String cnic) {
        boolean isDeleted = false;
        try {
            String query = "DELETE FROM Candidates WHERE cnic = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, cnic);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    isDeleted = true;
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    // GET NA constituency ID by code
    public Integer getNAIdByCode(String code) {
        String query = "SELECT id FROM NA_Constituencies WHERE na_code = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, code);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public int getConstituencyIdByCode(String code, String seatType) {
    int id = -1;
    String query = "";

    if (seatType.equals("PA")) {
        query = "SELECT id FROM PA_Constituencies WHERE pa_code = ?";
    } else if (seatType.equals("NA")) {
        query = "SELECT id FROM NA_Constituencies WHERE na_code = ?";
    }

    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, code);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return id;
}


public void incrementVoteCountByName(String candidateName) {
    try {
        String query = "UPDATE Candidates SET votes = votes + 1 WHERE name = ?";
        PreparedStatement pst = getConnection().prepareStatement(query);
        pst.setString(1, candidateName);
        pst.executeUpdate();
        pst.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void insertConstituencyResult(String constituency, String candidateName, String party,
                                     int votes, int totalVoters, String outcome) {
    String query = "INSERT INTO constituency_results (constituency, candidate_name, party, votes, total_voters, outcome) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, constituency);
        pst.setString(2, candidateName);
        pst.setString(3, party);
        pst.setInt(4, votes);
        pst.setInt(5, totalVoters);
        pst.setString(6, outcome);
        pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // GET PA constituency ID by code
    public Integer getPAIdByCode(String code) {
        String query = "SELECT id FROM PA_Constituencies WHERE pa_code = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, code);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
