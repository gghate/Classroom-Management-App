<%@ page language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <!-- Default form register -->
<div style="margin: 0 auto;width:50%" >
<article class="card-body mx-auto" style="max-width:400px;">
    <h4 class="card-title mt-3 text-center">Book your appointment</h4>
     
<form  >
  <!--  <div class="form-group">
      <label for="exampleFormControlInput1">Email address</label>
      <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
    </div>

  -->
    <div class="form-group">
      <label for="companySelect">Company</label>
      <select class="form-control" id="companySelect"  name="company" required>
        
        <option value="Hi">Hi</option>
        
        
      </select>
    </div>
    <div class="form-group">
      <label for="companyPerson">Select Person</label>
      <select class="form-control" id="companyPerson"  name="person" required>
        
          <option value="Hi">HI</option>
      </select>
    </div>
 

 
  <div class="form-group">
    <label for="purpose">Purpose</label>
    <input type="text"class="form-control" id="purpose"  name="purpose" placeholder="purpose of visit" required>
  </div>
  <div class="form-group" style="text-align:center ;margin-top:15%">
      <button class="btn btn-primary mb-2" style="display:inline-block" >Submit Application</button>
  </div>
  </form>
</article>
</div>
<!-- Default form register -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>