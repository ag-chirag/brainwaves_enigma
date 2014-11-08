<?php
  	$con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
	if (mysqli_connect_errno())
	{	
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
    	die();
	}
  	$result = mysqli_query($con,"SELECT * FROM queue");
    $row = mysqli_fetch_array($result);
    echo $row[$_GET['cat']];
  	mysqli_close($con);
?>