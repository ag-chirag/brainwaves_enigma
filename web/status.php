<?php
  session_start();
  $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
	if (mysqli_connect_errno())
	{	
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
    	die();
	}
  	$result = mysqli_query($con,"SELECT * FROM queue");
    $row = mysqli_fetch_array($result);
    $result1 = mysqli_query($con,"SELECT * FROM customertime");
    $row1 = mysqli_fetch_array($result1);
    $a1 = $row1['account']/$_SESSION['account'];
    $a2 = $row1['transaction']/$_SESSION['transaction']; 
    $a3 = $row1['loan']/$_SESSION['loan'];
    $a4 = $row1['others']/$_SESSION['others']; 
    echo $row['account'] . "#" . $a1 ."#". $row['transaction'] . "#" . $a2 ."#" . $row['loan'] . "#" . $a3 ."#" . $row['others'] . "#" . $a4;
  	mysqli_close($con);
?>