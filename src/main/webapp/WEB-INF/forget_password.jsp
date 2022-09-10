<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>User Registration</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/css/adminlte.min.css">
</head>

<body class="hold-transition  register-page">
<div class="login-box" style="width:fit-content">

<!-- /.login-logo -->
  <div class="card card-outline card-primary m-3">
    
  <div class="card-header text-center">      
      <a href="#" class="h1">Forget Password</a>
    </div>

    <div class="card-body">
      <form action="/password" method="post">
        <div class="row">
            
          <label>Question</label><span class="text-danger text-center"><errors path="question1" /></span>
        <div class="input-group mb-3">
        <select class="form-control" name="questions">
        <option value="question1">What is your favourite pet</option>
        <option value="question2">What is your favourite color</option>
        <option value="question3">What is your favourite movie</option>
        </select>
       
        
           </div>
            <div class="input-group mb-3">
         <input required="required" type="text" class="form-control" placeholder="Answer"
            id="answer" name="answer" />
            </div>
      
       
       
            <label>User Id &nbsp;</label><span class="text-danger text-center"><form:errors path="user_id" /></span>
        <div class="input-group mb-3">
        	<input required="required" type="text" class="form-control" placeholder="UserId"
        id="userId" name="userId"/>
       </div>
            <div class="col-5">
                <button  id="submit-button" type="submit" class="btn btn-primary btn-block">Check</button>
            </div>
        </div>
      </form>
      <P>${message}</P>
      
    </div>
    <!-- /.card-body -->
  </div>
  <!-- /.card -->
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/js/adminlte.min.js"></script>

</body>

</html>
