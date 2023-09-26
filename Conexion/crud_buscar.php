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
  if (!isset($_REQUEST['id'])) {
            echo "Falta el ID del registro a consultar";

}else{
	try {
        $mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");
        $id = $_REQUEST['id'];
            $result = $mysqli->query("SELECT * FROM tubos_solares WHERE id = '$id'");
            if ($result->num_rows > 0) {
                $rows = array();
                while ($row = $result->fetch_assoc()) {                       
                    $salida[]=$row;
                }
                print(json_encode($salida));                        
            } else {   
                echo "010"; 
            } 
        } catch (mysqli_sql_exception $e) {
            echo $e->getCode();
        }
        $mysqli->close(); 
    }                        
?>