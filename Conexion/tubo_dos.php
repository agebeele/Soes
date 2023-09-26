<?php
// Conexión a la base de datos MySQL
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "soes_bd";

$conn = new mysqli($servername, $username, $password, $dbname);

// Consulta SQL para obtener los datos de la gráfica
$sql = "SELECT fecha_registro1, x_value1, y_value1 FROM tubodos_soes";
$result = $conn->query($sql);

// Crear un arreglo para almacenar los datos
$data = array();

// Recorrer los resultados de la consulta y agregarlos al arreglo
while ($row = $result->fetch_assoc()) {
    $data[] = $row;
}

// Devolver los datos en formato JSON
header('Content-Type: application/json');
echo json_encode($data);

// Cerrar la conexión a la base de datos
$conn->close();
?>