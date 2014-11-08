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

  <div class="main-card">
    <h1>Customer Account Profile</h1><hr/><br>
    <p>
      <?php
      $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
      if (mysqli_connect_errno())
      { 
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
      }
      $result = mysqli_query($con,"SELECT * FROM customer WHERE pan = $_GET[pan] ");
      $row = mysqli_fetch_array($result);
      if(!$row)
      {
        echo "<center>No customer found!</center></br>";
      }
      else 
      {
        $time1 = time();
        echo "<p>";
        echo "<b>Account:</b> ".$row['pan']."<br/><br/>";
        echo "<b>Name:</b> ".$row['fname']. " " .$row['lname']."<br/><br/>";
        echo "<b>Date of Birth:</b> ".$row['dob']."<br/><br/>";
        echo "<b>Nationality:</b> ".$row['nationality']."<br/><br/>";
        echo "<b>Purpose:</b> ".$_GET['purpose'];
        echo "</p><br/>";
        echo "<div class=\"normal\">";
        echo "<button name=\"login\" class=\"login login-submit\" onclick=\"location.href = 'deletelist.php?id=".$_GET['id']."&t1=".$time1."';\">Done</button><br><br>";
        echo "</div>";
      }
      mysqli_close($con);
      ?>
    </p>
  </div>

  </body>
 </html>