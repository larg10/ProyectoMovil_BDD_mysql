<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("conexion_bdd.php");

        $Nombre = $_POST['Nombre'];
        $Apellido = $_POST['Apellido'];
        $Edad = $_POST['Edad'];
        $Genero = $_POST['Género'];
        $ID = $_POST['ID'];

        $query = "UPDATE app_movil SET Nombre = '$Nombre', Apellido = '$Apellido', Edad = '$Edad', Género = '$Genero' WHERE ID = '$ID'";
        $result = $mysql->query($query);

        if($mysql->affected_rows > 0){
            if($result === TRUE){
                echo "Datos actualizados";
            }else{
                echo "Error";
            }
        }else{
            echo "No se pudo actualizar";
        }

        $mysql->close();
    }