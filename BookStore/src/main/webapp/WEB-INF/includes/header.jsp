<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <header class="header">
        <nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="index.jsp">RidBuk</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Category</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <div class="">
                                    <li><a class="dropdown-item" href="#!">Major1</a></li>
                                    <li><a class="dropdown-item" href="#!">Major2</a></li>
                                    <li><a class="dropdown-item" href="#!">Major3</a></li>
                                </div>
                                <div class="">
                                    <li><a class="dropdown-item" href="#!">Major1</a></li>
                                    <li><a class="dropdown-item" href="#!">Major2</a></li>
                                    <li><a class="dropdown-item" href="#!">Major3</a></li>
                                </div>
                                <div class="">
                                    <li><a class="dropdown-item" href="#!">Major1</a></li>
                                    <li><a class="dropdown-item" href="#!">Major2</a></li>
                                    <li><a class="dropdown-item" href="#!">Major3</a></li>
                                </div>
                                <div class="">
                                    <li><a class="dropdown-item" href="#!">Major1</a></li>
                                    <li><a class="dropdown-item" href="#!">Major2</a></li>
                                    <li><a class="dropdown-item" href="#!">Major3</a></li>
                                </div>
                            </ul>
                        </li>
                    </ul>


                    <!-- Login page -->
                    <a class="btn btn-success" href="login.jsp" role="button"> Login</a>
                </div>
            </div>
        </nav>
    </header>

    <!-- Header-->
    <header class="bg-color py-5">
        <div class="container px-4 px-lg-5 my-5">
            <div class="text-center text-white">
                <h1 class="display-4 fw-bolder text-black">Welcome to RidBuk</h1>
                <p class="lead fw-normal text-black-50 mb-0">Every book will in your device</p>
            </div>
            <div class="container">

                <div class="row height d-flex justify-content-center align-items-center">

                    <div class="col-md-8">

                        <div class="search">
                            <i class="fa fa-search"></i>
                            <form action="SearchBookServlet">
                            <input id="searchinfo" name="searchinfo" type="text" class="form-control" style="display: inline-block; width: 80%" placeholder="Search books by Title, Author">
                    		<input type="submit" class="btn btn-primary" style="margin-bottom: 4px;" value="Search">
                            </form>
                            
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </header>