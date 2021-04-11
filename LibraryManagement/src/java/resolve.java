

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
import javax.servlet.http.HttpSession;


public class resolve extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                
                HttpSession session= req.getSession(false);  
                int no =  (int) session.getAttribute("id");  
                
                 
                    String qr = "Delete from library.contact WHERE id="+no+";";

                    st.executeUpdate(qr);

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Query Resolved...');");
                    out.println("</script>");

                    
                    RequestDispatcher rd = req.getRequestDispatcher("inbox.jsp");

                    rd.include(req, resp);

                
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            
        }
    }
    
}
