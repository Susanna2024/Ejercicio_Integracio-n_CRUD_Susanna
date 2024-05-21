package esjava_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarCurso(String nombre, String codigo, int aula) {
        try {
            String insertQuery = "INSERT INTO Curso (nombre, codigo, aula) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, nombre);
            statement.setString(2, codigo);
            statement.setInt(3, aula);
            statement.executeUpdate();
            statement.close();
            System.out.println("El nuevo curso se ha insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCurso(int id, String nuevoNombre) {
        try {
            String updateQuery = "UPDATE Curso SET nombre = ? WHERE id_curso = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, nuevoNombre);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            System.out.println("El nombre del curso ha sido actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void seleccionarCurso(int id) {
        try {
            String selectQuery = "SELECT * FROM Curso WHERE id_curso = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id_curso"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Código: " + resultSet.getString("codigo"));
                System.out.println("Aula: " + resultSet.getInt("aula"));
            } else {
                System.out.println("No se encontró ningún curso con el ID especificado.");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarReferenciasCurso(String codigoCurso) {
        try {
            String deleteReferenciasQuery = "DELETE FROM curso_basedatosbibliografica WHERE id_curso = (SELECT id_curso FROM curso WHERE codigo = ?)";
            PreparedStatement deleteReferenciasStatement = connection.prepareStatement(deleteReferenciasQuery);
            deleteReferenciasStatement.setString(1, codigoCurso);
            int rowsAffected = deleteReferenciasStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Las referencias del curso en la tabla curso_basedatosbibliografica han sido eliminadas exitosamente.");
            } else {
                System.out.println("No se encontraron referencias del curso en la tabla curso_basedatosbibliografica.");
            }
            deleteReferenciasStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCurso(String codigoCurso) {
        try {
            String deleteCursoQuery = "DELETE FROM Curso WHERE codigo = ?";
            PreparedStatement deleteCursoStatement = connection.prepareStatement(deleteCursoQuery);
            deleteCursoStatement.setString(1, codigoCurso);
            int rowsAffected = deleteCursoStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El curso con código '" + codigoCurso + "' ha sido eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún curso con código '" + codigoCurso + "'.");
            }
            deleteCursoStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getAllCursos() {
        List<String[]> cursoList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Curso";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_curso");
                String nombre = resultSet.getString("nombre");
                String codigo = resultSet.getString("codigo");
                int aula = resultSet.getInt("aula");

                String[] curso = {String.valueOf(id), nombre, codigo, String.valueOf(aula)};
                cursoList.add(curso);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursoList;
    }
}
