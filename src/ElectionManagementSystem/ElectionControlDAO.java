package ElectionManagementSystem;

import java.sql.*;

public class ElectionControlDAO {
    private Connection conn;

    // Constructor to open connection
    public ElectionControlDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add this getter method if you want to expose the connection
    public Connection getConnection() {
        return conn;
    }
    
    // Add ElectionControl entry
    public void addElectionControl(ElectionControl control) {
        try {
            String query = "INSERT INTO ElectionControl (status, start_date, start_time, end_date, end_time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setBoolean(1, control.isElectionsStatus());
            pst.setString(2, control.getElectionStartDate());
            pst.setString(3, control.getElectionStartTime());
            pst.setString(4, control.getElectionEndDate());
            pst.setString(5, control.getElectionEndTime());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update ElectionControl (status, start and end time)
public void updateElectionControl(ElectionControl control) {
    try {
        // Check if record exists
        String checkQuery = "SELECT COUNT(*) FROM ElectionControl WHERE id=1";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(checkQuery);
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        st.close();

        if (count == 0) {
            // No record, insert new one
            addElectionControl(control);
        } else {
            // Record exists, update it
            String query = "UPDATE ElectionControl SET status=?, start_date=?, start_time=?, end_date=?, end_time=? WHERE id=1";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setBoolean(1, control.isElectionsStatus());
            pst.setString(2, control.getElectionStartDate());
            pst.setString(3, control.getElectionStartTime());
            pst.setString(4, control.getElectionEndDate());
            pst.setString(5, control.getElectionEndTime());
            pst.executeUpdate();
            pst.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Get ElectionControl settings
    public ElectionControl getElectionControl() {
        ElectionControl control = null;
        try {
            String query = "SELECT * FROM ElectionControl WHERE id=1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                control = new ElectionControl(rs.getBoolean("status"));
                control.setElectionStartDate(rs.getString("start_date"));
                control.setElectionStartTime(rs.getString("start_time"));
                control.setElectionEndDate(rs.getString("end_date"));
                control.setElectionEndTime(rs.getString("end_time"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return control;
    }
    
  public void resetElectionControl() {
    try {
        String query = "UPDATE ElectionControl SET status = ?, start_date = ?, start_time = ?, end_date = ?, end_time = ? WHERE id = 1";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setBoolean(1, false);
        pst.setString(2, "");
        pst.setString(3, "");
        pst.setString(4, "");
        pst.setString(5, "");
        pst.executeUpdate();
        pst.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}