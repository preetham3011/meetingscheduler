import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.println("Welcome to the Meeting Scheduler");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Registration process
                    userDAO.register(scanner);
                    break;
                case 2:
                    // Login process
                    String username = userDAO.login(scanner);
                    if (username != null) {
                        showHomePage(scanner, username);
                    }
                    break;
                case 3:
                    // Exit the application
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showHomePage(Scanner scanner, String username) {
        MeetingDAO meetingDAO = new MeetingDAO();

        while (true) {
            System.out.println("\nWelcome, " + username + "!");
            System.out.println("1. Schedule a New Meeting");
            System.out.println("2. My Meetings");
            System.out.println("3. Upcoming Meetings");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Schedule a new meeting
                    System.out.print("Enter meeting title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter meeting description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter meeting start time (yyyy-MM-dd HH:mm:ss): ");
                    Timestamp startTime = Timestamp.valueOf(scanner.nextLine());
                    System.out.print("Enter meeting end time (yyyy-MM-dd HH:mm:ss): ");
                    Timestamp endTime = Timestamp.valueOf(scanner.nextLine());
                    System.out.print("Enter meeting location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter participants (comma-separated): ");
                    String[] participants = scanner.nextLine().split("\\s*,\\s*");

                    boolean success = meetingDAO.addMeeting(username, title, description, startTime, endTime, location, participants);
                    if (success) {
                        System.out.println("Meeting scheduled successfully!");
                    } else {
                        System.out.println("Failed to schedule meeting.");
                    }
                    break;
                case 2:
                    // View past meetings
                    List<Meeting> pastMeetings = meetingDAO.getPastMeetings(username);
                    System.out.println("Past Meetings:");
                    for (Meeting meeting : pastMeetings) {
                        System.out.println("Title: " + meeting.getTitle() + ", Date: " + meeting.getStartTime());
                    }
                    break;
                case 3:
                    // View upcoming meetings
                    List<Meeting> upcomingMeetings = meetingDAO.getUpcomingMeetings(username);
                    System.out.println("Upcoming Meetings:");
                    for (Meeting meeting : upcomingMeetings) {
                        System.out.println("Title: " + meeting.getTitle() + ", Date: " + meeting.getStartTime());
                    }
                    break;
                case 4:
                    // Logout
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
