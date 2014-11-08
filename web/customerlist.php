<?php
session_start();
?>
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
  	<h1>Customer List</h1><hr/><br>
  	<p>
  	<?php
  	$con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
	if (mysqli_connect_errno())
	{	
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
      die();
	}
    $_SESSION['person'] = $_GET['operation'];
  	$result = mysqli_query($con,"select p.pan, c.fname, c.lname, p.purpose, p.id from purpose as p, customer as c where p.category='$_GET[operation]' and p.pan = c.pan");
  	if(mysqli_num_rows($result) == 0)
  	{
    	echo "<center>No customer found!</center></br>";
  	}
  	else 
  	{
  		$i=1;
  		echo "<table class=\"table table-striped\">
      	<tr>
        	<th>Item</th>
        	<th>Account No.</th>
        	<th>Name</th>
        	<th>Purpose</th>
      	</tr>";
        while($row = mysqli_fetch_array($result))
        {
          echo '<tr>';
          echo '<td>'; echo $i. ". "; echo '</td>';
          echo '<td>'; echo "<a href=\"accountprofile.php?pan=".$row['pan']."&purpose=".$row['purpose']."&id=".$row['id']."\">".$row['pan']."</a>"; echo '</td>';
          echo '<td>'; echo $row['fname'] ." ". $row['lname'];echo '</td>';
          echo '<td>'; echo $row['purpose']; echo '</td>';
          echo '</tr>';
          $i++;
        }
        echo "</br>";
    }
    echo "</table>";
  	mysqli_close($con);
  	?>
  	</p>
  </div>

  </body>
 </html>