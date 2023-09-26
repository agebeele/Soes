<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "soes_bd";
$puerto = "3306";

// Crear una conexión
$conn = new mysqli($servername, $username, $password, $database, $puerto);

// Verificar la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

echo "Conexión exitosa a la base de datos.";

// Realizar operaciones en la base de datos aquí...

// Cerrar la conexión cuando hayas terminado
$conn->close();
?>