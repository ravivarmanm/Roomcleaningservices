<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Service Booking</title>
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
          <a href="#" class="h1">Booking</a>
        </div>
         <div class="card-body">   
        <form:form action="" method="post" modelAttribute="booking">
        
                 <div class="input-group mb-3">
                 <form:input oninput="setRoomCount()" min="1" max="10" required="required" type="number" class="form-control" placeholder="Room Count"
                id="roomCount" name="roomCount" path="room_count" />
                <small class="form-text text-muted"> No of Rooms needs to clean (hall also counted as room)</small>
                </div>
                 <div class="input-group mb-3">
                	<form:input required="required" type="text" class="form-control w-100" placeholder="address"
                id="address" name="address" path="address" />
                </div>
                 <div class="input-group mb-3">
                	<form:input minlength="6" maxlength="6" required="required" type="text" class="form-control w-100" placeholder="PinCode"
                id="pincode" name="pincode" path="pincode" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))" />
                	<small class="form-text text-muted"> Enter 6-digit pin code/postal code. Eg: 600001</small>
             
                </div>
                  <label>Time From</label>
                 <div class="input-group mb-3">
                	<input oninput="setTimeFrom()" class="form-control w-100" type="datetime-local" id="time_from" name="time_from"  />
                	<small id="time_from_help" class="form-text text-muted"></small>
                </div>
                  <label>Time To</label>
                 <div class="input-group mb-3">
                	<input class="form-control w-100" type="datetime-local" id="time_to" name="time_to" />
                	<small id="time_to_help" class="form-text text-muted"></small>
                </div>
             
                 <div class="input-group mb-3">
                	<form:input minlength="10" maxlength="10" required="required" type="text" class="form-control" placeholder="contact number"
                id="contactNumber" name="contactNumber" path="contactNumber" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))" />
                </div>
          <div class="col-4">
            <input type="submit" class="btn btn-primary btn-block"value="Register">
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
 
 <script type="text/javascript">
 	let room_count = document.getElementById("roomCount");
 	let time_from = document.getElementById("time_from");
 	let time_from_help = document.getElementById("time_from_help");
 	let time_to_help = document.getElementById("time_to_help");
 	let time_to = document.getElementById("time_to");
 	
 	time_to.disabled = true;
 	
 	let date = new Date(); 	
 	
 	date.setDate(date.getDate() + 1);	
 	let t1 = "Enter a date from ";
 	time_from.min = date.toJSON().slice(0,19);
 	time_from.value = date.toJSON().slice(0,19);
	t1 += date.toDateString();
 	date.setDate(date.getDate() + 30);	
	t1 += " to "+date.toDateString();
 	time_from.max = date.toJSON().slice(0,19);
 	
 	time_from_help.textContent = t1;
 	
 	function setTimeFrom(){
 		
 		time_to.disabled = false;
 		
 		let t1 = "Enter a date from ";
 			 
 		let date = new Date(time_from.value);
 		date.setDate(date.getDate() + 1);
 		t1 += date.toDateString();
 		time_to.value = date.toJSON().slice(0,19);
 		time_to.min = date.toJSON().slice(0,19);

 		date.setDate(date.getDate() + 2);	
 		t1 += " to "+date.toDateString();
 	 	time_to.max = date.toJSON().slice(0,19);
 	 	time_to_help.textContent = t1;
 	 	
 	}
 	
 	setTimeFrom();
 	
 	function setRoomCount(){
 		let temp = room_count.value;
 		if(temp < 1) room_count.value = "1";
 		else if(temp > 10) room_count.value = "10";
 	}
 </script>

</body>
</html>
