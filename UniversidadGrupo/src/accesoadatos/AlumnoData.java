package accesoadatos;

import entidades.Alumno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlumnoData {

    private Connection con = null;

    public AlumnoData() {
        con = Conexion.getConexion(); //cargar los drivers y establecer la conexion a la DB
    }

    public void guardarAlumno(Alumno alumno) {

        String sql = " INSERT INTO alumno (dni,apellido,nombre,fechaNacimiento,estado)"
                + "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isActivo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();//matriz o tabla de una columna en este caso con la cantidad de alumnos que ingrese
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));//le paso el numero de columnas en que estan los id
                JOptionPane.showMessageDialog(null, "Alumno guardado");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
        }
    }

    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni= ?, apellido= ?, nombre= ?, fechaNacimiento= ? "
                + "WHERE idAlumno= ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento())); 
            ps.setInt(5,alumno.getIdAlumno());
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Falta un parametro a modificar");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");
        }

    }

    public void eliminarAlumno(int idAlumno) {
        String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno=? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno eliminado");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");
        }

    }

    public Alumno buscarAlumno(int idAlumno) { //busco un alumno especifico
        Alumno al = null;
        String sql = "SELECT * FROM alumno WHERE idAlumno=?AND estado=1";
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                al = new Alumno();
                al.setIdAlumno(rs.getInt("idAlumno"));
                al.setDni(rs.getInt("dni"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                al.setActivo(rs.getBoolean("estado"));
                System.out.println("Imprimo los datos : " + al);
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese alumno");
            }
            ps.close();
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return al;
    }
    
    public Alumno buscarAlumnoPorDni(int dni) { //busco un alumno especifico
        System.out.println("persistencia.AlumnoData.buscarAlumno()");
        Alumno al = null;
        String sql = "SELECT * FROM alumno WHERE dni=? AND estado=1";
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni); // seteas el valor que estas buscando
            
           // System.out.println("valor del PS "+dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                al = new Alumno();
                al.setIdAlumno(rs.getInt("idAlumno"));
                al.setDni(rs.getInt("dni"));
                al.setApellido(rs.getString("apellido"));
                al.setNombre(rs.getString("nombre"));
                al.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                al.setActivo(rs.getBoolean("estado"));
                System.out.println("Imprimo los datos : " + al);
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese alumno");
            }
            ps.close();
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return al;
    }

        public List<Alumno> listarAlumnos() { 
        System.out.println("persistencia.AlumnoData.buscarAlumno()");
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno WHERE estado=1";
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(rs.getBoolean("estado"));
//                System.out.println("Datos: "+alumno);
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumnos;
    }

}
