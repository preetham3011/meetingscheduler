import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAO {

    // Method to add a new meeting
    public boolean addMeeting(String organizer, String title, String description,
                              Timestamp startTime, Timestamp endTime,
                              String location, String[] participants) {
        String sql = "INSERT INTO meetings (organizer, title, description, start_time, end_time, location, participants) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, organizer);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setTimestamp(4, startTime);
            pstmt.setTimestamp(5, endTime);
            pstmt.setString(6, location);
            pstmt.setArray(7, conn.createArrayOf("TEXT", participants));

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("An error occurred while scheduling the meeting: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve past meetings
    public List<Meeting> getPastMeetings(String username) {
        List<Meeting> meetings = new ArrayList<>();
        String sql = "SELECT * FROM meetings WHERE organizer = ? AND end_time < NOW()";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                meetings.add(new Meeting(
                        rs.getInt("id"),
                        rs.getString("organizer"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("location"),
                        (String[]) rs.getArray("participants").getArray(),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving past meetings: " + e.getMessage());
        }

        return meetings;
    }

    // Method to retrieve upcoming meetings
    public List<Meeting> getUpcomingMeetings(String username) {
        List<Meeting> meetings = new ArrayList<>();
        String sql = "SELECT * FROM meetings WHERE organizer = ? AND start_time >= NOW()";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                meetings.add(new Meeting(
                        rs.getInt("id"),
                        rs.getString("organizer"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("location"),
                        (String[]) rs.getArray("participants").getArray(),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving upcoming meetings: " + e.getMessage());
        }

        return meetings;
    }
}
