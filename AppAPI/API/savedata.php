<?php
include('service/functions.php');
include('service/connection.php');

if(isset($_POST['device_id']))
{
    date_default_timezone_set('Asia/Dhaka');
    $fcmtoken = $_GET['fcmtoken'];
    $ReqTime = date("Y-m-d h:i:sa");  
    $query = "INSERT INTO `arduinodata` VALUES (NULL, '".$_POST['device_id']."', '".$_POST['device_imei']."', '".$_POST['time']."', '".$_POST['device_timezone']."', '".$_POST['latitude']."', '".$_POST['longitude']."')";
   
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