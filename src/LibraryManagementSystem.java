import java.sql.*;
import java.util.*;

public class LibraryManagementSystem {
    static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    static final String USER = "root";
    static final String PASS = "password"; // replace with your MySQL password

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static void addBook(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        String query = "INSERT INTO books (title, author) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, title);
        stmt.setString(2, author);
        stmt.executeUpdate();
        System.out.println("Book added successfully!");
    }

    private static void viewBooks(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");

        System.out.println("\nBook List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") + ", Author: " + rs.getString("author"));
        }
    }

    private static void deleteBook(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter book ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        String query = "DELETE FROM books WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        int rows = stmt.executeUpdate();
        if (rows > 0) System.out.println("Book deleted successfully!");
        else System.out.println("Book ID not found.");
    }

    public static void main(String[] args) {
        try (Connection conn = connect(); Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add Book\n2. View Books\n3. Delete Book\n4. Exit");
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1: addBook(conn, sc); break;
                    case 2: viewBooks(conn); break;
                    case 3: deleteBook(conn, sc); break;
                    case 4: System.exit(0);
                    default: System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
