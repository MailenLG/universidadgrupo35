
import accesoadatos.AlumnoData;
import accesoadatos.Conexion;
import entidades.Alumno;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;


public class Main {

    public static void main(String[] args) {
    
          Connection con=Conexion.getConexion();
//        Alumno juan =new Alumno(12312312, "Luna", "Pedro", LocalDate.of(1980, Month.APRIL, 25), true);//aca solo se guarda en la RAM
//        AlumnoData alu= new AlumnoData();//aca es para guardarlo en la base de datos
//        alu.guardarAlumno(juan);

          Alumno juan =new Alumno(1,12312312, "Luna", "Juan Pedro", LocalDate.of(1980, Month.APRIL, 25), true);//aca solo se guarda en la RAM
          AlumnoData alu= new AlumnoData();//aca es para guardarlo en la base de datos
          alu.modificarAlumno(juan);

//        alu.eliminarAlumno(1);

          alu.buscarAlumno(1);
    }
    
}
