<?php
$server     ="localhost";
$username   ="db_user";
$password   ="password";
$db         ="smarthome_db";

$conn = mysqli_connect($server, $username, $password, $db);
	mysqli_query($conn,'SET CHARACTER SET utf8'); 
mysqli_query($conn,"SET SESSION collation_connection ='utf8_general_ci'");

if(!$conn)
{
    die("connection failed: " . mysqli_connect_error());
}
?>