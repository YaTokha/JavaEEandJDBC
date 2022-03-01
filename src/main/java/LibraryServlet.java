import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/library-servlet")

public class LibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "qwertyuiop123");
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT first_name from student");

            while(resultSet.next()){
                resp.getWriter().println(resultSet.getString("first_name"));
            }
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
