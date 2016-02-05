<%-- 
    Document   : new_product
    Created on : 11 avr. 2015, 16:02:05
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
        <jsp:include page="/WEB-INF/jspf/header.jsp"></jsp:include>
        <div id="new_product" class="new_product">
            <form method="POST" action=<%=request.getContextPath() + "/index?_event=submit_new_product&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%> name="frmNewProduct">

                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Product Name :</b>
                </div>
                <input name="product_name" type="text" style="height: 30px; width: 300px;" required>
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Category :</b>
                </div>	
                <select name="category" style="height: 30px; width: 300px;" required>
                    <option value="" selected></option>
                    <option value="Books">Books</option>
                    <option value="Computers">Computers</option>
                    <option value="Cars">Cars</option>
                    <option value="Others">Others</option>
                </select>
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Quantity :</b>
                </div>
                <input name="quantity" type="number" style="height: 30px; width: 300px;" min=0 required>
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Price :</b>
                </div>
                <input type="number" name="price" style="height: 30px; width: 300px;" min=0 step="0.01" required> 
                <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Currency :</b>
                </div>
                <select name="currency"  style="height: 30px; width: 300px;" required>
                    <option value="EUR">EUR</option>
                    <option value="USD">USD</option>
                </select> <br><br>
                <div style="vertical-align:top;width: 120px; display: inline-block;margin-left:30px">
                    <b>Description :</b>
                </div>
                <textarea name="description" style="height: 100px; width: 300px;"></textarea>
                <br><br>
                <div align="right" style="width: 462px; ">
                    <input type="submit" name="submit" value="Submit">
                </div>
            </form>
        </div>
    </body>
</html>
