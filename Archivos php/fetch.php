<?php

    if($_SERVER['REQUEST_METHOD'] == 'GET'){
        require_once("conexion_bdd.php");

        $ID = $_GET['ID'];

        $query = "SELECT * FROM app_movil WHERE ID = '$ID'";
        $result = $mysql->query($query);

        if($mysql->affected_rows > 0){
            while($row = $result->fetch_assoc()){
                $array = $row;
            }
            echo json_encode($array);
        }else{
            echo "no se encontró";
        }

        $result->close();
        $mysql->close();
    }