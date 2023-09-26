<?php
try {
    $mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");
    
    $datos = $mysqli->query("SELECT * FROM tubos_solares");
    if ($datos->num_rows > 0) {
        $datos->data_seek(0);
        while ($fila = $datos->fetch_assoc()) {
            $salida[] = $fila;
        }
        print(json_encode($salida));
    } else {
        echo "010";
    }
} catch (mysqli_sql_exception $e) {
    echo $e->getCode();
}

$mysqli->close();
?>