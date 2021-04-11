<%-- 
    Document   : viewStudents
    Created on : 14-Aug-2020, 10:27:45 AM
    Author     : korin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
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
        
        <h1 style="text-align:center;">List of Students in Library.</h1>
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
                    <th>STUDENT ROLL NO</th>
                    <th>STUDENT NAME</th>
                    <th>PASSWORD</th>
                </tr>
                
                <%
                while (rs.next()) {
                    
                    %>
                    <tr>
                        <td><%=rs.getInt(1) %></td>
                        <td><%=rs.getString(2) %></td>
                        <td><%=rs.getString(3) %></td>
                    </tr>
                    <%
                }
             %>
             </table>
             
             <br/>
             <br/>
             <br/>
                
    </body>
</html>
