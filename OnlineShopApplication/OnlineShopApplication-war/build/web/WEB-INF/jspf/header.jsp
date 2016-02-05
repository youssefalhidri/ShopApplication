<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<div class="header" align="right">
    <div style="display: inline-block;color: white"> <%="The user  " + request.getSession().getAttribute("username") + " is logged in "%>&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <a style="color: white"  href=<%= request.getContextPath() + "/logout.jsp"%>> <i><u>disconnect</u></i></a>
</div>