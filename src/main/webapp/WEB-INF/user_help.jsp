<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Help</title>
   <link  href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.4.6/tailwind.min.css" rel="stylesheet" />
   <link  href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/1.4.6/tailwind.min.css" rel="stylesheet" />
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
   
  
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@400;600;700&display=swap');

.font-nunito{
  font-family: Nunito Sans, san-serif;
}
</style>
<body class="font-nunito antialiased bg-gray-100 text-gray-900 my-16 flex items-center justify-center">
    <form:form action="" method="post" modelAttribute="ticket">
    <div class="container mx-auto px-4 sm:px-8 max-w-3xl">

        <div class="main-title mb-8">
            <h1 class="font-bold text-2xl text-center">How can we help you?</h1>
        </div>

        <div  x-data class="main-search mb-8 rounded-lg shadow-lg px-6 py-3 w-full flex items-center space-x-6 border border-gray-200 border-opacity-75">
            <button class=" focus:outline-none" @click="$refs.search.focus()">
                <svg 
                    class="w-6 h-6 text-gray-500"
                    fill="none" stroke-linecap="round" 
                    stroke-linejoin="round" stroke-width="2" 
                    viewBox="0 0 24 24" stroke="currentColor">
                    <path d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                </svg>
            </button>
            <form:input path="issue" x-ref="search" type="text" placeholder="Description" name="issue" class="text-base w-full bg-transparent focus:outline-none" />
        </div>
		  <div  x-data class="main-search mb-8 rounded-lg shadow-lg px-6 py-3 w-full flex items-center space-x-6 border border-gray-200 border-opacity-75">
            <button class=" focus:outline-none" @click="$refs.search.focus()">
                <svg 
                    class="w-6 h-6 text-gray-500"
                    fill="none" stroke-linecap="round" 
                    stroke-linejoin="round" stroke-width="2" 
                    viewBox="0 0 24 24" stroke="currentColor">
                    <path d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                </svg>
            </button>
            <form:input path="description" x-ref="search" type="text" placeholder="Describe your issue" name="description" class="text-base w-full bg-transparent focus:outline-none" />
        </div>
        <div class="form-group" style="margin-bottom:5%">
        <label  class="font-bold ">Date of issue</label>
              <input id="td" type="date" class="form-control" disabled/>
        </div>

        <div>
            <span class="text-danger text-center"><form:errors path="issue" /></span>
            <span class="text-danger text-center"><form:errors path="description" /></span>
        </div>

        <input class="btn btn-primary" type="submit" style="margin-left:40%" value="submit"/>
        </form:form>
        <footer style="margin-top:200px">
 <%@ include file="common/footer.jsp" %>
</footer>
        


 
</body>
<script>
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

document.getElementById('td').value = new Date().toDateInputValue();
var status = '${status}';
console.log(status,status=='success');
if(status=='success'){
	alert('successfully posted')
}

</script>
<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.3.5/dist/alpine.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>