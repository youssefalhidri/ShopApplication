<%-- 
    Document   : login
    Created on : 11 avr. 2015, 16:01:45
    Author     : hidri_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv='Expires' content='0'>
        <meta http-equiv='Pragma' content='no-cache'>
        <meta http-equiv='Cache-Control' content='no-cache'>
        <link type="text/css" rel="stylesheet" href="css/template.css">
    </head>
    <body >
       
        <div id="login" class="login" align="right" >
            <div style="color: #65DB65;">
                <% if (request.getAttribute("message")!=null) { %>
                <%=request.getAttribute("message")%>
                <%}%>
            </div>
            <div style="width: 681px; height: 408px;"  >
                <form    method="POST" action=<%=request.getContextPath() + "/index?_event=login&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%> name="frmLogin"  style="height: 240px; width: 414px">
                    <center>
                        <div class="table"  >
                            <table cellspacing="10" style="width: 200px; height: 159px">
                                <tr>
                                    <th>Username</th>
                                    <td><input type="text"  maxlength="255"
                                               name="username" class="input"  style="width: 200px; height: 28px"></td>
                                </tr>
                                <tr>
                                    <th>Password</th>
                                    <td><input  type="password"
                                                maxlength="255" name="password" class="input" style="width: 200px; height: 28px"></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td style="height: 39px;" align="right">
                                        <input type="submit" name="submit" value="" class="login_button"> </td>
                                </tr>
                            </table>
                        </div>
                    </center>
                </form>
            </div>
        </div>

    </body>
</html>
