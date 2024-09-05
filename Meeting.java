import java.sql.Timestamp;

public class Meeting {
    private int id;
    private String organizer;
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String location;
    private String[] participants;
    private String status;

    public Meeting(int id, String organizer, String title, String description, Timestamp startTime,
                   Timestamp endTime, String location, String[] participants, String status) {
        this.id = id;
        this.organizer = organizer;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.participants = participants;
        this.status = status;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
    public Timestamp getEndTime() { return endTime; }
    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String[] getParticipants() { return participants; }
    public void setParticipants(String[] participants) { this.participants = participants; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
