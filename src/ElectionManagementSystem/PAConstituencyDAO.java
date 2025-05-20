package ElectionManagementSystem;

import java.sql.*;
import java.util.ArrayList;

public class PAConstituencyDAO {
    private Connection conn;

    public PAConstituencyDAO() {
        conn = DBConnection.getConnection();
    }

    
    // Check if PA constituency exists by code
public boolean isPAConstituencyExists(String paCode) {
    String query = "SELECT id FROM PA_Constituencies WHERE pa_code = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, paCode);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

// Add new PA constituency
public void addPAConstituency(String paCode, int naId) {
    String query = "INSERT INTO PA_Constituencies (pa_code, na_id, total_voters) VALUES (?, ?, 0)";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, paCode);
        pst.setInt(2, naId);
        pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public ArrayList<Constituency> getAllConstituencies() {
        ArrayList<Constituency> constituencies = new ArrayList<>();

        String query = "SELECT pa.pa_code, na.na_code, pa.total_voters AS totalVotersPA, na.total_voters AS totalVotersNA " +
                       "FROM PA_Constituencies pa " +
                       "JOIN NA_Constituencies na ON pa.na_id = na.id";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String paCode = rs.getString("pa_code");
                String naCode = rs.getString("na_code");
                int totalVotersPA = rs.getInt("totalVotersPA");
                int totalVotersNA = rs.getInt("totalVotersNA");

                Constituency constituency = new Constituency(naCode, paCode);
                // set voter counts
                for (int i = 0; i < totalVotersPA; i++) constituency.addVoterPA();
                for (int i = 0; i < totalVotersNA; i++) constituency.addVoterNA();

                constituencies.add(constituency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return constituencies;
    }

    // Get ID by code
    public int getPAConstituencyIdByCode(String paCode) {
        String query = "SELECT id FROM PA_Constituencies WHERE pa_code = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, paCode);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Update PA code by ID
public boolean updatePAConstituency(String oldPACode, String newPACode, int newNAId) {
    String query = "UPDATE PA_Constituencies SET pa_code = ?, na_id = ? WHERE pa_code = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, newPACode);
        pst.setInt(2, newNAId);
        pst.setString(3, oldPACode);
        int rowsAffected = pst.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}




    // In PAConstituencyDAO.java
public int countPAConstituenciesByNAId(int naId) {
    String query = "SELECT COUNT(*) AS count FROM PA_Constituencies WHERE na_id = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setInt(1, naId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("count");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
    
   
// In PAConstituencyDAO.java
public boolean deletePAConstituencyByCode(String paCode) {
    String query = "DELETE FROM PA_Constituencies WHERE pa_code = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, paCode);
        int rowsAffected = pst.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Increment voter count
    public void incrementVoterCount(int paId) {
        String query = "UPDATE PA_Constituencies SET total_voters = total_voters + 1 WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, paId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
