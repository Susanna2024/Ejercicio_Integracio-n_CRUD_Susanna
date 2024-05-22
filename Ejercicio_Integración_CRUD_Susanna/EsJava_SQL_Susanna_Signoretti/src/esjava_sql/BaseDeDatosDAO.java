/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esjava_sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDatosDAO {
    private Connection connection;

    public BaseDeDatosDAO(Connection connection) {
        this.connection = connection;
    }

    // Métodos CRUD para BaseDatosBibliografica

    // Crear (Create)
    public void addBaseDatosBibliografica(String nombre, String fechaCreacion) throws SQLException {
        String query = "INSERT INTO BaseDatosBibliografica (base_datos_biblio, fechaCreacion) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, fechaCreacion);
            stmt.executeUpdate();
        }
    }

    // Leer todos (Read All)
    public List<Object[]> getAllBaseDatosBibliografica() throws SQLException {
        List<Object[]> basesDatos = new ArrayList<>();
        String query = "SELECT * FROM BaseDatosBibliografica";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nombre = rs.getString("base_datos_biblio");
                String fechaCreacion = rs.getString("fechaCreacion");
                basesDatos.add(new Object[]{nombre, fechaCreacion});
            }
        }
        return basesDatos;
    }

    // Leer por nombre (Read by Name)
    public Object[] getBaseDatosBibliograficaByName(String nombre) throws SQLException {
        Object[] baseDatos = null;
        String query = "SELECT * FROM BaseDatosBibliografica WHERE base_datos_biblio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String fechaCreacion = rs.getString("fechaCreacion");
                    baseDatos = new Object[]{nombre, fechaCreacion};
                }
            }
        }
        return baseDatos;
    }

    // Actualizar (Update)
    public void updateBaseDatosBibliografica(String nombre, String fechaCreacion) throws SQLException {
        String query = "UPDATE BaseDatosBibliografica SET fechaCreacion = ? WHERE base_datos_biblio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fechaCreacion);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        }
    }

    // Eliminar (Delete)
    public void deleteBaseDatosBibliografica(String nombre) throws SQLException {
        String query = "DELETE FROM BaseDatosBibliografica WHERE base_datos_biblio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        }
    }

    // Métodos CRUD para BaseDatosElearning

    // Crear (Create)
    public void addBaseDatosElearning(String nombre, String fechaCreacion) throws SQLException {
        String query = "INSERT INTO BaseDatosElearning (base_datos_elearning, fechaCreacion) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, fechaCreacion);
            stmt.executeUpdate();
        }
    }

    // Leer todos (Read All)
    public List<Object[]> getAllBaseDatosElearning() throws SQLException {
        List<Object[]> basesDatos = new ArrayList<>();
        String query = "SELECT * FROM BaseDatosElearning";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nombre = rs.getString("base_datos_elearning");
                String fechaCreacion = rs.getString("fechaCreacion");
                basesDatos.add(new Object[]{nombre, fechaCreacion});
            }
        }
        return basesDatos;
    }

    // Leer por nombre (Read by Name)
    public Object[] getBaseDatosElearningByName(String nombre) throws SQLException {
        Object[] baseDatos = null;
        String query = "SELECT * FROM BaseDatosElearning WHERE base_datos_elearning = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String fechaCreacion = rs.getString("fechaCreacion");
                    baseDatos = new Object[]{nombre, fechaCreacion};
                }
            }
        }
        return baseDatos;
    }

    // Actualizar (Update)
    public void updateBaseDatosElearning(String nombre, String fechaCreacion) throws SQLException {
        String query = "UPDATE BaseDatosElearning SET fechaCreacion = ? WHERE base_datos_elearning = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fechaCreacion);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        }
    }

    // Eliminar (Delete)
    public void deleteBaseDatosElearning(String nombre) throws SQLException {
        String query = "DELETE FROM BaseDatosElearning WHERE base_datos_elearning = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        }
    }
}

