<%-- 
    Document   : shopping_cart
    Created on : 11 avr. 2015, 16:02:17
    Author     : hidri_000
--%>
<%@page import="com.shopapplication.model.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/template.css">
    </head>
    <body>
         <jsp:include page="WEB-INF/jspf/header.jsp"></jsp:include>
        <div id="search_result" class="search_result" style="background-color: rgb(221, 252, 252);" >

            <%
                Collection<ShoppingOrder> orders = (Collection<ShoppingOrder>) request
                        .getAttribute("orders");
            %>


            <div>
                <%
                    if (orders != null) {
                %>
                <table
                    style="margin-top: 50px; border-spacing:0px;border-color:white">

                    <tr valign="middle" height="50">
                        <th style="width: 100px;">Product Id</th>
                        <th style="width: 240px;">Product Name</th>
                        <th style="width: 150px;">Price</th>
                        <th style="width: 200px;">Order quantity</th>
                        <th  style="width: 350px;">Operations</th>
                    </tr>


                    <%
                        for (ShoppingOrder order : orders) {
                    %>
                    <tr>

                        <td align="center" style="width: 100px;"><%=order.getProduct().getProductID()%>
                        </td>
                        <td align="center" style="width: 240px;"><%=order.getProduct().getProductName()%>
                        </td>
                        <td align="center" style="width: 150px;"><%=order.getProduct().getPrice()%>
                        </td>
                        <td align="center" style="width: 350px;"><%=order.getProduct().getQuantity()%>
                        </td>
                        <td align="center">
                            <div id="result_actions">

                                <a
                                    href=<%=request.getContextPath() + "/index?_event=confirm_order"
                                                                        + "&product_id="
                                                                        + order.getProduct().getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                                    <img
                                        src=<%=request.getContextPath() + "/images/confirm-order.png"%>>
                                </a>
                                <a
                                    href=<%=request.getContextPath() + "/index?_event=remove_order"
                                                                        + "&product_id="
                                                                        + order.getProduct().getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                                    <img
                                        src=<%=request.getContextPath() + "/images/confirm-order.png"%>>
                                </a>

                            </div>
                        </td>

                    </tr>
                    <%}%><%}%>

                </table>
            </div>
            <br> <br>
        </div>
    </body>
</html>
