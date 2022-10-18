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


				<%@ page import="java.util.ArrayList, Model.Book" %>
                <%	ArrayList<Book> searchList = (ArrayList<Book>)request.getSession().getAttribute("SearchResults");%>
    <!--books-->
    <div class="container" id="bookContainer">
        <div class="row ">
        
        <%if (searchList != null){
			for (Book b: searchList) { %> 
        
        <div class="col-md-6 mb10" style="margin-bottom: 20px; margin-top: 20px;">
      <div class="row">
      <div class="col-md-4">
        <a href="<%=b.getReadLink() %>" class="thumbnail bge" title="HackSpace Magazine: Issue 58"><img style="width: 150px;" src="<%=b.getImageUrl() %>" alt="HackSpace Magazine: Issue 58"></a>
      </div>    
      <div class="col-md-8 justify">
      <p class="desClass">
      <a href="/books/1001664382115" title="HackSpace Magazine: Issue 58"><%=b.getTitle() %></a>
       <span class="nobr">
       <span class="nobr" title="Without ratings"><img src="/img/star0.png" width="16" height="16" alt="" class="star"><img src="/img/star0.png" width="16" height="16" alt="" class="star">
       <img src="/img/star0.png" width="16" height="16" alt="" class="star">
       <img src="/img/star0.png" width="16" height="16" alt="" class="star">
       <img src="/img/star0.png" width="16" height="16" alt="" class="star">
       <br>
       </span>
       </span>
       
      <%=b.getDescription() %>
      </p>      
      </div>
      </div>    
  </div>
  <%} } %>
        
			     </div>
                </div>