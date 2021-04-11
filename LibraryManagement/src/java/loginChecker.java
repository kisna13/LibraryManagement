
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

public class loginChecker extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
        int choice = Integer.parseInt(req.getParameter("ch")) ;
        
        String username = req.getParameter("name");
        String password = req.getParameter("pass");
        
        PrintWriter out = resp.getWriter();

        
        try {

            Class.forName("com.mysql.jdbc.Driver");            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            
            
            Statement st = con.createStatement();
            System.out.println("Connected");

            switch(choice)
            {
                case 2:
                {
                    int flag = 1;
                    
                    ResultSet rs = st.executeQuery("select * from library.admin");

                    while (rs.next()) {
                        if(rs.getString(1).equals(username))
                        {
                            if(rs.getString(2).equals(password))
                            {

                                HttpSession session = req.getSession();
        
                                session.setAttribute("username", username);
                                
                                RequestDispatcher rd = req.getRequestDispatcher("admin.jsp");
                                rd.forward(req, resp);
                                
                            }

                            flag = 0;
                            
                        }
                    }
                    
                    if(flag == 0)
                    {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Username Password doesnt match.. Please try again..');");
                        out.println("</script>");
                        
                        RequestDispatcher rd = req.getRequestDispatcher("/index.html");

                        rd.include(req, resp);
                    }
                    
                    else if(flag == 1)
                    {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Sorry there is no such user.. Please try again..');");
                        out.println("</script>");
                        
                        RequestDispatcher rd = req.getRequestDispatcher("/index.html");
                           
                        rd.include(req, resp);
                    }
                    break;
                }
                
                case 1:
                {
                    int flag = 1;
                    
                    ResultSet rs = st.executeQuery("select * from library.student");

                    while (rs.next()) {
                        if(rs.getString(2).equals(username))
                        {
                            if(rs.getString(3).equals(password))
                            {
                                
                                HttpSession session = req.getSession();
        
                                session.setAttribute("username", username);
                                
                                RequestDispatcher rd = req.getRequestDispatcher("student.jsp");
                                rd.forward(req, resp);
                                
                            }
            
                            flag = 0;
                        }
                    }
                    
                    
                    
                    if(flag == 0)
                    {

                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Username Password doesnt match.. Please try again..');");
                        out.println("</script>");
                        
                        RequestDispatcher rd = req.getRequestDispatcher("/index.html");

                        rd.include(req, resp);
                    }
                    else if(flag == 1)
                    {

                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Sorry there is no such user.. Please try again..');");
                        out.println("</script>");
                        
                        RequestDispatcher rd = req.getRequestDispatcher("/index.html");
                           
                        rd.include(req, resp);
                    }
                    break;
                }
                                    
            }

            con.close();
          
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

        

}
