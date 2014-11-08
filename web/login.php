<?php
  session_start();
  $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
	if (mysqli_connect_errno())
	{	
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
      die();
	}
  $result = mysqli_query($con,"SELECT * FROM employee WHERE uname='$_POST[user]' AND pswd='$_POST[pass]'");
	$row = mysqli_fetch_array($result);
  if(!$row)
  {
  	echo "Invalid Username or Password! ";
  	echo "<a href=\"qless.php\"> Click here!!";
  	die();
  }
  else
  {
    $_SESSION['name']=$row['uname'];
    $_SESSION['id']=$row['id'];
    $_SESSION['account']=1;
    $_SESSION['transaction']=1;
    $_SESSION['loan']=1;
    $_SESSION['others']=1;
  }	
  mysqli_close($con);
  header('Location: home.php');
?>