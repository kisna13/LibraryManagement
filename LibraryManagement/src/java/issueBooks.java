
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



public class issueBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        PrintWriter out = resp.getWriter();
        
        try {

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                String bookNo = req.getParameter("issueno");
                
                int stock = 0;
                String bookName="";
                int rollNo = 0;
                int count = 0;
                int flag = 1;
                    
                ResultSet rs = st.executeQuery("select * from library.books");

                while (rs.next()) {
                    if(rs.getString(1).equals(bookNo))
                    {
                        bookName = rs.getString(2);
                            
                        if(rs.getInt(3)==0)
                        {
                            
                            flag = 2;
                            break;
                        }
                        
                        flag = 0;

                        stock = rs.getInt(3);

                        break;
                        
                    }
                }   
                if(flag == 1)
                {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry No such Book exists...');");
                    out.println("</script>");

                    RequestDispatcher rd = req.getRequestDispatcher("issueBook.jsp");

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
                            count += 1;
                        }
                    }
                    
                    if(count < 3)
                    {
                        stock -= 1;

                    

                        qr = "update library.books SET stock="+stock+" WHERE serialNo="+bookNo+";";
                        st.executeUpdate(qr);


                        int a = 2;
                        qr = "insert into library.issue values("+rollNo+",\""+name+"\","+bookNo+",\""+bookName+"\");";
                        st.executeUpdate(qr);

                        
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Book issued Sucessfully...');");
                        out.println("</script>");

                        RequestDispatcher rd = req.getRequestDispatcher("student.jsp");

                        rd.include(req, resp);
                    }
                    else
                    {
                        
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('You have reached maxmimum issuing limit of 3 books. Please return any book to issue new book...');");
                        out.println("</script>");
                        
                        RequestDispatcher rd = req.getRequestDispatcher("student.jsp");

                        rd.include(req, resp);
                    }
                    
                    

                }
                else if(flag == 2)
                {
                    out.print("Sorry Book is out of stock please contact the admin...");
                    out.print("\n");

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry Book is out of stock please contact the admin...');");
                    out.println("</script>");
                        
                    RequestDispatcher rd = req.getRequestDispatcher("issueBook.jsp");

                    rd.include(req, resp);
                }

                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            
        }
    }
    
}
