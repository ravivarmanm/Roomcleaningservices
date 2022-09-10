<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>User Payment</title>

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
<div class="login-box" style="min-width:50vw;max-width:min-content">

<!-- /.login-logo -->
  <div class="card card-outline card-primary m-3">
    
  	<div class="card-header text-center">      
      <a href="#" class="h1">Service Booked Successfully</a>
    </div>
	
    <div class="card-body">
       	<div>
       		<dl class="row">
       			<dt class="col-lg-4 col-md-4 col-sm-4">Service Id</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service_id}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">User Id</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${user_id}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Registered At</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${created}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Time Slot</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${time_from} - ${time_to}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Alloted Cleaner</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${cleaner_id}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Amount Required</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${amount} USD</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Room Count</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${room_count}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Address</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${address}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Pincode</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${pincode}</dd>
       		</dl>
       	</div>
        <div class="row">
            <div class="col-7">
            </div>
            <div class="col-5">
                <a role="button" href="./" id="submit-button" class="btn btn-success btn-flat btn-block">Go Dashboard</a>
            </div>
        </div>
      
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
