
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class returnBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        PrintWriter out = resp.getWriter();
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                String bookNo = req.getParameter("returnno");
                
                int stock = 0;
                String bookName="";
                int rollNo = 0;
                int count = 0;
                int flag = 1;
                boolean issuedThisBook = false;
                    
                ResultSet rs = st.executeQuery("select * from library.books");

                while (rs.next()) {
                    if(rs.getString(1).equals(bookNo))
                    {
                        bookName = rs.getString(2);
                        
                        flag = 0;

                        stock = rs.getInt(3);

                        break;
                        
                    }
                }   
                
                if(flag == 1)
                {
                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry there is no such books issued by library, Please verify the serial no of the book...');");
                    out.println("</script>");

                    RequestDispatcher rd = req.getRequestDispatcher("returnBook.jsp");

                    rd.include(req, resp);
                    
                    
                }
                else if(flag == 0)
                {
                    HttpSession session = req.getSession(false);
        
                    String name = (String) session.getAttribute("username");
                    
                    String qr = "select * from library.student;";                    
                    ResultSet rs1 = st.executeQuery(qr);
                    
                    while(rs1.next())
                    {
                        if(rs1.getString(2).equals(name))
                        {
                            rollNo = rs1.getInt(1);
                        }
                    }
                    
                    
                    ResultSet rs2 = st.executeQuery("select * from library.issue");
                    
                    while(rs2.next())
                    {
                        if(rs2.getInt(1) == rollNo)
                        {
                            if(rs2.getInt(3) == Integer.parseInt(bookNo))
                            {
                                issuedThisBook = true;
                            }
                            count += 1;
                        }
                    }
                    
                    if(count != 0)
                    {
                        if(issuedThisBook == true)
                        {
                            stock += 1;

                            qr = "update library.books SET stock="+stock+" WHERE serialNo="+bookNo+";";
                            st.executeUpdate(qr);



                            qr = "delete from library.issue where id = "+rollNo+" and serialNo = "+bookNo+";";
                            st.executeUpdate(qr);


                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Book returned sucessfully..');");
                            out.println("</script>");

                            RequestDispatcher rd = req.getRequestDispatcher("student.jsp");

                            rd.include(req, resp);
                        }
                        else
                        {
                            
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('You have not issued entered book, Please verify books number and try again...');");
                            out.println("</script>");
                            
                            RequestDispatcher rd = req.getRequestDispatcher("returnBook.jsp");

                            rd.include(req, resp);
                        }
                        
                    }
                    else
                    {
                        
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('You have not issued any books, Please issue books in order to return ...');");
                        out.println("</script>");

                        RequestDispatcher rd = req.getRequestDispatcher("student.jsp");

                        rd.include(req, resp);
                    }
                    
                    

                }
                
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            
        }
    }
    
}
