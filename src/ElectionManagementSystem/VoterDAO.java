package ElectionManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VoterDAO {

    private Connection conn;

    public VoterDAO() {
        conn = DBConnection.getConnection();
    }

    // Helper: Get NA Constituency ID by name
    private int getNAConstituencyIdByName(String name) throws SQLException {
        String query = "SELECT id FROM NA_Constituencies WHERE name = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("NA Constituency not found: " + name);
            }
        }
    }

    // Helper: Get PA Constituency ID by name
    private int getPAConstituencyIdByName(String name) throws SQLException {
        String query = "SELECT id FROM PA_Constituencies WHERE name = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("PA Constituency not found: " + name);
            }
        }
    }

    // Create
    public boolean addVoter(String name, String cnic, int naId, int paId) {
        String query = "INSERT INTO voter (name, cnic, na_constituency_id, pa_constituency_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, cnic);
            pst.setInt(3, naId);
            pst.setInt(4, paId);

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addVoter(Voter voter) {
        String query = "INSERT INTO voter (name, cnic, na_constituency_id, pa_constituency_id, has_voted) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, voter.getName());
            pst.setString(2, voter.getCnic());
            pst.setInt(3, getNAConstituencyIdByName(voter.getNationalAssemblyConstituency()));
            pst.setInt(4, getPAConstituencyIdByName(voter.getProvincialAssemblyConstituency()));
            pst.setBoolean(5, voter.getHasVoted());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all with constituency names (JOIN)
    public ArrayList<Voter> getAllVoters() {
        ArrayList<Voter> voters = new ArrayList<>();
        String query = "SELECT v.name, v.cnic, na.na_code AS na_constituency, pa.pa_code AS pa_constituency, v.has_voted "
                + "FROM voter v "
                + "JOIN NA_Constituencies na ON v.na_constituency_id = na.id "
                + "JOIN PA_Constituencies pa ON v.pa_constituency_id = pa.id";

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Voter v = new Voter(
                        rs.getString("name"),
                        rs.getString("cnic"),
                        rs.getString("na_constituency"),
                        rs.getString("pa_constituency")
                );
                v.setHasVoted(rs.getBoolean("has_voted"));
                voters.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voters;
    }

    // Read one by CNIC with constituency names (JOIN)
    public Voter getVoterByCnic(String cnic) {
        Voter v = null;
        String query = "SELECT v.name, v.cnic, na.na_code AS na_constituency, pa.pa_code AS pa_constituency, v.has_voted "
                + "FROM voter v "
                + "JOIN NA_Constituencies na ON v.na_constituency_id = na.id "
                + "JOIN PA_Constituencies pa ON v.pa_constituency_id = pa.id "
                + "WHERE v.cnic = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, cnic);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                v = new Voter(
                        rs.getString("name"),
                        rs.getString("cnic"),
                        rs.getString("na_constituency"),
                        rs.getString("pa_constituency")
                );
                v.setHasVoted(rs.getBoolean("has_voted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    public boolean updateVoter(String name, String cnic, int naId, int paId) {
        String query = "UPDATE voter SET name=?, na_constituency_id=?, pa_constituency_id=? WHERE cnic=?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setInt(2, naId);
            pst.setInt(3, paId);
            pst.setString(4, cnic);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteVoter(String cnic) {
        String query = "DELETE FROM voter WHERE cnic=?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, cnic);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateHasVotedStatus(String cnic, boolean hasVoted) {
    try {
        String query = "UPDATE voter SET has_voted = ? WHERE cnic = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setBoolean(1, hasVoted);
            pst.setString(2, cnic);
            pst.executeUpdate();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
