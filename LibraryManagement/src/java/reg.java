
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

public class reg extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                String name = req.getParameter("name");
                String pass = req.getParameter("psw");
                String conf = req.getParameter("psw-repeat");
                
                int flag = 1;
                int count = 0;
                
                ResultSet rs = st.executeQuery("select * from library.student");

                while (rs.next()) {
                    if(rs.getString(2).equals(name))
                    {
                        flag = 0;                       
                    }
                    count += 1;
                }   
                if(flag == 1)
                {
                    if(pass.equals(conf))
                    {
                        count+=1;
                        
                        String qr = "insert into library.student values("+count+",\""+name+"\","+pass+")";

                        st.executeUpdate(qr);

                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('User Sucessfully registered..');");
                        out.println("</script>");
                        

                        RequestDispatcher rd = req.getRequestDispatcher("index.html");

                        rd.include(req, resp);

                    }
                    else
                    {

                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Password you entered doesnt match. Try Again!!!');");
                        out.println("</script>");
                        
                        RequestDispatcher rd = req.getRequestDispatcher("/registration.html");

                        rd.include(req, resp);
                    }
                    
                }
                else
                {                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry!! Username already exists!!...');");
                    out.println("</script>");

                    RequestDispatcher rd = req.getRequestDispatcher("/registration.html");

                    rd.include(req, resp);
                }

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        
    }
    
}
