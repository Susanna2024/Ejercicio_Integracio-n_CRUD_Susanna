package esjava_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection connection;

    public MaterialDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void insertarMaterial(String nombre, String teoria, String fecha) {
        try {
            String insertQuery = "INSERT INTO Material (nombre, teoria, fecha) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, teoria);
            preparedStatement.setString(3, fecha);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Material insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el material.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Material> seleccionarTodosLosMateriales() {
        List<Material> materiales = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM Material";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idMaterial = resultSet.getInt("Id_material");
                String nombre = resultSet.getString("nombre");
                String teoria = resultSet.getString("teoria");
                String fecha = resultSet.getString("fecha");
                Material material = new Material(idMaterial, nombre, teoria, fecha);
                materiales.add(material);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    // UPDATE
    public void actualizarMaterial(int idMaterial, String nombre, String teoria, String fecha) {
        try {
            String updateQuery = "UPDATE Material SET nombre = ?, teoria = ?, fecha = ? WHERE Id_material = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, teoria);
            preparedStatement.setString(3, fecha);
            preparedStatement.setInt(4, idMaterial);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Material actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún material con el ID especificado.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void eliminarMaterial(int idMaterial) {
        try {
            String deleteQuery = "DELETE FROM Material WHERE Id_material = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idMaterial);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Material eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún material con el ID especificado.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
