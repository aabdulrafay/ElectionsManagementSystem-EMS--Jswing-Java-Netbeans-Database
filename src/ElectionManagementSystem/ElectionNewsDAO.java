package ElectionManagementSystem;

import java.sql.*;
import java.util.ArrayList;

public class ElectionNewsDAO {
   private Connection conn;

    // Constructor to open connection
    public ElectionNewsDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add news
    public boolean addNews(ElectionNews news) {
        try {
            String query = "INSERT INTO ElectionNews (news) VALUES (?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, news.getNews());
                pst.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all news
    public ArrayList<ElectionNews> getAllNews() {
    ArrayList<ElectionNews> newsList = new ArrayList<>();
    try {
        String query = "SELECT * FROM ElectionNews";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                String newsText = rs.getString("news");
                ElectionNews news = new ElectionNews(newsText);
                newsList.add(news);
            }

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return newsList;
}


// Delete news by text
    public boolean deleteNewsByText(String news) {
        String sql = "DELETE FROM ElectionNews WHERE news = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, news);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    // Update news by ID
    public void updateNews(int id, String updatedNews) {
        try {
            String query = "UPDATE ElectionNews SET news = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, updatedNews);
            pst.setInt(2, id);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
