package ElectionManagementSystem;

import java.sql.*;
import java.util.ArrayList;

public class AdministratorDAO {

    private Connection conn;

    // Constructor to open connection
    public AdministratorDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add an administrator
    public boolean addAdmin(Administrator admin) {
        String query = "INSERT INTO administrators (id, password) VALUES (?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, admin.getId());
            pst.setString(2, admin.getPassword());
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all administrators
    public ArrayList<Administrator> getAllAdmins() {
        ArrayList<Administrator> admins = new ArrayList<>();
        String query = "SELECT * FROM administrators";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Administrator admin = new Administrator(
                        rs.getString("id"),
                        rs.getString("password")
                );
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public void updateLastLoginTime(String adminId) {
    try {
        String query = "UPDATE Administrator SET last_login = ? WHERE username = ?";
        PreparedStatement pst = conn.prepareStatement(query);

        // Current timestamp
        Timestamp now = new Timestamp(System.currentTimeMillis());

        pst.setTimestamp(1, now);
        pst.setString(2, adminId);
        pst.executeUpdate();
        pst.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    // Get admin by ID
    public Administrator getAdminById(String id) {
        String query = "SELECT * FROM administrators WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Administrator(
                        rs.getString("id"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete administrator
    public boolean deleteAdmin(String id) {
        String query = "DELETE FROM administrators WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, id);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update administrator
    public boolean updateAdmin(Administrator admin) {
        String query = "UPDATE administrators SET password = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, admin.getPassword());
            pst.setString(2, admin.getId());
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login method with login time update
    public boolean login(String username, String password) {
        boolean isValid = false;
        try {
            String query = "SELECT * FROM administrators WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                isValid = true;

                // If valid — update login_time
                String updateQuery = "UPDATE administrators SET login_time = ? WHERE username = ?";
                try (PreparedStatement updatePst = conn.prepareStatement(updateQuery)) {
                    updatePst.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                    updatePst.setString(2, username);
                    updatePst.executeUpdate();
                }
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    
    
}
