<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

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
      <a href="#" class="h1">Booking Service</a>
    </div>
	
    <div class="card-body">
      <form:form action="/pay" method="post" modelAttribute="order">
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="service_id" />
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="user_uid" />
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="price" />
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="description" />
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="currency" />
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="method" />
          <form:input hidden="hidden" required="required" type="text" class="form-control" path="intent" />
       	<div>
       		<dl class="row">
       			<dt class="col-lg-4 col-md-4 col-sm-4">Service Id</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.service_id}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">User Id</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.user_id}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Registered At</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.created}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Time Slot</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.time_from} - ${service.time_to}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Alloted Cleaner</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.cleaner_id}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Amount Required</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${amount} USD</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Room Count</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.room_count}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Address</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.address}</dd>
       			<dt class="col-lg-4 col-md-4 col-sm-4">Pincode</dt>
    			<dd class="col-lg-8 col-md-8 col-sm-8">${service.pincode}</dd>
       		</dl>
       	</div>
        <div class="row">
            <div class="col-7">
            </div>
            <div class="col-5">
                <button id="submit-button" type="submit" class="btn btn-primary btn-flat btn-block">Proceed to Pay</button>
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
</body>
</html>
