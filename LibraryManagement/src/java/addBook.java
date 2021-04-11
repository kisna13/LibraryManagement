
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

public class addBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                String name = req.getParameter("bname");
                String no = req.getParameter("no");
                String stock = req.getParameter("stock");
                
                int flag = 1;
                    
                ResultSet rs = st.executeQuery("select * from library.books");

                while (rs.next()) {
                    if(rs.getString(2).equals(name))
                    {
                        flag = 0;
                        break;
                        
                    }
                }   
                
                if(flag == 1)
                {
                    String qr = "insert into library.books values("+no+",\""+name+"\","+stock+")";

                    st.executeUpdate(qr);

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Book Sucessfully Added...');");
                    out.println("</script>");

                    RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");

                    rd.include(req, resp);
                }
                else
                {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry Book already exists...');");
                    out.println("</script>");
                    
                    RequestDispatcher rd = req.getRequestDispatcher("/addBook.jsp");

                    rd.include(req, resp);
                }

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        
    }
    
}
