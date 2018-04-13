package ch.kbw.dao;

import ch.kbw.model.Post;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Adel
 */
@Named
@SessionScoped
public class AgendaDAO implements Serializable {

    @Inject
    private OverallDAO overallDAO;
    private Set<Post> posts = new LinkedHashSet<>();

    public void createPost(String title, Date date, String place, String content) {
        PreparedStatement pst = null;
        String query = "INSERT INTO agenda (title, datum, ort, content)"
                + "VALUES (?, ?, ?, ?)";
        try {
            Post post = new Post();
            post.setTitle(title);
            post.setDate(date);
            post.setPlace(place);
            post.setContent(content);
            posts.add(post);
            pst = overallDAO.getConnection().prepareStatement(query);
            pst.setString(1, post.getTitle());
            pst.setDate(2, post.getDate());
            pst.setString(3, post.getPlace());
            pst.setString(4, post.getContent());
            pst.executeUpdate();
            overallDAO.getLog().info("SUCESSFULLY CREATED NEW POST WITH CONTENT: " + post.getContent());

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Set<Post> listAllPosts() {
        ResultSet rs = null;
        PreparedStatement pst = null;
        String query = "Select * from agenda";
        try {
            pst = overallDAO.getConnection().prepareStatement(query);
            pst.execute();
            rs = pst.getResultSet();
            posts.clear();
            while (rs.next()) {
                Post post = new Post();
                post.setPostID(rs.getInt(1));
                post.setTitle(rs.getString(2));
                java.sql.Date date1 = new java.sql.Date(rs.getDate(3).getTime());
                post.setDate(date1);
                post.setPlace(rs.getString(4));
                post.setContent(rs.getString(5));

                posts.add(post);
            }

            pst.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;

    }

    public void deleteByID(Post post) {
        PreparedStatement pst = null;
        String query = "Delete from agenda where beitragID = ?";

        try {
            pst = overallDAO.getConnection().prepareStatement(query);

            pst.setInt(1, post.getPostID());
            pst.executeUpdate();
            posts.remove(post);

        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void makeChanges(Post post, String newContent, String newTitle) {
        PreparedStatement pst = null;
        String query = "UPDATE agenda "
                + "SET content = ?, title = ? WHERE beitragID = ?";
        try {
            pst = overallDAO.getConnection().prepareStatement(query);
            pst.setString(1, newContent);
            pst.setString(2, newTitle);
            pst.setInt(3, post.getPostID());
            
            post.setContent(newContent);
            post.setTitle(newTitle);
            pst.executeUpdate();
            overallDAO.getLog().info("SUCESSFULLY UPDATED POST WITH A NEW CONTENT: " + post.getContent());
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Set<Post> getPosts() {
        return posts;
    }

}
