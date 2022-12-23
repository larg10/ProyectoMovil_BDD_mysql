<?php

    $mysql = new mysqli("localhost", "root","","intento_movil");

    if($mysql->connect_error){
        die("Error al conectar" . $mysql->connect_error);
    }
    ?>