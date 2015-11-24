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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pregunta;
import modelo.Respuesta;

/**
 *
 * @author andresbailen93
 */
public class RespuestaDAO {

    private Connection con = null;
    PreparedStatement psSentencia = null;

    /**
     * Constructor por defecto que instancia la conexion con la base de datos.
     */
    public RespuestaDAO() {
        con = new ConexionOrcl().conecta();
    }
    public void insertaRespuesta(Respuesta resp){
        if(psSentencia==null){
            try {
                psSentencia = con.prepareStatement("INSERT INTO RESPUESTA (ID_RESPUESTA,TEXTO,CORRECTA,ID_PREGUNTA) VALUES (?,?,?,?)");
                psSentencia.clearParameters();
                psSentencia.setInt(1,resp.getId_respuesta());
                psSentencia.setString(2,resp.getTexto());
                psSentencia.setBoolean(3,resp.isCorrecta());
                psSentencia.setInt(4,resp.getId_pregunta());
                psSentencia.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(RespuestaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                psSentencia=null;
            }

        }
        
    }
    public ArrayList<Respuesta> devuelveRespuesta(Pregunta preg){
        if(psSentencia==null){
            try {
                psSentencia = con.prepareStatement("SELECT * FROM RESPUESTA WHERE ID_PREGUNTA=?");
                psSentencia.clearParameters();
                psSentencia.setInt(1, preg.getId_pregunta());
            } catch (SQLException ex) {
                Logger.getLogger(RespuestaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs= psSentencia.executeQuery();
            while (rs.next()) {
             = new Usuario(rs.getString("dni"), rs.getString("nombre"),
                            rs.getString("password"), rs.getString("contraseña"),
                            rs.getBoolean("es_prof"));

                }
        }
        
    }
}