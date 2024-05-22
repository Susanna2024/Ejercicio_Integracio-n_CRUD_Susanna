/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esjava_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForoDAO {
    private Connection connection;

    public ForoDAO(Connection connection) {
        this.connection = connection;
    }

    public void modificarForo(int idForo, String nuevoContenido) {
        try {
            String updateQuery = "UPDATE Foro SET contenido = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, nuevoContenido);
            preparedStatement.setInt(2, idForo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El contenido del foro ha sido modificado con éxito.");
            } else {
                System.out.println("No se encontró ningún foro con el ID especificado.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarTodoElForo() {
        try {
            String selectQuery = "SELECT * FROM Foro";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idForo = resultSet.getInt("id");
                String autorId = resultSet.getString("autor_id");
                String contenido = resultSet.getString("contenido");
                String fechaPublicacion = resultSet.getString("fecha_publicacion");
                System.out.println("ID Foro: " + idForo);
                System.out.println("Autor ID: " + autorId);
                System.out.println("Contenido: " + contenido);
                System.out.println("Fecha Publicación: " + fechaPublicacion);
                System.out.println("-------------------");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

