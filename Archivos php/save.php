<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("conexion_bdd.php");

        $nombre = $_POST['nombre'];
        $apellido = $_POST['apellido'];
        $edad = $_POST['edad'];
        $genero = $_POST['genero'];

        $query = "INSERT INTO app_movil (Nombre, Apellido, Edad, Género) VALUES ('$nombre','$apellido','$edad','$genero')";
        $result = $mysql->query($query);

        if($result == TRUE){
            echo "Se agregaron los datos correctamente a la base de datos";
        }else{
            echo "ERROR";
        }

        $mysql->close();
    }
?>