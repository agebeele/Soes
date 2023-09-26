<?php
/*
Codigos de error de sistema
    1045 > usr/pass
    1062 > PK duplicada
    1136 > colum count
    
    Estos errores no se deben validad en cte, se debe corregir el WS
    2002 > ip/pto
    1064 > You have an error...
    1146 > Tabla
    1049 > BD

    Codigos de validaci贸n propios
    001 > Datos faltantes
    002 > Consul OK
    010 > no hay conicidencias
*/
if (
    !isset($_REQUEST['correo'])||
    !isset($_REQUEST['contra'])) {
            echo "Faltan datos para crear el registro";
        } else {
            try {
                $mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");
                $correo = $_REQUEST['correo'];
                $contra = $_REQUEST['contra'];

                $mysqli->query("INSERT INTO usuarios_soes (correo,contra)
                VALUES ('$correo', '$contra')");
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