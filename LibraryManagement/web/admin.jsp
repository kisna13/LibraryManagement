<%-- 
    Document   : admin
    Created on : 31-Jul-2020, 4:00:58 PM
    Author     : koringa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                background-image: url("book.jpg");
              background-size: cover;
              position: relative;
              background-repeat: no-repeat;
            }
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
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                width: cover;
                background-color: #f1f1f1;
                text-align:center;
                margin-right: 30%;
                margin-left: 30%;
                margin-top: 8%;
              }

              li a {
                display: block;
                color: #000;
                padding: 8px 16px;
                text-decoration: none;
              }

              li a.active {
                background-color: #4CAF50;
                color: white;
              }

              li a:hover:not(.active) {
                background-color: #555;
                color: white;
              }
              .logout {
                
                width: 40%;
                border: none;
                background-color: #800000;
                color: white;
                padding: 14px 28px;
                font-size: 16px;
                margin-right: 30%;
                margin-left: 30%;
                cursor: pointer;
                text-align: center;
              }

              .logout:hover {
                background-color: #ddd;
                color: black;
              }
              
              .secul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
              }

              .secli {
                float: left;
              }

             .sec-li-a {
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

              .secactive {
                background-color: #4CAF50;
              }

            .secbutton:hover {
              background-color: #FFFF00;
              
              
        </style>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body style="background-color:lightgreen;">
        
         <% 
           
            session = request.getSession(false);
            String name = (String) session.getAttribute("username");
            
            if(name==null)
            {
               response.sendRedirect("index.html");
            }
            
        %>
        <ul class="secul">
            <li class="secli">
                <a class="sec-li-a" >Welcome: <% out.print(name); %> </a>
            </li>
            <li style="float:right"><a class="sec-li-a" href="about.jsp">About</a></li>
            <li style="float:right"><a class="sec-li-a" href="admin.jsp">Back to Library</a></li>
            <li style="float:right"><a class="sec-li-a" href="home.html">Home Page</a></li>
            <li style="float:right"><a class="sec-li-a" href="inbox.jsp">Inbox</a></li>
            
        </ul>
        
        
            
        <ul>
            <li><a class="active" href="admin.jsp" style="font-size:30px;">Home</a></li>
            <li><a href="addBook.jsp">Add Book</a></li>
            <li><a href="updateBook.jsp">Update Book</a></li>
            <li><a href="deleteBook.jsp">Delete Book</a></li>
            <li><a href="deleteStudent.jsp">Delete Student</a></li>
            <li><a href="viewStudents.jsp">View Students</a></li>
            <li><a href="viewBooks.jsp">View Books</a></li>
        </ul>
        
        
        <br/>
       
        <br/>

        <br/>
        
        <form action="logout.jsp" method="post">
            <input class='logout'  type="submit" value="LogOut">
        </form>
        
    </body>
</html>
