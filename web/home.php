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
      <li><a href="logout.php">sign out</a></li>
    </ul>
  </div>
  </div>
  </nav>

  <div class="login-card">
    <button name="login" class="login login-submit" onclick="location.href = 'customerlist.php?operation=account';">Account Operation</button><br><br>
    <button name="login" class="login login-submit" onclick="location.href = 'customerlist.php?operation=transaction';">Transaction</button><br><br>
    <button name="login" class="login login-submit" onclick="location.href = 'customerlist.php?operation=loan';">Loan</button><br><br>
    <button name="login" class="login login-submit" onclick="location.href = 'customerlist.php?operation=others';">Others</button><br><br>
  </div>

  </body>
</html>