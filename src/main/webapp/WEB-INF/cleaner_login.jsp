<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Cleaner Login</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/css/adminlte.min.css">

</head>

<body class="hold-transition login-page">
<div class="login-box">

<!-- /.login-logo -->
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
      <a href="#" class="h1">Cleaner Login</a>
    </div>

    <div class="card-body">
    <form action="" method="post">
        <div class="input-group mb-3">
        <input required type="text" class="form-control" placeholder="CleanerId"
        id="cleanerId" name="cleanerId" value="${cleanerId}">
          <div class="input-group-append">
            <span class="input-group-text"><i class="fas fa-user"></i></span>
          </div>
        </div>
        <div class="input-group mb-3">
        <input required type="password" class="form-control" placeholder="password"
        id="password" name="password" value="${password}">
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
        <div class="text-danger text-center">${message}</div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="remember" name="remember">
              <label for="remember">
                Remember Me
              </label>
            </div>
          </div>
          <div class="col-4">
            <button type="submit" class="btn btn-primary btn-block">Log In</button>
          </div>
        </div>
      </form>
      <%-- <p class="mb-1">
        <a href="./forgot-send.php">Forgot Password</a>
      </p> --%>
	  <p class="mb-0">
		<a href="/cleaner_register" class="text-center">Don't have an account?</a>
      </p>
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
</script>

</body>
</html>
