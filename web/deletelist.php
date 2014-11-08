<?php
    session_start();
    $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
    if (mysqli_connect_errno())
    { 
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    $result = mysqli_query($con,"SELECT * FROM purpose WHERE id = $_GET[id] ");
    $row = mysqli_fetch_array($result);
    if(!$row)
    {
        echo "<center>Nothing found!</center></br>";
    }
    else
    {
        $time1 = $_GET['t1'];
        $time2 = time();
        $tdiff = round(abs($time2 - $time1) / 60,2);
        echo $time1 . " " . $time2 . " " . $tdiff."<br/>";

        $result2 = mysqli_query($con,"SELECT * FROM customertime");
        $row2 = mysqli_fetch_array($result2);
        $val = $row2[$_SESSION['person']] + $tdiff;
        $cat = $_SESSION['person'];
        $_SESSION[$cat]++;
        echo $_SESSION[$cat];
        //echo $val . " abc ";
        //echo $cat . "<br/>";
        mysqli_query($con,"UPDATE customertime SET `$cat` = $val WHERE 1 ");
        echo $_SESSION['person'];

    	$result1 = mysqli_query($con,"SELECT * FROM queue");
    	$row1 = mysqli_fetch_array($result1);
    	//echo $row1[$row['category']];
    	$val = $row1[$row['category']]-1;
    	$cat = $row['category'];
    	//echo $val;
    	//echo $cat;
    	mysqli_query($con,"UPDATE queue SET `$cat` = $val ");
    	mysqli_query($con,"DELETE FROM purpose WHERE id = $_GET[id]");
    	header('Location: home.php');
    }
?>