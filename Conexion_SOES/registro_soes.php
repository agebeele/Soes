<?php
if (
    !isset($_REQUEST['clave'])||
    !isset($_REQUEST['nombre'])||
    !isset($_REQUEST['apellido_paterno'])||
    !isset($_REQUEST['apellido_materno'])||
    !isset($_REQUEST['correo'])||
    !isset($_REQUEST['contra'])) {
            echo "Faltan datos para crear el registro";
        } else {
            try {
                $mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");
                $clave = $_REQUEST['clave'];
                $nombre = $_REQUEST['nombre'];
                $apellido_paterno = $_REQUEST['apellido_paterno'];
                $apellido_materno = $_REQUEST['apellido_materno'];
                $correo = $_REQUEST['correo'];
                $contra = $_REQUEST['contra'];

                $mysqli->query("INSERT INTO usuarios_soes (clave, nombre, apellido_paterno, apellido_materno, correo, contra)
                VALUES ('$clave','$nombre','$apellido_paterno','$apellido_materno','$correo', '$contra')");
                 if($mysqli->affected_rows>0)
                 {
                     echo "002";
                 }   
                 else
                 {   
                     echo "000"; 
                 }
             } catch (mysqli_sql_exception $e) {
                 echo $e->getCode();
             }
             $mysqli->close();
         }
         
         ?>