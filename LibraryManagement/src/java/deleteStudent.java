

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


public class deleteStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                String no = req.getParameter("rno");
                
                int flag = 1;
                    
                ResultSet rs = st.executeQuery("select * from library.student");

                while (rs.next()) {
                    if(rs.getString(1).equals(no))
                    {
                        flag = 0;
                        break;
                        
                    }
                }   
                if(flag == 1)
                {
               
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry No such Student exists...');");
                    out.println("</script>");
                    
                    RequestDispatcher rd = req.getRequestDispatcher("deleteStudent.jsp");

                    rd.include(req, resp);
                    
                    
                }
                else if(flag == 0)
                {
                    String qr = "Delete from library.student WHERE id="+no+";";

                    st.executeUpdate(qr);
                
                    qr = "Delete from library.issue WHERE id="+no+";";

                    st.executeUpdate(qr);

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Student Removed from library...');");
                    out.println("</script>");
                    
                    RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");

                    rd.include(req, resp);

                }

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            
        }
    }
    
}
