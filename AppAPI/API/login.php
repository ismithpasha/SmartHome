<?php
    include('service/functions.php');
    include('service/connection.php');
  header('Content-type: application/json; charset=utf-8');
    $formEmail=$formPass=$loginError="";

    if(isset($_GET['email'])) {
        
        $formEmail=validateFormData($_GET['email']);
       $formPass =validateFormData($_GET['password']);
        
             $query = "SELECT * FROM users where email='".$formEmail."'";
            $result = mysqli_query($conn,$query);
                 
        if (mysqli_num_rows( $result ) >0 ) {
            while( $row_am = mysqli_fetch_assoc($result) ) {
                        
                        $hashedPass =$row_am['password'];
                    }
             if (password_verify($formPass,$hashedPass)) {
                  
                 echo "success";
             }
            else{
                 echo "wrongpassword";
                 
            }
            
        }
    
        else {
            echo "invaliduser";
        }
        
    }

?>
