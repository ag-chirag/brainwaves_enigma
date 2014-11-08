<?php
  $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
	if (mysqli_connect_errno())
	{	
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
      die();
	}
  mysqli_query($con,"INSERT INTO `waiting`( `pan`, `category`, `purpose`, `bid`) VALUES ($_GET[pan],'$_GET[category]','$_GET[purpose]',$_GET[bid])");
  mysqli_close($con);
  echo "1";
?>