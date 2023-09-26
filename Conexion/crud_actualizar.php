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
            !isset($_REQUEST['id'])|| 
            !isset($_REQUEST['compania']) || 
            !isset($_REQUEST['cantidad_tubos']) || 
            !isset($_REQUEST['cantidad_personas']) ||
            !isset($_REQUEST['tiempo_garantia']) || 
            !isset($_REQUEST['telefono'])||
            !isset($_REQUEST['pagina_web'])||
            !isset($_REQUEST['correo_e'])|| 
            !isset($_REQUEST['direccion'])){
    echo "Faltan datos para actualizar el registro";
} else {
    try {
    	$mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");
                $id = $_REQUEST['id'];
                $compania = $_REQUEST['compania'];
                $cantidad_tubos = $_REQUEST['cantidad_tubos'];
                $cantidad_personas = $_REQUEST['cantidad_personas'];
                $tiempo_garantia = $_REQUEST['tiempo_garantia'];
                $telefono = $_REQUEST['telefono']; 
                $pagina_web = $_REQUEST['pagina_web'];
                $correo_e = $_REQUEST['correo_e'];
                $direccion = $_REQUEST['direccion'];

       $mysqli->query("UPDATE tubos_solares SET 
       compania = '$compania', 
       cantidad_tubos = '$cantidad_tubos',
       cantidad_personas = '$cantidad_personas',
       tiempo_garantia = '$tiempo_garantia',
       telefono = '$telefono', 
       pagina_web = '$pagina_web', 
       correo_e = '$correo_e', 
       direccion = '$direccion' WHERE id = '".$_REQUEST['id']."' ");
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