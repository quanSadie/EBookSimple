<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Staff-Add New Book</title>
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
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="alert alert-warning" role="alert">
                A simple primary alert—check it out!
            </div>
            <div class="col-lg-6 border border-primary border-3 rounded-3">
                <div class="text-center">
                    <h2 class="text-primary">Add a book</h2>
                </div>
                
                <form action="uploadBook" method="post" enctype="multipart/form-data">
                
                	<div class="my-3">
                        <label class="form-label">Author ID </label>
                        <input type="text" class="form-control" name="authorid">
                    </div>
                
               		<div class="my-3">
                        <label class="form-label">Author </label>
                        <input type="text" class="form-control" name="book_author">
                    </div>
                    <div class="my-3">
                        <label class="form-label">Book Title </label>
                        <input type="text" class="form-control" name="nameP">
                    </div>

                    <div class="my-3">
                        <label class="form-label">Book Price </label>
                        <input type="text" class="form-control" name="bprice">
                    </div>

                    <div class="my-3">
                        <label for="userInput" class="col-form-label">Upload Photo</label>
                        <input class="form-control" type="file" name="photo"  onchange="ValidateSingleInput(this);">
                    </div>

                    <div class="my-3">
                        <label class="form-label">Category </label>
                        <input type="text" class="form-control" name="categoryID">
                    </div>
                    
                    <div class="my-3">
                        <label class="form-label">ISBN </label>
                        <input type="text" class="form-control" name="b_isbn">
                    </div>

                    <div class="my-3">
                        <label class="form-label">Book Description </label>
                        <input type="text" class="form-control" name="bdescription">
                    </div>


                    <div class="my-3">
                        <label class="form-label">Book file </label>
                        <input class="form-control" type="file" name="bookFile">
                    </div>

                    <div class="text-center mb-3">
                        <input type="submit" class="btn btn-outline-primary" value="Add New Product">
                        <a class="btn btn-outline-secondary" name="submit" href="staff.jsp">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>



	<script src="js/checkFileType.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>

</html>