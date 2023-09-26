<?php
if (!isset($_REQUEST['correo']) || !isset($_REQUEST['contra'])) {
    echo "Falta el correo o la contraseña";
} else {
    try {
        $mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");
        $correo = $_REQUEST['correo'];
        $contra = $_REQUEST['contra'];

        $result = $mysqli->query("SELECT * FROM usuarios_soes WHERE correo = '$correo' AND contra = '$contra'");
        if ($result->num_rows > 0) {
            $salida = array();
            while ($fila = $result->fetch_assoc()) {
                $salida[] = $fila;
            }
            print (json_encode($salida));
        } else {
            echo "010";
            // El error 010 significa que no hay ningún dato en la base de datos
        }
    } catch (mysqli_sql_exception $e) {
        // Captura la excepción y muestra un mensaje de error personalizado
        echo $e->getCode();
    }
    $mysqli->close();
}
?>






