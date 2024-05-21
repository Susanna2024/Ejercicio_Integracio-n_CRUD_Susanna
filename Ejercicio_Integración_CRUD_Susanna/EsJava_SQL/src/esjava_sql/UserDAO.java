/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esjava_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarUsuario(String nombre, String apellido, String correo, int telefono, String contrasena, String rol, boolean autentificacionDosFactores, String fechaCreacion) {
        try {
            String insertQuery = "INSERT INTO Usuario (nombre, apellido, correo, telefono, contrasena, rol, autentificacionDosFactores, fechaCreacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, correo);
            preparedStatement.setInt(4, telefono);
            preparedStatement.setString(5, contrasena);
            preparedStatement.setString(6, rol);
            preparedStatement.setBoolean(7, autentificacionDosFactores);
            preparedStatement.setString(8, fechaCreacion);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El nuevo usuario se ha insertado correctamente.");
                
                // Obtener el ID generado
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    System.out.println("ID generado: " + generatedId);
                }
            } else {
                System.out.println("No se pudo insertar el usuario.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCorreo(int id, String nuevoCorreo) {
        try {
            String updateQuery = "UPDATE Usuario SET correo = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, nuevoCorreo);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El correo electrónico del usuario ha sido actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún usuario con el ID especificado.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void seleccionarUsuario(int id) {
        try {
            String selectQuery = "SELECT * FROM Usuario WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Apellido: " + resultSet.getString("apellido"));
                System.out.println("Correo: " + resultSet.getString("correo"));
                System.out.println("Teléfono: " + resultSet.getInt("telefono"));
                System.out.println("Contraseña: " + resultSet.getString("contrasena"));
                System.out.println("Rol: " + resultSet.getString("rol"));
                System.out.println("Autentificación Dos Factores: " + resultSet.getBoolean("autentificacionDosFactores"));
                System.out.println("Fecha de Creación: " + resultSet.getString("fechaCreacion"));
            } else {
                System.out.println("No se encontró ningún usuario con el ID especificado.");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public List<String[]> getAllUsers() {
        List<String[]> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Usuario";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String correo = resultSet.getString("correo");
                int telefono = resultSet.getInt("telefono");
                String contrasena = resultSet.getString("contrasena");
                String rol = resultSet.getString("rol");
                boolean autentificacionDosFactores = resultSet.getBoolean("autentificacionDosFactores");
                String fechaCreacion = resultSet.getString("fechaCreacion");

                String[] user = {String.valueOf(id), nombre, apellido, correo, String.valueOf(telefono), contrasena, rol, String.valueOf(autentificacionDosFactores), fechaCreacion};
                userList.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}