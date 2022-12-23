<?php

    $mysql = new mysqli("localhost", "id20053357_larg10","Larg109080??","id20053357_intento_movil");

    if($mysql->connect_error){
        die("Error al conectar" . $mysql->connect_error);
    }
    ?>