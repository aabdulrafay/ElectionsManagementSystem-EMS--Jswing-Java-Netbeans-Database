package ElectionManagementSystem;

import java.sql.*;
import java.util.ArrayList;

public class NAConstituencyDAO {
    private Connection conn;

    // Constructor to open connection
    public NAConstituencyDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   // Check if NA constituency exists by code
public boolean isNAConstituencyExists(String naCode) {
    String query = "SELECT id FROM NA_Constituencies WHERE na_code = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, naCode);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

// Add new NA constituency
public int addNAConstituency(String naCode) {
    String query = "INSERT INTO NA_Constituencies (na_code, total_voters) VALUES (?, 0)";
    try (PreparedStatement pst = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, naCode);
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}


    // Read (get all)
    public ArrayList<String> getAllNAConstituencies() {
        ArrayList<String> list = new ArrayList<>();
        String query = "SELECT na_code FROM NA_Constituencies";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(rs.getString("na_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
       
    

    // Get ID by code
    public int getNAConstituencyIdByCode(String naCode) {
        String query = "SELECT id FROM NA_Constituencies WHERE na_code = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, naCode);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Update NA code by ID
    public boolean updateNAConstituency(int id, String newCode) {
        String query = "UPDATE NA_Constituencies SET na_code = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, newCode);
            pst.setInt(2, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete by ID
public boolean deleteNAConstituencyByCode(String naCode) {
    String query = "DELETE FROM NA_Constituencies WHERE na_code = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, naCode);
        int rowsAffected = pst.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Increment voter count
    public void incrementVoterCount(int naId) {
        String query = "UPDATE NA_Constituencies SET total_voters = total_voters + 1 WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, naId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getNAIdByCode(String code) {
    int id = -1;
    try {
        Connection con = DBConnection.getConnection();
        String query = "SELECT id FROM NA_Constituencies WHERE na_code = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, code);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return id;
}

}
