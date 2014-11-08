<?php
  	$con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
	if (mysqli_connect_errno())
	{	
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
    	die();
	}
  	$result = mysqli_query($con,"SELECT * FROM waiting WHERE pan = $_GET[pan] ");
  	if(mysqli_num_rows($result) == 0)
  	{
    	echo "0";
    	die();
  	}
  	else 
  	{
  		while($row = mysqli_fetch_array($result))
    	{
      		$pan = $row['pan'];
      		$cat = $row['category'];
      		$purpose = $row['purpose'];
      		$bid = $row['bid'];
      		   	

      		$result1 = mysqli_query($con,"SELECT * FROM queue");
    		$row1 = mysqli_fetch_array($result1);    		
      		$val = $row1[$row['category']]+1;
      		//echo $val . " ab ";
      		mysqli_query($con,"INSERT INTO `purpose`( `pan`, `category`, `purpose`, `bid`) VALUES ($pan,'$cat','$purpose',$bid)"); 
    		mysqli_query($con,"UPDATE queue SET `$cat` = $val ");	
    	}    	    	
    	mysqli_query($con,"DELETE FROM waiting WHERE pan = $_GET[pan]");
  	}
  	mysqli_close($con);
  	echo "1";
?>