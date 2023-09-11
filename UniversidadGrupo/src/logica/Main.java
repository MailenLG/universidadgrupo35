
//Import Alumno
import entidades.Alumno;

import java.time.LocalDate;
import java.time.Month;

//Import Materia
import entidades.Materia;

//Import Conexiones
import accesoadatos.Conexion;
import accesoadatos.AlumnoData;
import accesoadatos.MateriaData;
import java.sql.*;


public class Main {

    public static void main(String[] args) {

        Connection con = Conexion.getConexion();
        
        
        //Alumno CRUD
       //Alumno juan =new Alumno(12312312, "Lunass", "Pedro", LocalDate.of(1980, Month.APRIL, 25), true);//aca solo se guarda en la RAM
        AlumnoData aludata= new AlumnoData();//aca es para guardarlo en la base de datos
       Alumno juan =new Alumno(1,25684752, "Lunssass", "Juan Pedro", LocalDate.of(1980, Month.APRIL, 25),true);//aca solo se guarda en la RAM
//          AlumnoData alu = new AlumnoData();//aca es para guardarlo en la base de datos
       aludata.modificarAlumno(juan);
////        alu.eliminarAlumno(1);
////        alu.buscarAlumnoPorDni(12312312);+
//          for(Alumno alumno:alu.listarAlumnos()){
//              System.out.println(alumno.getDni());   
//              System.out.println(alumno.getApellido());   
//              System.out.println(alumno.getNombre());   
//              System.out.println(alumno.getFechaNacimiento());   
//          }
          
        //Materia CRUD
        Materia mat = new Materia("Matematica I", 1, true);//aca solo se guarda en la RAM
        MateriaData matdata = new MateriaData();//aca es para guardarlo en la base de datos
        Materia matmod = new Materia(1, "Matematicassss I", 1, true);//aca solo se guarda en la RAM
        
        for (Materia materia : matdata.listarMateria(1)) {
            System.out.println("Id de la Materia : " + materia.getIdMateria());
            System.out.println("Nombre de la Materia : " + materia.getNombre());
            System.out.println("Año Cursada: " + materia.getAnio());
            System.out.println("Estado Cursada: " + materia.isEstado());
        }
        System.out.println("Modificoooo..");
        matdata.modificarMateria(matmod);
        // matdata.guardarMateria(mat);

        //matdata.eliminarMateria(1);
        // matdata.buscarMateria(1);
        
        //Listado de materias por estado 1= activo 0= eliminado
        for (Materia materia : matdata.listarMateria(1)) {
            System.out.println("Id de la Materia : " + materia.getIdMateria());
            System.out.println("Nombre de la Materia : " + materia.getNombre());
            System.out.println("Año Cursada: " + materia.getAnio());
            System.out.println("Estado Cursada: " + materia.isEstado());
        }


    }

}
