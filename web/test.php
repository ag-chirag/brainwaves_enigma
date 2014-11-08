<?php
  $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
  if (mysqli_connect_errno())
  { 
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  $result = mysqli_query($con,"SELECT * FROM customer");
  if(mysqli_num_rows($result) == 0)
  {
    echo "<center>No customer found!</center></br>";
  }
  else 
  {
    while($row = mysqli_fetch_array($result))
    {
      echo $row['pan'] . " " . $row['fname'] . " " . $row['lname'] . "<br/>";
    }
  }
  mysqli_close($con);
?>