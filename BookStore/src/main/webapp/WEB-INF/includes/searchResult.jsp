<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
        <div class="row">
            <div class="col-md-9">
                <h1 style="">Search results for "<span style="font-style: italic; color: blue; ">
                <%=request.getSession().getAttribute("searchKey") %>
                </span>"</h1>
            </div>
            <hr>
        </div>
        <br>
        <ins class=""></ins>
    </div>
    <br>

    <!--books-->
    <div class="container" id="bookContainer">
        <div class="row ">
            <div class="col-md-8">
                <!--The book info start-->
                <%@ page import="java.util.ArrayList, Model.Book" %>
                <%	ArrayList<Book> searchList = (ArrayList<Book>)request.getSession().getAttribute("SearchResults");%>
                
                <%
                if (searchList == null) {
                	response.sendRedirect("index.jsp");
                }
                if (searchList != null){
				for (Book b: searchList) { %>
				
				<div style="margin: auto;" class="row mb-1 d-flex justify-content-between">
                    <div class="col-md-9 col-sm-6 col-xl-4 text-center">
                        <a href="#" class="thumbnail bge">
                            <img src="<%=b.getImageUrl() %>" alt="Book cover" class="lazy" style="display:inline-block;">
                        </a>
                    </div>
                    <div style="margin: auto;" class="col-md-9 col-sm-6 desc col-xl-8">
                            <p>
                                <a href="ShowOneBookServlet?currentISBN13=<%=b.getIsbn()%>" class="b text-decoration-none">
                                    <%=b.getTitle()%>
                                </a>
                                <br>
                                 <span style="font-weight: 700;" class="price"><%=b.getPrice()%></span>
                                 <br>
                                <span class="nobr">
                                    <span class="nobr" title="without ratting">
                                        <img src="./img/star0.png" width="16" height="16" alt="star">
                                        <img src="./img/star0.png" width="16" height="16" alt="star">
                                        <img src="./img/star0.png" width="16" height="16" alt="star">
                                        <img src="./img/star0.png" width="16" height="16" alt="star">
                                        <img src="./img/star0.png" width="16" height="16" alt="star">
                                    </span>
                                </span>
                            </p>
                            <p>
                                <small>
                                    by
                                    <b>Christian Mayer</b>
                                </small>
                            </p>
                            <p class="desClass"> <%=b.getDescription() %>
                            </p>
                    </div>
                    </div>
                    <br>
                    </div>
				
				<%
					}
				}
			%>
			     </div>
                </div>