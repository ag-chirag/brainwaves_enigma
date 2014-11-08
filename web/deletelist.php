<?php
    $con=mysqli_connect("sql4.freemysqlhosting.net","sql457377","iH2%uV8*","sql457377");
    if (mysqli_connect_errno())
    { 
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    mysqli_query($con,"DELETE * FROM purpose WHERE id = $_GET[id] ");
?>