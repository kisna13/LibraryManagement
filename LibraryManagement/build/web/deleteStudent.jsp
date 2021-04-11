<%-- 
    Document   : deleteStudent
    Created on : 1-Aug-2020, 3:11:23 PM
    Author     : korin
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 90%;
                margin-left: 50px;
                }

            td, th {
              border: 1px solid #dddddd;
              text-align: center;
              padding: 8px;
            }
            tr:nth-child(even) {
                background-color: #dddddd;
            }
            
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
              }

              li {
                float: left;
              }

              li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 20px;
              }

              li a:hover:not(.active) {
                background-color: #0000CD;
              }

              .active {
                background-color: #4CAF50;
              }

            .button:hover {
              background-color: #FFFF00;
            }
            
        </style>
        
        <title>Delete Student</title>
        
    </head>
    <body style="background-color:#F5F2D0;">
        
        <%
            session = request.getSession(false);
        
            String name = (String) session.getAttribute("username");
            
            if(name==null)
            {
               response.sendRedirect("index.html");
            }
            
        %>
        
        <ul>
            <li class="secli">
                <a class="sec-li-a" >Welcome: <% out.print(name); %> </a>
            </li>
                       
            <li style="float:right"><a href="logout.jsp">Logout</a></li>
            <li style="float:right"><a href="about.jsp">About</a></li>
            <li style="float:right"><a href="contact.html">Contact</a></li>
            <li style="float:right"><a href="admin.jsp">Main Menu</a></li>
            <li style="float:right"><a class="active" href="home.html">Home Page</a></li>
            
            
        </ul>
            
        <h2 style="text-align:center;">Enter the Roll Number of the student to remove that student.</h2>
        
        <form action="deleteStudent" method="post">
            <table>
                
                <tr>
                    <td>
                        ROLL NO:
                    </td>
                    
                    <td style="text-align:center;">
                        <input type="text" name="rno">                  
                    </td>

                    
                    <td style="text-align:center;">
                          <input type="submit" value="Delete">
                    </td>
                    
                    
                </tr>
            </table>
       </form>
        
        <%

                Class.forName("com.mysql.jdbc.Driver");            

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

                Statement st = con.createStatement();
                
                                    
                ResultSet rs = st.executeQuery("select * from library.student");

                %>
                <br/>
                <br/>
                <br/>
                
                <table>
                    
                <tr>
                    <th>ROLL NO</th>
                    <th>NAME</th>
                </tr>
                <%
                while (rs.next()) {
                    
                    %>
                    <tr>
                        <td><%=rs.getInt(1) %></td>
                        <td><%=rs.getString(2) %></td>
                    </tr>
                    <%
                }
             %>
             </table>
        
    </body>
</html>
