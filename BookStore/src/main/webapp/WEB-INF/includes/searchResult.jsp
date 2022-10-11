<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
        <div class="row">
            <div class="col-md-9">
                <h1>"Ten search" search result</h1>
            </div>
            <div class="col-md-3">
                <div class="form-group sortby">
                    <label for="sortby" class="col-sm-4 control-label"> Sort by: </label>
                    <div class="col-sm-8 right">
                        <select name="sortby" id="sortby">
                        <option value="relevance" selected="selected">Relevance</option>
                        <option value="date">Date</option>
                        <option value="rating">Rating</option>
                        <option value="title">Title</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                Found
                <b>672</b> books
            </div>
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
                if (searchList != null){
				for (Book b: searchList) { %>
				
				<div class="row mb-1 d-flex justify-content-between">
                    <div class="col-md-3 col-sm-6 col-xl-4 text-center">
                        <a href="#" class="thumbnail bge">
                            <img src="<%=b.getImageUrl() %>" alt="Book cover" class="lazy" style="display:inline-block;">
                        </a>
                    </div>
                    <div style="margin: auto;" class="col-md-9 col-sm-6 desc col-xl-8">
                            <p>
                                <a href="#" class="b text-decoration-none">
                                    <span class="price">b.getPrice()</span> <%=b.getTitle()%>
                                </a>
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
                            <p> <%=b.getDescription() %>
                            </p>
                    </div>
                    </div>
                    </div>
				
				<%
					}
				}
			%>
			
			
                
                    <p class="pagination"><strong>Pages:</strong> <a href="/search/python?page=1"><strong>1</strong></a>, <a href="/search/python?page=2">2</a>, <a href="/search/python?page=3">3</a> ... <a href="/search/python?page=68">68</a> | <a href="/search/python?page=2">Next→</a></p>
                    </div>
                </div>