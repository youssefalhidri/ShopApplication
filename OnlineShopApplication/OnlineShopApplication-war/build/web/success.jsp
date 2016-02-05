<%-- 
    Document   : success
    Created on : 11 avr. 2015, 16:02:35
    Author     : hidri_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div style="display:inline-block;margin-left: 100px">
            <img src=<%=request.getContextPath() + "/images/success.png"%> style="height:73px" >
            <div style="display:inline-block; height: 86px">
                <h4 style="display:inline-block;vertical-align: middle;">Operation completed successfully</h4>
                <br>
                <% if (request.getAttribute("message")!=null) { %>
                &nbsp;<%=request.getAttribute("message")%>
                <%}%>
            </div>

        </div>
    </body>
</html>
