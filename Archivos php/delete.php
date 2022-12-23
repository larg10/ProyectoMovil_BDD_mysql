<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("conexion_bdd.php");
        $Nombre = $_POST['Nombre'];
        $Apellido = $_POST['Apellido'];

        $query = "DELETE FROM app_movil WHERE Nombre = '$Nombre' && Apellido = '$Apellido'";
        $result = $mysql->query($query);

        if($mysql->affected_rows > 0){
            if($result === TRUE){
                echo "El usuario fue eliminado";
            }
        }else{
            echo "No se encontró el resultado";
        }

        $mysql->close();
    }