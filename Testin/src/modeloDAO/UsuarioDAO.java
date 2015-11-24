/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andresbailen93
 */
public class UsuarioDAO {

    private Connection con = null;
    PreparedStatement psSentencia=null;

    public UsuarioDAO() {

        con = new ConexionOrcl().conecta();
    }
    public void InsertaUsuario(String dni,String nombre, String apellidos, String password, boolean es_profesor){

            if(null==psSentencia){
                try {
                    psSentencia=con.prepareStatement("INSERT INTO USUARIO (DNI,NOMBRE,APELLIDOS,PASSWORD,ES_PROFESOR) VALUES (?,?,?,?,?)");
                    psSentencia.clearParameters();
                    psSentencia.setString(1,dni);
                    psSentencia.setString(2, nombre);
                    psSentencia.setString(3, apellidos);
                    psSentencia.setString(4,password);
                    psSentencia.setBoolean(5,es_profesor);
                    psSentencia.executeUpdate();
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                psSentencia=null;
                }
            
        }
    }
    
    
    public boolean LogginUser(String dni, String password){
        String userdni = null;
        String userpass = null;
        boolean loggin=false;
        
        if(psSentencia==null){
            try {
                try {
                    psSentencia=con.prepareStatement("SELECT DNI,PASSWORD FROM USUARIO WHERE DNI=?");
                    psSentencia.clearParameters();
                    psSentencia.setString(1,dni);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                ResultSet rs=psSentencia.executeQuery();
                while(rs.next()){
                    userdni=rs.getString("dni");
                    userpass=rs.getString("password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            if(dni.equals(userdni) && password.equals(userpass)){
                loggin=true;
            }
        }
        return loggin;
    }
    
    
    
    public boolean isProfesor(String dni){
        Boolean isprofesor=false;
         if(psSentencia==null){
            try {
                try {
                    psSentencia=con.prepareStatement("SELECT ES_PROFESOR FROM USUARIO WHERE DNI=?");
                    psSentencia.clearParameters();
                    psSentencia.setString(1,dni);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                ResultSet rs=psSentencia.executeQuery();
                while(rs.next()){
                    isprofesor=rs.getBoolean("ES_PROFESOR");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
 return isprofesor;

}
    
    
}
