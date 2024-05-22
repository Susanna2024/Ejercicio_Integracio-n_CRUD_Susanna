/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package esjava_sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List; // Importa List
import esjava_sql.MaterialDAO; // Importa MaterialDAO

public class EsJava_SQL {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Obtener la conexión a la base de datos
            connection = DBConnection.getConnection();

            // ----- Operaciones en Usuario -----
            System.out.println("// ----- Operaciones en Usuario -----");

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

            
            System.out.println();
            // ----- Operaciones en Curso -----
            System.out.println("// ----- Operaciones en Curso -----");

            // Crear una nueva instancia de CursoDAO pasando la conexión como parámetro
            CursoDAO cursoDAO = new CursoDAO(connection);

            // Eliminar las referencias del curso en las tablas relacionadas
            System.out.println("Eliminando las referencias del curso en las tablas relacionadas...");
            cursoDAO.eliminarReferenciasCurso("C105");

            // Eliminar el curso con el código 'C105'
            System.out.println("Eliminando el curso con código 'C105'...");
            cursoDAO.eliminarCurso("C105");

            
            System.out.println();
            // ----- Operaciones en Profesor -----
            System.out.println("// ----- Operaciones en Profesor -----");

            // Crear una instancia de ProfesorDAO pasando la conexión como parámetro
            ProfesorDAO profesorDAO = new ProfesorDAO(connection);
            
            // Insertar un nuevo profesor
            System.out.println("Insertando un nuevo profesor...");
            profesorDAO.insertarProfesor(11); // Cambia 1 al id de Usuario correspondiente
           
            // Actualizar el profesor
            System.out.println("Actualizando el profesor...");
            profesorDAO.actualizarProfesor(1, 2); // Cambia 1 y 2 a los ids correspondientes
            
            // Eliminar el profesor
            System.out.println("Eliminando el profesor...");
            profesorDAO.eliminarProfesor(1); // Cambia 1 al id de Profesor correspondiente
            
            
             System.out.println();
// ----- Operaciones en Material -----
System.out.println("// ----- Operaciones en Material -----");

// Crear una instancia de MaterialDAO pasando la conexión como parámetro
MaterialDAO materialDAO = new MaterialDAO(connection);

// Seleccionar y mostrar todos los materiales
System.out.println("Seleccionando y mostrando todos los materiales...");
List<Material> materiales = materialDAO.seleccionarTodosLosMateriales();
for (Material material : materiales) {
    System.out.println("ID Material: " + material.getIdMaterial());
    System.out.println("Nombre: " + material.getNombre());
    System.out.println("Teoría: " + material.getTeoria());
    System.out.println("Fecha: " + material.getFecha());
    System.out.println("-------------------");
}

//examen
            System.out.println();
            System.out.println("// ----- Operaciones en Examen -----");


    ExamenDAO examenDAO = new ExamenDAO(connection);

            // CREAR: Añadir un nuevo examen
            examenDAO.addExamen("2024-05-30", 88.0);

            // LEER: Obtener todos los exámenes
            List<Object[]> examenes = examenDAO.getAllExamenes();
            for (Object[] examen : examenes) {
                System.out.println("Fecha: " + examen[0] + ", ExamenCalificado: " + examen[1]);
            }

            // LEER: Obtener un examen por fecha
            Object[] examenByFecha = examenDAO.getExamenByFecha("2024-05-20");
            if (examenByFecha != null) {
                System.out.println("Fecha: " + examenByFecha[0] + ", ExamenCalificado: " + examenByFecha[1]);
            }

            // ACTUALIZAR: Actualizar un examen
            examenDAO.updateExamen("2024-05-30", 90.0);

            // ELIMINAR: Eliminar un examen
            examenDAO.deleteExamen("2024-05-30");

            //base de datos
            
            System.out.println();
            System.out.println("// ----- Operaciones en BDD-----");

             BaseDeDatosDAO baseDeDatosDAO = new BaseDeDatosDAO(connection);

            // Operaciones CRUD para BaseDatosBibliografica
            baseDeDatosDAO.addBaseDatosBibliografica("Nueva Base Biblio", "2024-05-30 09:00:00");
            List<Object[]> bibliograficas = baseDeDatosDAO.getAllBaseDatosBibliografica();
            for (Object[] base : bibliograficas) {
                System.out.println("Bibliografica - Nombre: " + base[0] + ", Fecha: " + base[1]);
            }
            baseDeDatosDAO.updateBaseDatosBibliografica("Nueva Base Biblio", "2024-06-01 09:00:00");
            baseDeDatosDAO.deleteBaseDatosBibliografica("Nueva Base Biblio");

            // Operaciones CRUD para BaseDatosElearning
            baseDeDatosDAO.addBaseDatosElearning("Nueva Base Elearning", "2024-05-30 09:00:00");
            List<Object[]> elearnings = baseDeDatosDAO.getAllBaseDatosElearning();
            for (Object[] base : elearnings) {
                System.out.println("Elearning - Nombre: " + base[0] + ", Fecha: " + base[1]);
            }
            baseDeDatosDAO.updateBaseDatosElearning("Nueva Base Elearning", "2024-06-01 09:00:00");
            baseDeDatosDAO.deleteBaseDatosElearning("Nueva Base Elearning");
            
            
            
            
            
            
            
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
