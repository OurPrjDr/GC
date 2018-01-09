<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Contact</title>
</head>
<body>

 <form method="post" action="NewContact">
  <div class="container">
    <label><b>Fist Name</b></label>
    <input type="text" placeholder="Enter first name" name="firstName" required/>
    <br>
    <label><b>Last Name</b></label>
    <input type="text" placeholder="Enter last name" name="lastName" required/>
        <br>
    
    <label><b>Email</b></label>
    <input type="text" placeholder="Enter email" name="email" required/>
        <br>
    
 
    
     <fieldset>
    <legend>Address</legend>
 
        <label><b>Street</b></label>
    		<input type="text" placeholder="Enter street" name="street"  />
        <br>
         <label><b>City</b></label>
    		<input type="text" placeholder="Enter city" name="city" />
        <br>
                 <label><b>Zip</b></label>
    		<input type="text" placeholder="Enter zip" name="zip"  />
        <br>
                 <label><b>Country</b></label>
    		<input type="text" placeholder="Enter country" name="country"  />
        <br>
    </fieldset>
    
    
    <fieldset>
    <legend>Phones</legend>
        <fieldset>
		    <legend>Mobile Phones</legend>
 		        
		        <label><b>Phone Kind</b></label>
		        <input type="text" placeholder="Enter Mobile Phone kind" name="phoneMobileKind"   />  <br>
		        
		        <label><b>Phone Number</b></label>
		        <input type="text" placeholder="Enter Mobile Phone number" name="phoneMobileNumber"   />  <br>
		        
        </fieldset>
                <fieldset>
		    <legend>House Phones</legend>
 		        
		        <label><b>Phone Kind</b></label>
		        <input type="text" placeholder="Enter House Phone kind" name="phoneHouseKind"   />  <br>
		        
		        <label><b>Phone Number</b></label>
		        <input type="text" placeholder="Enter House Phone number" name="phoneHouseNumber"   />  <br>
		        
        </fieldset>
                <fieldset>
		    <legend>Office Phones</legend>
 		        
		        <label><b>Phone Kind</b></label>
		        <input type="text" placeholder="Enter Office Phone kind" name="phoneOfficeKind"   />  <br>
		        
		        <label><b>Phone Number</b></label>
		        <input type="text" placeholder="Enter Office Phone number" name="phoneOfficeNumber"   />  <br>
		        
        </fieldset>
         
    </fieldset> 


 
    <input type="submit" value="submit"/>     
    <input type="reset" value="reset"/> 
   </div>
  </form> 


</body>
</html>