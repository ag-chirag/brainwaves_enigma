<?php
  session_start();
  $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
  if (mysqli_connect_errno())
  { 
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
      die();
  }
  $result = mysqli_query($con,"SELECT * FROM `customer` WHERE `uname` = '$_GET[uname]' and `pswd` = '$_GET[pswd]'");
  $row = mysqli_fetch_array($result);
  if(!$row)
  {
    echo "Invalid Username or Password";
    die();
  }
  else
  {
       $arr = array('userId' => $row['pan'], 'Name' => $row['fname']);
      echo json_encode($arr);
    //echo $row['fname'] . "#" . $row['pan'];
  } 
  mysqli_close($con);
?>