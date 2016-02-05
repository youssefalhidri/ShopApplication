<%-- 
    Document   : add_user
    Created on : 11 avr. 2015, 16:00:43
    Author     : hidri_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/template.css">
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"></jsp:include>
        <div id="new_user" class="new_user">
            <form method="POST" action=<%=request.getContextPath() + "/index?_event=submit_new_user&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%> name="frmNewUser">

                <br><br>

                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>User Id :</b>
                </div>
                <input name="user_id" type="text" style="height: 30px; width: 300px;" required>
                <br><br>

                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Password :</b>
                </div>
                <input type="password" name="password" style="height: 30px; width: 300px;" required> 
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>User Name :</b>
                </div>
                <input name="username" type="text" style="height: 30px; width: 300px;" required>
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Role :</b>
                </div>	
                <select name="role" style="height: 30px; width: 300px;" required>
                    <option value="Administrator">Aministrator</option>
                    <option value="Customer">Customer</option>
                </select>
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>E-mail :</b>
                </div>
                <input name="email" type="text" style="height: 30px; width: 300px;">
                <br><br>
                <div align="right" style="width: 462px; ">
                    <input type="submit" name="submit" value="Submit">
                </div>
            </form>
        </div>
    </body>
</html>
