/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package esjava_sql;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Susanna
 */

public class EsJava_SQL {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Obtener la conexión a la base de datos
            connection = DBConnection.getConnection();

            // Crear una instancia de UserDAO pasando la conexión como parámetro
            UserDAO userDAO = new UserDAO(connection);

            // Insertar un nuevo usuario
            System.out.println("Insertando un nuevo usuario...");
            userDAO.insertarUsuario("Susanna", "Signoretti", "susie@example.com", 123456789, "nuevapassword", "User", false, "2024-05-21 10:00:00");

            // Actualizar el correo electrónico del usuario
            System.out.println("Actualizando el correo electrónico del usuario...");
            userDAO.actualizarCorreo(31, "susanna_updated@example.com");

            // Seleccionar y mostrar los datos del usuario
            System.out.println("Seleccionando y mostrando los datos del usuario...");
            userDAO.seleccionarUsuario(31);

            // Crear una nueva instancia de CursoDAO pasando la conexión como parámetro
            CursoDAO cursoDAO = new CursoDAO(connection);

            // Eliminar las referencias del curso en las tablas relacionadas
            System.out.println("Eliminando las referencias del curso en las tablas relacionadas...");
            cursoDAO.eliminarReferenciasCurso("C105");

            // Eliminar el curso con el código 'C105'
            System.out.println("Eliminando el curso con código 'C105'...");
            cursoDAO.eliminarCurso("C105");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión al finalizar
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
