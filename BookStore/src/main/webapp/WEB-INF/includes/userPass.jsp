<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <section style="height:auto;">
        <div class="container" style="height: auto !important;">
            <a href="index.jsp" title="Bookstore" style="text-decoration: none;">Home</a>
             > 
            <a href="#" title="Books" style="text-decoration: none;" >Account</a>
             > 
            <b>Book</b>
            <hr>
        </div>
        <!--books-->
        <%@ page import="java.util.ArrayList, Model.Account" %>
    <%	Account acc = (Account )request.getSession().getAttribute("person");%>
    <div class="container" id="bookContainer">
        <div class="row ">
            <div class="col-md-4">
                    <section class="user-sidebar">
                        <div class="userinfo ">
                            <div class="useritem">
                                <figure>
                                    <img alt="" src="//st.ntcdntempv3.com/data/siteimages/anonymous.png" class="avatar user-img">
                                    <figcaption>
                                        <div class="user-name"><%=acc.getFullName() %></div>    
                                    </figcaption>
                                </figure>
                            </div>          
                        </div>
                    </section>
                    <nav class="user-sidelink" style="background-color: #222;">
                        <ul id="property1" class="" role="tablist">
                            <li role="presentation" class="active">
                                <a class="linklist"  href="userPage.jsp" ><i class="fa fa-tachometer"></i>  User profile</a>
                            </li>
                            <li role="presentation" class="active">
                                <a class="linklist" href="userInfo.jsp" ><i class="fa fa-info-circle"></i>  Information</a>
                            </li>
                            <li role="presentation" class="active">
                                <a class="linklist" href="#" ><i class="fa fa-book"></i>  Books owned</a>
                            </li>
                            <li role="presentation" class="active">
                                <a class="linklist" href="changePass.jsp"><i class="fa fa-lock"></i>  Change password</a>
                            </li>
                        </ul>
                    </nav>
                    
            </div>
            <div class="col-md-6">
                    <h2>Change Password</h2>
                    <label>Current Password</label>
                    <div class="form-group pass_show">
                        <input type="password" value="faisalkhan@123" class="form-control" placeholder="Current Password">
                    </div>
                    <label>New Password</label>
                    <div class="form-group pass_show">
                        <input type="password" value="faisal.khan@123" class="form-control" placeholder="New Password">
                    </div>
                    <label>Confirm Password</label>
                    <div class="form-group pass_show">
                        <input type="password" value="faisal.khan@123" class="form-control" placeholder="Confirm Password">
                    </div>
                    <div class="form-group" style="margin-top: 10% ;">
                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Change Password" type="submit">
                    </div>
                </div>
        </div>
    </div>
    </section>