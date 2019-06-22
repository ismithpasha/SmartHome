<?php
include('service/functions.php');
include('service/connection.php');

if(isset($_GET['fcmtoken']))
{
    date_default_timezone_set('Asia/Dhaka');
    $fcmtoken = $_GET['fcmtoken'];
    $ReqTime = date("Y-m-d h:i:sa");  
    $query = "INSERT INTO fcmtokens VALUES('null','".$fcmtoken."','".$ReqTime."')";
    
    $result = mysqli_query($conn,$query);
    
    if($result>0)
    {
        echo "Y";
    }
    else
    {
        echo "N";
    }
}

?>