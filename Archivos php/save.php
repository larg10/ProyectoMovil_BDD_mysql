<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require_once("conexion_bdd.php");

        $nombre = $_POST['Nombre'];
        $apellido = $_POST['Apellido'];
        $edad = $_POST['Edad'];
        $genero = $_POST['Género'];
        $ID = $_POST['ID'];

        $query = "INSERT INTO app_movil (Nombre, Apellido, Edad, Género, ID) VALUES ('$nombre','$apellido','$edad','$genero','$ID')";
        $result = $mysql->query($query);

        if($result == TRUE){
            echo "Se agregaron los datos correctamente a la base de datos";
        }else{
            echo "ERROR";
        }

        $mysql->close();
    }
?>