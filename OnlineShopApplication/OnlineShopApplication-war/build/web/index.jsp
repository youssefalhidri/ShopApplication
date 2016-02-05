<%-- 
    Document   : index
    Created on : 11 avr. 2015, 15:59:35
    Author     : hidri_000
--%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
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

        <div style="margin-bottom: 10px;">
            <ul id="menu_horizontal">
                <li class="bouton_gauche"><a target="_blank"
                                             href=<%=request.getContextPath() + "/index?_event=add_user&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                        <img src= <%= request.getContextPath() + "/images/add_user.jpg"%>  />Add user</a></li>
                <li class="bouton_gauche"><a target="_blank"
                                             href=<%=request.getContextPath() + "/index?_event=view_audit_trail&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                        <img src=<%=request.getContextPath() + "/images/audits.jpg"%>  />Audit trail</a></li>
                <li class="bouton_gauche"><a target="_blank"
                                             href=<%=request.getContextPath() + "/index?_event=view_shopping_cart&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                        <img src=<%=request.getContextPath() + "/images/shopping_cart.jpg"%>  />Shopping cart</a></li>
            </ul>
        </div>

        <div id="search_fields" class="search_fields">

            <form name="search_form" method="POST"
                  action='<%=request.getContextPath() + "/index?_event=search_product&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>'
                  style="margin-top: 30px;">
                <div style="width: 120px; display: inline-block; margin-left: 30px">
                    <b>Product Id :</b>
                </div>
                <input name="product_id" style="height: 30px; width: 200px;">

                <div style="width: 120px; display: inline-block; margin-left: 30px">
                    <b>Product Name : </b>
                </div>

                <input name="product_name" style="height: 30px; width: 200px;">



                <div style="width: 120px; display: inline-block; margin-left: 30px">
                    <b>Category : </b>
                </div>
                <select name="category" style="height: 30px; width: 200px">
                </select>

                <!--  
                <div style="width: 120px; display: inline-block;margin-left:30px">
                        <b>Creation Date : </b>
                </div>
                <input name="creation_date" type=date value=<%=new Date()%>style="height: 30px; width: 200px;"> <br>
                -->
                <br> <br> <br> <br>
                <div align="left">
                    <input type="submit" class="search_button" value="">
                </div>
            </form>

        </div>

        <div id="search_result" class="search_result">

            <div align="right"
                 style="background-color: rgb(26, 163, 219); height: 60px;">
                <a style="margin-right: 10px; margin-left: 10px;" target="_blank"
                   href=<%=request.getContextPath() + "/index?_event=add_new_product&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                    <img src=<%=request.getContextPath() + "/images/new.png"%>>
                </a>

            </div>

            <div>
                <%
                    String style = "simple";
                    String position = "top";
                    int maxPageItems = 5;
                    int maxIndexPages = 5;
                    List list = (List) session.getAttribute("products");
                    if (list == null) {
                        list = new ArrayList();
                    }
                %>


                <pg:pager items="<%= list.size()%>" index="center"
                          maxPageItems="<%= maxPageItems%>" maxIndexPages="<%= maxIndexPages%>"
                          isOffset="<%= true%>" export="offset,currentPageNumber=pageNumber"
                          scope="request">
                    <%-- keep track of preference --%>
                    <pg:param name="style" />
                    <pg:param name="position" />
                    <pg:param name="index" />
                    <pg:param name="maxPageItems" />
                    <pg:param name="maxIndexPages" />
                    <center>
                        <div style="height: 57px">

                            <pg:index >
                                <jsp:include page="WEB-INF/jspf/pager.jsp" flush="true" /> 			
                            </pg:index></div>

                        <table width="100%" cellspacing="0" cellpadding="4" border="1" style="border-color: rgb(207, 206, 218);
                               border-style: dotted;">

                            <tr align="center" bgcolor="#FFCE00"  style="background-color: rgb(26, 163, 219); color: white;">
                                <td><strong>Product Id</strong></td>
                                <td><strong>Product Name</strong></td>
                                <td><strong>Quantity</strong></td>
                                <td><strong>Price</strong></td>
                                <td><strong>Operation</strong></td>
                            </tr>

                            <%
                                for (int i = offset.intValue(), l = Math.min(i
                                        + maxPageItems, list.size()); i < l; i++) {
                            %>
                            <pg:item>
                                <tr>
                                    <td align="center" style="width: 120px;"><%=((Product) list.get(i)).getProductID()%>
                                    </td>
                                    <td align="center" style="width: 240px;"><%=((Product) list.get(i)).getProductName()%>
                                    </td>
                                    <td align="center" style="width: 200px;"><%=((Product) list.get(i)).getQuantity()%>
                                    </td>
                                    <td align="center" style="width: 150px;"><%=((Product) list.get(i)).getPrice() + " " + ((Product) list.get(i)).getCurrency()%>
                                    </td>
                                    <td align="center" style="width: 240px;"> 
                                        <a style="margin-right: 10px; margin-left: 10px;" target="_blank"
                                           href=<%=request.getContextPath() + "/index?_event=view_product&product_id=" + ((Product) list.get(i)).getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                                            <img src=<%=request.getContextPath() + "/images/view.png"%>>view
                                        </a>
                                        <a style="margin-right: 10px; margin-left: 10px;" target="_blank"
                                           href=<%=request.getContextPath() + "/index?_event=edit_product&product_id=" + ((Product) list.get(i)).getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                                            <img src=<%=request.getContextPath() + "/images/edit.png"%>>edit
                                        </a>
                                        <a style="margin-right: 10px; margin-left: 10px;" 
                                           href=<%=request.getContextPath() + "/index?_event=remove_product&product_id=" + ((Product) list.get(i)).getProductID() + "&csrfPreventionSalt=" + request.getAttribute("csrfPreventionSalt")%>>
                                            <img src=<%=request.getContextPath() + "/images/remove.png"%>>remove
                                        </a>

                                    </td>
                                </tr>
                            </pg:item>
                            <%  }%>
                        </table></center>
                    <hr>



                </pg:pager>


            </div>


            <br> <br>

        </div>

    </body>
</html>
