package com.example.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebService {
    public String BorrarPerfil(String id) {
        String aux = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/crud_borrar.php");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setDoOutput(true);
            OutputStreamWriter datSal = new OutputStreamWriter(conexion.getOutputStream());
            String data = "id=" + URLEncoder.encode(id, "UTF-8");
            datSal.write(data);
            datSal.flush();
            datSal.close();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                while (linea != null) {
                    aux = aux + linea;//Concatenar datos linea por linea
                    linea = reader.readLine();//leer siguiente linea
                }
                reader.close();//Cerrar buffer de lectura
                if (aux.equals("2002")) {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                } else if (aux.equals("001")) {
                    aux = "Sin id para validar";
                } else if (aux.equals("000")) {
                    aux = "No se pudo mostrar la ID";
                } else if (aux.equals("002")) {
                    aux = "ID Borrada";
                }
            } else {
                aux = "ERROR al procesar servicio: " + conexion.getResponseCode();
            }
            conexion.disconnect();
        } catch (Exception ex) {
            aux = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return aux;
    }
    public String BuscarPerfil (String id) {
        String response = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/crud_buscar.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String data = "id=" + URLEncoder.encode(id, "UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            writer.close();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                     response += line;
                     line = reader.readLine();
                }
                if (response.equals("2002")) {
                    response = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                } else if (response.equals("001")) {
                    response = "Sin ID para validar";
                } else if (response.equals("000")) {
                    response = "No se pudo mostrar la ID";
                } else if (response.equals("010")) {
                    response = "No se encontraron resultados";
                }
                reader.close();
            } else {
                response = "ERROR al procesar el servicio: " + connection.getResponseCode();
            }
            connection.disconnect();
        } catch (Exception ex) {
            response = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return response;
    }
    public String InsertarPerfil (String id, String compania, String cantidad_tubos, String cantidad_personas, String tiempo_garantia, String telefono,
                                  String pagina_web, String correo_e, String direccion) {
        String response = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/crud_insertar.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Construye los datos a enviar en el cuerpo de la solicitud
            String data = "id=" + URLEncoder.encode(id, "UTF-8")
                    + "&compania=" + URLEncoder.encode(compania, "UTF-8")
                    + "&cantidad_tubos=" + URLEncoder.encode(cantidad_tubos, "UTF-8")
                    + "&cantidad_personas=" + URLEncoder.encode(cantidad_personas, "UTF-8")
                    + "&tiempo_garantia=" + URLEncoder.encode(tiempo_garantia, "UTF-8")
                    + "&telefono=" + URLEncoder.encode(telefono, "UTF-8")
                    + "&pagina_web=" + URLEncoder.encode(pagina_web, "UTF-8")
                    + "&correo_e=" + URLEncoder.encode(correo_e, "UTF-8")
                    + "&direccion=" + URLEncoder.encode(direccion, "UTF-8");


            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            writer.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
                if (response.equals("002")) {
                    response = "Usuario creado";
                } else if (response.equals("000")) {
                    response = "No se pudo crear el registro";
                } else {
                    response += line;
                }
                reader.close();
            } else {
                response = "ERROR al procesar el servicio: " + connection.getResponseCode();
            }

            connection.disconnect();
        } catch (Exception ex) {
            response = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return response;
    }
    public String ActualizarPerfil (String id, String compania, String cantidad_tubos, String cantidad_personas, String tiempo_garantia, String telefono,
                                    String pagina_web, String correo_e, String direccion){
        String response = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/crud_actualizar.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Construye los datos a enviar en el cuerpo de la solicitud
            String data = "id=" + URLEncoder.encode(id, "UTF-8")
                    + "&compania=" + URLEncoder.encode(compania, "UTF-8")
                    + "&cantidad_tubos=" + URLEncoder.encode(cantidad_tubos, "UTF-8")
                    + "&cantidad_personas=" + URLEncoder.encode(cantidad_personas, "UTF-8")
                    + "&tiempo_garantia=" + URLEncoder.encode(tiempo_garantia, "UTF-8")
                    + "&telefono=" + URLEncoder.encode(telefono, "UTF-8")
                    + "&pagina_web=" + URLEncoder.encode(pagina_web, "UTF-8")
                    + "&correo_e=" + URLEncoder.encode(correo_e, "UTF-8")
                    + "&direccion=" + URLEncoder.encode(direccion, "UTF-8");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            writer.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                    if (response.equals("002")) {
                        response = "Datos actualizados";
                    } else if (response.equals("000")) {
                        response = "No se pudieron actualizar la id";
                    }else {
                        response += line;
                    }
                }
                reader.close();
            } else {
                response = "ERROR al procesar el servicio: " + connection.getResponseCode();
            }

            connection.disconnect();
        } catch (IOException ex) {
            response = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return response;
    }
    public String buscarUno() {
        String aux = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/crud_buscar_RV.php");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setDoOutput(true);
            OutputStreamWriter datSal = new OutputStreamWriter(conexion.getOutputStream());
            String data = "id=" + URLEncoder.encode("1", "UTF-8");;
            datSal.write(data);
            datSal.flush();
            datSal.close();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                while (linea != null) {
                    aux = aux + linea; // Concatenar datos línea por línea
                    linea = reader.readLine(); // Leer siguiente línea
                }
                reader.close(); // Cerrar buffer de lectura
                if (aux.equals("2002")) {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                } else if (aux.equals("001")) {
                    aux = "Sin ID para validar";
                } else if (aux.equals("000")) {
                    aux = "No se pudo mostrar la ID";
                } else if (aux.equals("010")) {
                    aux = "No se encontraron resultados";
                }
            } else {
                aux = "ERROR al procesar servicio: " + conexion.getResponseCode();
            }
            conexion.disconnect();
        } catch (Exception ex) {
            aux = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return aux;
    }
    public String login(String correo, String contra) {
        String aux = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/login_soes.php");

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setDoOutput(true);
            OutputStreamWriter data1 = new OutputStreamWriter(conexion.getOutputStream());

            String data = "correo=" + URLEncoder.encode(correo, "UTF-8")
                    +  "&contra=" + URLEncoder.encode(contra, "UTF-8");

            data1.write(data);
            data1.flush();
            data1.close();

            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                while (linea != null) {
                    aux = aux + linea; // Concatenar datos línea por línea
                    linea = reader.readLine(); // Leer siguiente línea
                }
                reader.close(); // Cerrar buffer de lectura
                if (aux.equals("2002")) {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                } else if (aux.equals("001")) {
                    aux = "Correo sin validar";
                } else if (aux.equals("000")) {
                    aux = "No se pudo mostrar la contraseña";
                } else if (aux.equals("010")) {
                    aux = "El Usuario o Contraseña no existe";
                }
            } else {
                aux = "ERROR al procesar servicio: " + conexion.getResponseCode();
            }
            conexion.disconnect();
        } catch (Exception ex) {
            aux = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return aux;
    }
    public String registarUsuario (String correo, String contra) {
        String response = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/registro_soes.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Construye los datos a enviar en el cuerpo de la solicitud
            String data = "correo=" + URLEncoder.encode(correo, "UTF-8")
                    + "&contra=" + URLEncoder.encode(contra, "UTF-8");


            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            writer.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
                if (response.equals("002")) {
                    response = "Usuario creado";
                } else if (response.equals("000")) {
                    response = "No se pudo crear el registro";
                } else {
                    response += line;
                }
                reader.close();
            } else {
                response = "ERROR al procesar el servicio: " + connection.getResponseCode();
            }

            connection.disconnect();
        } catch (Exception ex) {
            response = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return response;
    }
    public String datosUsuarioCorreo (String correo) {
        String response = "";
        try {
            URL url = new URL("http://192.168.137.49:80/Conexion_SOES/datos_usuario.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Construye los datos a enviar en el cuerpo de la solicitud
            String data = "correo=" + URLEncoder.encode(correo, "UTF-8");


            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            writer.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
                if (response.equals("002")) {
                    response = "Usuario creado";
                } else if (response.equals("000")) {
                    response = "No se pudo crear el registro";
                } else {
                    response += line;
                }
                reader.close();
            } else {
                response = "ERROR al procesar el servicio: " + connection.getResponseCode();
            }

            connection.disconnect();
        } catch (Exception ex) {
            response = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return response;
    }
}
