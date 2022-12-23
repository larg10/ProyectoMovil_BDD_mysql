<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("conexion_bdd.php");
        $ID = $_POST['ID'];

        $query = "DELETE FROM app_movil WHERE ID = '$ID'";
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