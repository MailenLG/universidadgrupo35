package logica;



import accesoadatos.AlumnoData;
import accesoadatos.Conexion;
import entidades.Alumno;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        Connection con = Conexion.getConexion();
        Alumno juan =new Alumno(12312312, "Luna", "Pedro", LocalDate.of(1980, Month.APRIL, 25), true);//aca solo se guarda en la RAM
//        AlumnoData alu= new AlumnoData();//aca es para guardarlo en la base de datos
//        Alumno juan =new Alumno(1,12312312, "Luna", "Juan Pedro", LocalDate.of(1980, Month.APRIL, 25),true);//aca solo se guarda en la RAM
          AlumnoData alu = new AlumnoData();//aca es para guardarlo en la base de datos
//        alu.modificarAlumno(juan);
//        alu.eliminarAlumno(1);
//        alu.buscarAlumnoPorDni(12312312);
          for(Alumno alumno:alu.listarAlumnos()){
              System.out.println(alumno.getDni());   
              System.out.println(alumno.getApellido());   
              System.out.println(alumno.getNombre());   
              System.out.println(alumno.getFechaNacimiento());   
          }
    }

}
