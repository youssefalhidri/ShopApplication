<%-- 
    Document   : view_product
    Created on : 11 avr. 2015, 16:02:46
    Author     : hidri_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.shopapplication.action.*"%>
<%@page import="java.util.*"%>
<%@page import="com.shopapplication.model.*"%>
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
            <div style="height: 400px; width: 600px; display: inline-block">
                <img
                    onerror="this.src='<%=request.getContextPath() + "/images/not_available_photo.jpg"%>'"
                    src=<%=request.getContextPath() + "/images/+" + product.getProductID() + ".jpg"%>>
                <br>

            </div>
            <div
                style="display: inline-block; width: 620px; height: 400px; vertical-align: top; margin-top: 50px">
                <table>
                    <tr>
                        <td width="220" valign="top"><b>Product Id :</b></td>
                        <td width="400"><%=product.getProductID()%></td>
                    </tr>
                    <tr>
                        <td width="220" valign="top"><b>Product Name :</b></td>
                        <td width="400"><%=product.getProductName()%></td>
                    </tr>
                    <tr>
                        <td width="220" valign="top"><b>Available quantity :</b></td>
                        <td width="400"><%=product.getQuantity()%></td>
                    </tr>
                    <tr>
                        <td width="220" valign="top"><b>Product Price :</b></td>
                        <td width="400"><%=product.getPrice() + " " + product.getCurrency()%></td>
                    </tr>
                    <tr>
                        <td width="220" valign="top"><b>Description :</b></td>
                        <td width="400"><%=product.getDescription()%></td>
                    </tr>

                    <tr>
                        <td width="220" height="100" valign="top"><b>Comment :</b></td>
                        <td width="400" height="100">
                            <form style="width: 400px"  method="POST" action=<%=request.getContextPath() + "/index?_event=add_comment&product_id=" + product.getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%> name="frmAddCommentProduct">
                                <textarea name = comment></textarea>
                                <input type="submit" name="submit" value="Add Comment">
                            </form>
                        </td>
                    </tr>

                    <tr>
                        <td width="200" height="100" valign="top"><b>Add To Shopping Cart :</b></td>
                        <td width="400" height="100">
                            <form style="width: 400px"  method="POST" action=<%=request.getContextPath() + "/index?_event=add_order&product_id=" + product.getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%> name="frmAddCommentProduct">
                                <div style="vertical-align:top;width: 130px; display: inline-block;margin-left:30px">
                                    <b>Order Amount</b>
                                </div>
                                <input name="amount" type="number" style="height: 30px; width: 125px;" min=0 required>
                                <input type="submit" name="submitOrder" value="Add Order" style="height: 34px; width: 100px;">
                            </form>
                        </td>
                    </tr>
                </table>

            </div >
            <%String comment = product.getComment();
                if (comment != null) {
            %>
        </div>
        <br>
        <div align="left;width: 600px;" >
            <h4>Customer comments :</h4>

            <%= comment%>
        </div>
        <%}%>
    </body>
</html>
