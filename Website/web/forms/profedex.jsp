<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link href="../css/profemonFeatures.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <title>Profedex</title>
</head>


<body>
<script>
  $( document ).ready(function() {
    $("#navbar").load("navbar.html");
  });
</script>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container" id="navbar"></div>
</nav>

  <div id="filterContainer">
    <form action="">
      <input type="text" name="filterby">
      <input type="submit" value="Filter" type="button" class="btn btn-primary active">
    </form>
  </div>

  <div id="profedexContainer">

    <div class="col-md-3 col-sm-6 hero-feature">
      <div class="thumbnail">
        <img src="/pics/Profedex/Blastoller.png" alt="">
        <div class="caption">
          <h4>#ID: Name</h4>
        </div>
      </div>
    </div>

  </div>
</body>
</html>
