<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Book management</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/bootstraps.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="jquery.js"></script>
    <style>
        .readbtn {
            background: yellowgreen;
            color: white;
        }
    </style>
</head>

<body style="background-color: #D8C3A5;">
    <!-- Header & navigation bar-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a style="margin-left: 10px;"class="navbar-brand" href="index.jsp">
            <i class="fa fa-home" aria-hidden="true"></i> Home
        </a>
        <a class="navbar-brand" href="staff.jsp">
            <i class="fa fa-cogs" aria-hidden="true"></i> Manage
        </a>
        
        <div class="nav nav-tabs" id="tab-nav" roll="tablist">
            <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">
                List of books
            </button>

            <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-bill" type="button" role="tab" aria-controls="nav-bill" aria-selected="false">
                Bill
            </button>
        </div>
        <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Options
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item" href="UpdateServletAdmin?user=${NAME.username}">Update Account</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>


        <a class="nav-link" href="addbook.jsp">Add a book</a>

    </nav>
    <!-- Header & navigation bar END-->

    <!-- Books manager -->
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade p-3 show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <div class="container-lg mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg">
                        <div>
                            <table class="table table-warning table-bordered border-light table-hover">
                                <thead class="table-light">
                                    <tr class="text-center">
                                        <td>Title</td>
                                        <td>Image</td>
                                        <td>Description</td>
                                        <td>Price</td>
                                        <td>Rating</td>
                                        <td>ISBN</td>
                                        <td>Read Link</td>
                                        <td colspan="2">Action</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <%@ page import="java.util.ArrayList, Model.Book" %>
                                <%ArrayList<Book> allBooks = (ArrayList<Book>) request.getSession().getAttribute("allBooks"); %>
                                <%request.setAttribute("allBooks", allBooks); %>
                                    <c:forEach items="${requestScope.allBooks}" var="value">
                                        <tr>
                                            <td class="text-center"><c:out value="${value.title}" /></td>
                                            <td class="text-center"><img src="<c:out value="${value.imageUrl}" />" alt="ImageCover" style="width: 170px" /></td>
                                            <td class="text-center" id="description"><c:out value="${value.description}" /></td>
                                            <td class="text-center"><c:out value="${value.price}" /></td>
                                            <td class="text-center">Book Rating 01</td>
                                            <td class="text-center"><c:out value="${value.isbn}" /></td>
                                            <td class="text-center">
                                                <a href="<c:out value="${value.readLink}" />" download>Read</a>
                                            </td>
                                            <td class="text-center">
                                                <a class="btn btn-outline-secondary" href="./staffUpdateBook.html">Update</a>
                                            </td>
                                            <td class="text-center">
                                                <a class="btn btn-outline-secondary" href="DeleteBookServlet?bisbn=${value.isbn}">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
 
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Bill-->
        <div class="tab-pane fade p-3" id="nav-bill" role="tabpanel" aria-labelledby="nav-bill-tab">

            <div class="container-lg ">
                <div class="row justify-content-center">
                    <div class="col-lg">
                        <div>
                            <table class="table table-primary table-bordered border-light table-hover">
                                <thead class="table-light">
                                    <tr class="text-center">
                                        <td>Product ID</td>
                                        <td>Product Name</td>
                                        <td>Price</td>


                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="p" value="${PRODUCT}" scope="session" />
                                    <c:forEach items="${p}" var="product">
                                        <tr>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>


                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>





    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>

</html>