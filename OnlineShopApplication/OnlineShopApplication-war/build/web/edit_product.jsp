<%-- 
    Document   : edit_product
    Created on : 11 avr. 2015, 16:00:57
    Author     : hidri_000
--%>
<%@page import="com.shopapplication.action.*"%>
<%@ page import="java.util.*"%>
<%@page import="com.shopapplication.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/template.css">
    </head>
    <body id="body" class="body">
        <jsp:include page="WEB-INF/jspf/header.jsp"></jsp:include>
        <%Product product = (Product) request.getAttribute("product");%>
        <div id="view_div" style="display: inline-block; width: 1300px">
            <div style="height: 600px; width: 600px; display: inline-block">
                <img
                    onerror="this.src='<%=request.getContextPath() + "/images/not_available_photo.jpg"%>'"
                    src=<%=request.getContextPath() + "/images/+" + product.getProductID()%>>
                <br>

            </div>
            <div
                style="display: inline-block; width: 600px; height: 500px; vertical-align: top; margin-top: 50px">
                <form method=POST action=<%=request.getContextPath() + "/index?_event=update_product&product_id=" + product.getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                    <table>
                        <tr>
                            <td width="200" valign="top"><b>Product Id :</b></td>
                            <td width="400"><%=product.getProductID()%></td>
                        </tr>
                        <tr>
                            <td width="200" valign="top"><b>Product Name :</b></td>
                            <td width="400"><%=product.getProductName()%></td>
                        </tr>
                        <tr>
                            <td width="200" valign="top"><b>Available quantity :</b></td>
                            <td width="400">
                                <input name="quantity" value=<%=product.getQuantity()%> type="number" style="height: 30px; width: 125px;" min=0 required>
                            </td>
                        </tr>
                        <tr>
                            <td width="200" valign="top"><b>Product Price :</b></td>
                            <td width="400"><%=product.getPrice() + " " + product.getCurrency()%></td>
                        </tr>
                        <tr>
                            <td width="200" valign="top"><b>Description :</b></td>
                            <td width="400"><%=product.getDescription()%></td>
                        </tr>
                        <tr>
                            <td width="200" valign="top"><b></b></td>
                            <td width="400">
                                <div align="right" style="width:100px" >
                                    <input type="submit" name="submit" value="Submit" style="height: 28px; width: 100px;">
                                </div>
                            </td>
                        </tr>

                    </table>

                </form>
            </div>

        </div>
    </body>
</html>
