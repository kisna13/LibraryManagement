<%-- 
    Document   : logout
    Created on : 22-Oct-2020, 11:18:36 AM
    Author     : korin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My JSP 'logout.jsp' starting page</title>
    </head>
    <body>
        <%
            session = request.getSession(false);
        
            String name = (String) session.getAttribute("username");
            
            if(name==null)
            {
               response.sendRedirect("index.html");
            }
            
            
        %>
        
        <%request.getSession().setAttribute("username", null);%>
        <h1 style="text-align:center;">
            Your session has expired. Click <a href='index.html'>here</a> to login again.<br>
        </h1>
        
    </body>
</html>
