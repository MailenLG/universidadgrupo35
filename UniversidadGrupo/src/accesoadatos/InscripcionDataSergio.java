
package accesoadatos;


import entidades.InscripcionSergio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionDataSergio {
    
    
     private Connection con = null;

    public InscripcionDataSergio() {
        con = Conexion.getConexion(); //cargar los drivers y establecer la conexion a la DB
    }

    public void guardarInscripcion(InscripcionSergio inscripcion) {
        
        //Consulta para agregar materia

        String sql = " INSERT INTO inscripcion (idAlumno,idMateria,nota)"
                + "VALUES (?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.setInt(3, inscripcion.getNota());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();//matriz o tabla de una columna en este caso con la cantidad de materias que ingrese
            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));//le paso el numero de columnas en que estan los id
                JOptionPane.showMessageDialog(null, "Inscripcion guardada");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }

//    public void modificarInscripcion(Inscripcion materia) {
//        
//        //Consulta para modificar materia
//        String sql = "UPDATE materia SET nombre= ?, anio= ? "
//                + "WHERE idMateria= ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, materia.getNombre());
//            ps.setInt(2, materia.getAnio());
//            ps.setInt(3,materia.getIdMateria());
//            int exito = ps.executeUpdate();
//
//            if (exito == 1) {
//                JOptionPane.showMessageDialog(null, "Materia modificada");
//            } else {
//                JOptionPane.showMessageDialog(null, "dedicate a otra cosa");
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "error al acceder a la tabla Materia");
//        }
//
//    }
//
//    public void eliminarMateria(int idMateria) {
//        String sql = "UPDATE materia SET estado = 0 WHERE idMateria=? ";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, idMateria);
//            int exito = ps.executeUpdate();
//
//            if (exito == 1) {
//                JOptionPane.showMessageDialog(null, "Materia eliminada con exito");
//
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "error al acceder a la tabla Materia");
//        }
//
//    }
//
//    public Materia buscarMateria(int idMateria) { //busco un materia especifico
////        System.out.println("persistencia.AlumnoData.buscarAlumno()");
//        Materia mat = null;
//        String sql = "SELECT * FROM materia WHERE idMateria=? AND estado=1";
//        PreparedStatement ps;
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, idMateria);
////            System.out.println("valor del PS "+idMateria);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//
//                mat = new Materia();
//                mat.setIdMateria(rs.getInt("idMateria"));
//                mat.setNombre(rs.getString("nombre"));
//                mat.setAnio(rs.getInt("anio"));
//                mat.setEstado(rs.getBoolean("estado"));
//                System.out.println("Imprimo los datos : " + mat);
//            } else {
//                JOptionPane.showMessageDialog(null, "No existe esa Materia");
//            }
//            ps.close();
//        } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
//        }
//        return mat;
//    }
//
//       //Busco materia por estado 1= activa 0= eliminada
//    
//        public List<Materia> listarMateria(int valor) { 
//     
//        ArrayList<Materia> materias = new ArrayList<>();
//        //Hago la consulta
//        String sql = "SELECT * FROM materia WHERE estado=?";
//      
//        PreparedStatement ps;
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, valor);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Materia materia = new Materia();
//                materia.setIdMateria(rs.getInt("idMateria"));
//                materia.setNombre(rs.getString("nombre"));             
//                materia.setAnio(rs.getInt("anio"));                 
//                materia.setEstado(rs.getBoolean("estado"));
//                materias.add(materia);
//            }
//            ps.close();
//        } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materias");
//        }
//        return materias;
//    }

    
    
    
}
