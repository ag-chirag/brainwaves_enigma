<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>QLess</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>

  <body>
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Enigma</a>
    </div>
  <div id="navbar" class="navbar-collapse collapse">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">FAQ</a></li>
    </ul>
  </div>
  </div>
  </nav>

  <div class="login-card">
    <h1>Log-in</h1><br>
    <form name="myForm" action="login.php" method="post">
      <input type="text" name="user" value="" placeholder="Username">
      <input type="password" name="pass" value="" placeholder="Password">
      <input type="submit" name="login" class="login login-submit" value="Sign in">
    </form>
  </div>

  </body>
</html>