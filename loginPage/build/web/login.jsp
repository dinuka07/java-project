<%-- 
    Document   : login
    Created on : Dec 23, 2023, 8:49:03 PM
    Author     : Tajma eBay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="`loin.js">
</head>
<body>
    <h4></h4>
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            
            <div class="login-form">
                <div class="sign-in-htm">
                    <form method="POST" action="LoginServlet">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input name="username" type="text" class="input">
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Password</label>
                        <input name="password" type="password" class="input" data-type="password">
                    </div>
                    <div class="group">
                        <input id="check" type="checkbox" class="check" checked>
                        <label for="check"><span class="icon"></span> Keep me Signed in</label>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign In">
                    </div>
                    </form>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                    </div>
                </div>
                <div class="sign-up-htm">
                    <form method="POST" action="SignUpServlet">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="username" type="text" class="input">
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Password</label>
                        <input id="password" type="password" class="input" data-type="password">
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Repeat Password</label>
                        <input id="confirm_password" type="password" class="input" data-type="password">
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Email Address</label>
                        <input id="email" type="email" class="input">
                    </div>
                    <div class="group">
                        <button id="btn_submit" class="button">Sign Up</button>
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <label id="signin_link" for="tab-1">Already Member?</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(document).ready(function(){

  $('#btn_submit').click(function(e){
      e.preventDefault();
      
      var username  = $('#username').val();
      var password = $('#password').val();
      var confirm_password = $('#confirm_password').val();
      var email = $('#email').val();
      
      
      if (password == confirm_password) {
      $.post("SignUpServlet", 
      { 
          username : username,
          password : password,
          email : email,
      },
        function(data) {
            if(data.indexOf("SUCCESS") > -1) {
                $('#signin_link').click();
            } else {
                alert('Sign Up Failed. Please Try Again In A While');
            }
        });
    } else {
        alert("The Entered Passwords Does Not Match!");
    }
  });

});
</script>
</html>