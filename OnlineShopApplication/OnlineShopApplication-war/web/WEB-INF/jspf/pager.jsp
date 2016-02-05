<%-- 
    Document   : pager
    Created on : 11 avr. 2015, 19:32:32
    Author     : hidri_000
--%>

<%@ page session="false" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>

<table border=0 cellpadding=0 width=10%   cellspacing=0>
    <tr align=center valign=top>
        <td valign=bottom><font face=arial,sans-serif
                                size=-1> </font></td>
            <pg:prev export="pageUrl" ifnull="<%= true%>">
                <% if (pageUrl != null) {%>
            <td align=right><A HREF="<%= pageUrl%>"> <br>
                    <b>Previous</b></A></td>
                    <% }   %>

        </pg:prev>
        <pg:pages>
            <% if (pageNumber == currentPageNumber) {%>
            <td> <br>
                <font color=#A90A08><%= pageNumber%></font></td>
                <% } else {%>
            <td><A HREF="<%= pageUrl%>"> <br>
                    <%= pageNumber%></A></td>
                    <% }%>
                </pg:pages>
                <pg:next export="pageUrl" ifnull="<%= true%>">
                    <% if (pageUrl != null) {%>
            <td><A HREF="<%= pageUrl%>"> <br>
                    <b>Next</b></A></td>
                    <% }%>
                </pg:next>
    </tr>
</table>

