<?php
// Verificar si se recibió el correo electrónico del usuario
if (!isset($_REQUEST['correo'])) {
    echo json_encode(array("success" => false, "message" => "Falta el correo electrónico del usuario a consultar"));
    exit();
}

// Obtener el correo electrónico del usuario enviado desde la app
$correo = $_REQUEST['correo'];

try {
    $mysqli = new mysqli("localhost", "root", "", "soes_bd", "3306");

    // Verificar la conexión a la base de datos
    if ($mysqli->connect_errno) {
        echo json_encode(array("success" => false, "message" => "Error de conexión: " . $mysqli->connect_error));
        exit();
    }

    // Utilizar consultas preparadas para mayor seguridad
    $stmt = $mysqli->prepare("SELECT * FROM usuarios_soes WHERE correo = ?");
    $stmt->bind_param("s", $correo);
    $stmt->execute();

    // Obtener resultados
    $result = $stmt->get_result();

    // Verificar si se encontraron resultados
    if ($result->num_rows > 0) {
        $rows = array();
        while ($row = $result->fetch_assoc()) {
            $rows[] = $row;
        }
        echo json_encode(array("success" => true, "data" => $rows));
    } else {
        echo json_encode(array("success" => false, "message" => "No se encontraron resultados"));
    }

    $stmt->close();
    $mysqli->close();
} catch (mysqli_sql_exception $e) {
    echo json_encode(array("success" => false, "message" => "Error al procesar la consulta: " . $e->getMessage()));
}    
?>