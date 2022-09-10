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
      <a href="#" class="h1">User Registration</a>
    </div>

    <div class="card-body">
      <form:form action="" method="post" modelAttribute="user">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-12">
            	<label>First Name &nbsp;</label><span class="text-danger text-center"><form:errors path="first" /></span>
                <div class="input-group mb-3">
                	<form:input required="required" type="text" class="form-control" placeholder="FirstName"
                id="FirstName" name="first" path="first" />
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-12">
            	<label>Last Name &nbsp;</label><span class="text-danger text-center"><form:errors path="last" /></span>
                <div class="input-group mb-3">
                	<form:input required="required" type="text" class="form-control" placeholder="LastName"
                id="LastName" name="last" path="last" />
                </div>
            </div>
        </div>
         <div class="row">
            <div class="col-6">
            	<label>DOB &nbsp;</label><span class="text-danger text-center"><form:errors path="dob" /></span>
                <div class="input-group mb-3">
                <form:input required="required" type="date" class="form-control" placeholder="dob"
                id="dob" name="dob" path="dob" min="1960-01-01" max="2003-12-31"/>
                </div>
            </div>
            <div class="col-6">
            	<label>Gender &nbsp;</label><span class="text-danger text-center"><form:errors path="gender" /></span>
                <div class="input-group mb-3">
                    <form:select path="gender" name="gender" required="required" class="form-control">
                        <form:option value="male">Male</form:option>
                        <form:option value="female">Female</form:option>
                        <form:option value="other">Other</form:option>
                    </form:select>
                </div>
            </div>
           
        </div>
	    <label>User Id &nbsp;</label><span class="text-danger text-center"><form:errors path="user_id" /></span>
        <div class="input-group mb-3">
        	<form:input minlength="5" required="required" type="text" class="form-control" placeholder="UserId"
        id="userId" name="userId" path="user_id"/>
          <div class="input-group-append">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
          </div>
        </div> 
        <label>Password &nbsp;</label>
        <div class="input-group mb-3">
        <input minlength="5" oninput="checkPass()" required type="password" class="form-control" placeholder="password"
        id="password" name="password" value="${password}" />
          <div class="input-group-append">
                <span id="eye-open" onclick="changeType('text')" class="input-group-text">
                    <i class="fas fa-eye"></i>
                </span>
                <span hidden id="eye-closed" onclick="changeType('password')"
                class="input-group-text">
                    <i class="fas fa-eye-slash"></i>
                </span>
          </div>
        </div>
        
        <div class="input-group mb-3">
            <input oninput="checkPass()" required type="password" class="form-control" placeholder="Enter Password Again" id="re-password" name="re-password">
        </div>
        <label>Email&nbsp;</label><span class="text-danger text-center"><form:errors path="mail" /></span>
        <div class="input-group mb-3">
            <form:input required="required" type="email" class="form-control" placeholder="example@gmail.com"
            id="email" name="email" path="mail"/>
            <div class="input-group-append">
                <span class="input-group-text"><i class="fas fa-envelope"></i></span>
            </div>
        </div>
 		 
        <label>Phone&nbsp;</label><span class="text-danger text-center"><form:errors path="phone" /></span>
        <div class="input-group mb-3">
            <form:input minlength="10" maxlength="10" required="required" type="text" class="form-control" placeholder="1234567890"
            id="phone" name="phone" path="phone" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))" />
            <div class="input-group-append">
                <span class="input-group-text"><i class="fas fa-phone"></i></span>
            </div>
        </div>
        
          <label>Question1</label><span class="text-danger text-center"><form:errors path="question1" /></span>
        <div class="input-group mb-3">
            <form:input required="required" type="text" class="form-control" placeholder="What is your favourite pet"
            id="Question1" name="Question1" path="question1"/>
        </div>
        <label>Question2</label><span class="text-danger text-center"><form:errors path="question2" /></span>
        <div class="input-group mb-3">
            <form:input required="required" type="text" class="form-control" placeholder="What is your favourite color"
            id="question2" name="question2" path="question2"/>
        </div>
       <label>Question3</label><span class="text-danger text-center"><form:errors path="question3" /></span>
       <div class="input-group mb-3">
            <form:input required="required" type="text" class="form-control" placeholder="What is your favourite movie"
            id="question3" name="question3" path="question3"/>
        </div>
       
        <div class="text-danger text-center mb-2">${message}</div>

        <div class="row">
            <div class="col-7">
                <p class="mb-0">
                    <a style="justify-self: center;align-items: center;display: flex;" href="./user_login" class="text-center">Already have an account?</a>
                </p>
            </div>
            <div class="col-5">
                <button disabled id="submit-button" type="submit" class="btn btn-primary btn-block">Register</button>
            </div>
        </div>
      </form:form>
      
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

<script>
        var pass = document.getElementById("password");
        var eye_o = document.getElementById("eye-open");
        var eye_c = document.getElementById("eye-closed");
        var submit = document.getElementById("submit-button");
        var re_pass = document.getElementById("re-password");
        var err = document.getElementById("error-msg");
        var changeType = (name) => {
            if (name == "text") {
                eye_o.hidden = true;
                eye_c.hidden = false;
                pass.type = "text"
            } else {
                eye_o.hidden = false;
                eye_c.hidden = true;
                pass.type = "password"
            }
        }

        var checkPass = () => {
            if (pass.value !== re_pass.value) {
                submit.disabled = true;
                re_pass.className = "form-control is-invalid";
            } else {
                submit.disabled = false;
                re_pass.className = "form-control is-valid";
            }
        }
</script>
</body>
</html>
