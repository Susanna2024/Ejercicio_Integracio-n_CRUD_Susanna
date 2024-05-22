/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esjava_sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamenDAO {
    private Connection connection;

    public ExamenDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear (Create)
    public void addExamen(String fecha, double examenCalificado) throws SQLException {
        String query = "INSERT INTO EXAMEN (Fecha, ExamenCalificado) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fecha);
            stmt.setDouble(2, examenCalificado);
            stmt.executeUpdate();
        }
    }

    // Leer todos los exámenes (Read All)
    public List<Object[]> getAllExamenes() throws SQLException {
        List<Object[]> examenes = new ArrayList<>();
        String query = "SELECT * FROM EXAMEN";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String fecha = rs.getString("Fecha");
                double examenCalificado = rs.getDouble("ExamenCalificado");
                examenes.add(new Object[]{fecha, examenCalificado});
            }
        }
        return examenes;
    }

  // Leer un examen por fecha (Read by Fecha)
    public Object[] getExamenByFecha(String fecha) throws SQLException {
        Object[] examen = null;
        String query = "SELECT * FROM EXAMEN WHERE Fecha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fecha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double examenCalificado = rs.getDouble("ExamenCalificado");
                    examen = new Object[]{fecha, examenCalificado};
                }
            }
        }
        return examen;
    }

    // Actualizar (Update)
    public void updateExamen(String fecha, double examenCalificado) throws SQLException {
        String query = "UPDATE EXAMEN SET ExamenCalificado = ? WHERE Fecha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, examenCalificado);
            stmt.setString(2, fecha);
            stmt.executeUpdate();
        }
    }

    // Eliminar (Delete)
    public void deleteExamen(String fecha) throws SQLException {
        String query = "DELETE FROM EXAMEN WHERE Fecha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fecha);
            stmt.executeUpdate();
        }
    }
}