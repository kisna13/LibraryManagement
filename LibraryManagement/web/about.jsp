<%-- 
    Document   : about
    Created on : 22-Oct-2020, 10:11:40 AM
    Author     : korin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <style>
            
            body {
              font-family: Arial, Helvetica, sans-serif;
              margin: 0;
            }

            html {
              box-sizing: border-box;
            }

            *, *:before, *:after {
              box-sizing: inherit;
            }

            .column {
              width: 33.3%;
              margin-bottom: 16px;
              padding: 0 8px;
              background-color: white;
            }

            .card {
              box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
              margin: 8px;
            }

            .about-section {
              padding: 5px;
              text-align: center;
              background-color: #474e5d;
              color: white;
            }

            .container {
              padding: 0 16px;
            }

            .container::after, .row::after {
              content: "";
              clear: both;
              display: table;
            }

            .title {
              color: grey;
            }

            .button {
              border: none;
              outline: 0;
              display: inline-block;
              padding: 8px;
              color: white;
              background-color: #000;
              text-align: center;
              cursor: pointer;
              width: 100%;
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

            @media screen and (max-width: 650px) {
              .column {
                width: 100%;
                display: block;
              }
            }
            
        </style>
    </head>
    <body style="background-color: #00FFFF">
        <%
            session = request.getSession(false);
        
            String name = (String) session.getAttribute("username");

            if(name==null)
            {
               response.sendRedirect("index.html");
            }
            
        %>
        <div class="about-section">
            <ul>
                <li>
                        <a>Welcome: <%
                           out.print(name); 
                        %> </a>
                </li>
                
                <li style="float:right"><a href="logout.jsp">Logout</a></li>
                <li style="float:right"><a class="active" href="home.html">Home</a></li>
            </ul>
        </div>

        <div class="row">
          <div class="column" style="margin-left: 400px">
            <div class="card">
              <img src="kisna.jpg" alt="Kisna" style="width:100% ">
              <div class="container">
                <h2>Kisna Koringa</h2>
                <p class="title">CEO & Founder</p>
                <p>Student of Marwadi University.</p>
                <p>Studying in 3rd year.</p>
                <p>kkrebels@gmail.com</p>
                <p><a href="contact.html" style="font-size: 20px;">Contact</a></p>
              </div>
            </div>
          </div>

        </div>

    </body>
</html>
