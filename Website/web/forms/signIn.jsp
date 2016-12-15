<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link href="../css/profemonFeatures.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <title></title>
</head>
<body>

<script>
  $( document ).ready(function() {
    $("#navbar").load("navbar.html");

    $("#signIn").click(function () {
      var data = {
        username: $("#username").val(),
        password: $("#password").val()
      };
      $.ajax({
        type: "POST",
        url: "/SignIn",
        dataType: "json",
        data: JSON.stringify(data),
        success: [
          function(data) {
            alert(data.isRegistered);
            if(data.isRegistered) {
              window.location.replace("/Views/profile.jsp");
            } else {
              alert("User or Password not registered, please register first or try again");
            }
          }
        ],
        error: [
          function (request, status) {
            alert(status);
          }
        ]
      });
    })
  });
</script>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container" id="navbar"></div>
</nav>

<div class="container">

  <div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
      <form role="form">
        <fieldset>
          <h2>Sign In</h2>
          <hr class="colorgraph">
          <div class="form-group">
            <input name="username" id="username" class="form-control input-lg" placeholder="Username">
          </div>
          <div class="form-group">
            <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password">
          </div>
          <hr class="colorgraph">
          <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6">
              <button id="signIn" type="button" class="btn btn-lg btn-success btn-block">Sign In</button>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6">
              <a href="" class="btn btn-lg btn-primary btn-block">Register</a>
            </div>
          </div>
        </fieldset>
      </form>
    </div>
  </div>

</div>
</body>
</html>
