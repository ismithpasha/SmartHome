<?php
    include('service/functions.php');
    include('service/connection.php');
    header('Content-type: application/json; charset=utf-8');

       $query = "SELECT * FROM arduinodata";
        $result = mysqli_query($conn, $query);
       $json_object = array(); 
          if (mysqli_num_rows($result) > 0) {
            while ($row = mysqli_fetch_assoc($result)) {
           
                $json_object[] = $row;
            }
            echo  json_encode(array($json_object), JSON_UNESCAPED_UNICODE); 
        }
        else{
            echo "No Data Found";
        }
?>

