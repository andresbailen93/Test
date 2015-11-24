/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Examen;
import modelo.Test;
import modelo.Usuario;

/**
 *
 * @author andresbailen93
 */
public class ExamenDAO {
    
    private Connection con = null;
    PreparedStatement pstmt=null;
    Statement stmt = null;

    /**
     * Constructor de la clase ExamenDAO, en el que al atributo de tipo Connection se asigna una conexión llamando a la clase ConexiónOrcl
     */
    public ExamenDAO() {
        con = new ConexionOrcl().conecta();
    }
    
    /**
     * Procedimiento que introduce un examen en la Base de datos
     * @param exam Recibe como parámetro un objeto de la clase Examen
     */
    public void crearExamen(Examen exam){
        try {
            pstmt = con.prepareStatement("INSERT INTO EXAMEN VALUES (?,?,?,?,?,?)");
            pstmt.clearParameters();
            pstmt.setString(1,exam.getDni());
            pstmt.setDate(3,exam.getFecha());
            pstmt.setInt(4,exam.getAciertos());
            pstmt.setInt(5,exam.getFallos());
            pstmt.setDouble(6,exam.getNota());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pstmt=null;
        }
   
    }
    
    
    public void devolverNotas(Test test){
       Map<String,Double> lista_notas = new TreeMap<String,Double>();
       
        try {
            stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ")
        } catch (SQLException ex) {
            Logger.getLogger(ExamenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       

          
        
        
        
    }
    
    
   
    
    
    
}
