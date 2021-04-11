
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class contact extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        String fname = req.getParameter("firstname");
        String lname = req.getParameter("lastname");
        String coun = req.getParameter("country");
        String phone = req.getParameter("phone");
        String message = req.getParameter("subject");
        int count=0;
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                ResultSet rs = st.executeQuery("select * from library.contact");
                
                while(rs.next())
                {
                    count = rs.getInt(1);
                }
                
                count += 1;
                
                String qr = "insert into library.contact values("+count+",\""+fname+"\",\""+lname+"\",\""+phone+"\",\""+coun+"\",\""+message+"\")";

                st.executeUpdate(qr);
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Query Submit. We will contact you soon.');");
                out.println("</script>");
                
                RequestDispatcher rd = req.getRequestDispatcher("home.html");

                rd.include(req, resp);

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
    
    }
}
