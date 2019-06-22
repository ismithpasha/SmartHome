<?php
	include('service/functions.php');
    include('service/connection.php');

if(isset($_GET['Name']))
{
    $Name = $_GET['Name'];
    $Email = $_GET['Email'];    
    $Password = $_GET['Password'];
    $Mobile = $_GET['Phone'];
    
         $vquery = "SELECT * FROM users  where email='".$Email."' or phone='".$Mobile."'";
          $vresult = mysqli_query($conn,$vquery);

            if (mysqli_num_rows($vresult ) >0 ) {

                echo "EmailPhoneExist";
            }
            else
            {
             $hPassword = password_hash($Password,PASSWORD_DEFAULT);

                   $query = "INSERT INTO users VALUES('null','".$Name."','".$Email."','".$hPassword."','".$Mobile."' )";

                    $result = mysqli_query($conn,$query);

                    if($result>0)
                    {
                       echo "success";
                    }
                    else
                    {
                         echo "SignUpFailed";
                    } 
                }
            }

?>