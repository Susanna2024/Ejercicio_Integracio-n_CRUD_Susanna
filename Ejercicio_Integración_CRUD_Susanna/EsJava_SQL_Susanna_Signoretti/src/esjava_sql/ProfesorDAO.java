/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esjava_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {
    private Connection connection;

    public ProfesorDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarProfesor(int usuarioId) {
        try {
            String insertQuery = "INSERT INTO Profesor (usuario_id) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, usuarioId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El nuevo profesor se ha insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el profesor.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProfesor(int idProfesor, int nuevoUsuarioId) {
        try {
            String updateQuery = "UPDATE Profesor SET usuario_id = ? WHERE id_profesor = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, nuevoUsuarioId);
            preparedStatement.setInt(2, idProfesor);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El profesor ha sido actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún profesor con el ID especificado.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProfesor(int idProfesor) {
        try {
            String deleteQuery = "DELETE FROM Profesor WHERE id_profesor = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idProfesor);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El profesor ha sido eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún profesor con el ID especificado.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

