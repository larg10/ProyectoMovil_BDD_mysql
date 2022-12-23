<?php

    if($_SERVER['REQUEST_METHOD'] == 'GET'){
        require_once("conexion_bdd.php");

        $Nombre = $_GET['Nombre'];
        $Apellido = $_GET['Apellido'];

        $query = "SELECT * FROM app_movil WHERE Nombre = '$Nombre' && Apellido = '$Apellido'";
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