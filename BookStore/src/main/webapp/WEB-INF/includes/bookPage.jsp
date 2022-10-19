<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<section style="height:auto;">
        <div class="container" style="height: auto !important;">
         <%@ page import="Model.Book" %>
          	<% Book b1 = (Book) request.getSession().getAttribute("thisBookDetail"); %>
          	<% pageContext.setAttribute("b1", b1);  %>
            <a href="index.jsp" title="Bookstore" style="text-decoration: none;">Bookstore</a>
             > 
            <a href="#" title="Books" style="text-decoration: none;" >Books</a>
             > 
            <b><c:out value="${b1.title}" /></b>
            <hr>
        </div>
        <div class="container" id="bookContainer" style="height: auto !important; background: rgba(216,195,192,0.5)">
            <div class="col-md-4 col-sm-6 sticky1" style="height:auto !important; min-height: 0px !important;">
                <div class="bge2" style="background-color: #D8C3A2;">
                    <img src="<c:out value="${b1.imageUrl}" />" alt="Day la ten sach" title="Day la ten sach" width="100%" itemprop="image" class="imgborder bookCover" style="background: #eee;">
                    <div class="mrg20">
                    <%if (!b1.getPrice().equals("FREE")) { %>
                    	<a href="" class="btn btn-block btn1 max300" rel="nofollow" target="_blank" title="buy" style="background: #b2395b; color:#fff;">BUY</a>
                    <%} else { %>
                    	 <br>
                    <%} %>
                    <br>
                    <a href="<c:out value="${b1.readLink}" />" class="btn btn-block btn-default max300" rel="nofollow" target="_blank" title="preview" style="background: #939393; color:#fff;">Read</a>
          
                </div>
                </div>
                
            </div>
            <div class="col-md-8 col-sm-6 desc">
                <table class="table table-striped">
                    <tbody>
                        <tr>
                            <td>Price</td>
                            <td class="color2">
                                <b>FREE</b>
                                <div itemscope itemprop="offers" itemtype=""></div>
                            </td>
                        </tr>
                        <tr>
                            <td>Rating</td>
                            <td class="nobr">
                                <span class="nobr" title="Without ratings">
                                    <img src="https://itbook.store/img/star0.png" width="16" height="16" alt class="star">
                                    <img src="https://itbook.store/img/star0.png" width="16" height="16" alt class="star">
                                    <img src="https://itbook.store/img/star0.png" width="16" height="16" alt class="star">
                                    <img src="https://itbook.store/img/star0.png" width="16" height="16" alt class="star">
                                    <img src="https://itbook.store/img/star0.png" width="16" height="16" alt class="star">
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="t50">Author</td>
                            <td>
                                <b>N/A</b>
                            </td>
                        </tr>

                        <tr>
                            <td>Pages</td>
                            <td>
                                <b itemprop="numberOfPages">200</b>
                            </td>
                        </tr>
                        <tr>
                            <td>Format</td>
                            <td>
                                <b>Paper book / ebook</b>
                                (
                                    <b>PDF</b>
                                )
                            </td>
                        </tr>
                        <tr>
                            <td>ISBN13</td>
                            <td>
                                <b itemprop="isbn"><c:out value="${b1.isbn}" /></b>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <ul id="property1" class="nav nav-tabs" role="tablist" style="text-decoration: none;margin-left: 5px;margin-right: 5px;">
                    <li role="presentation" class="active">
                        <b>Description</b>
                    </li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="desc" itemprop="description">
                    <c:out value="${b1.description}" />
                    </div>
                </div>
            </div>
        </div>
    </section>