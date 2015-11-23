/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inftel23
 */
public class ConexionOrcl {

    public Connection conecta() {
        Connection con = null;
        try {

            Properties pr1 = new Properties();
            FileInputStream conexionconf = new FileInputStream("./conexionconf.properties");
            pr1.load(conexionconf);
            conexionconf.close();
            String conexion="jdbc:oracle:"+pr1.getProperty("tipoConexion")+
                    ":"+pr1.getProperty("user")+"/"+pr1.getProperty("pass")+"@"
                    +pr1.getProperty("host")+":"+pr1.getProperty("port")+":"
                    +pr1.getProperty("SID");
            
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection(conexion);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConexionOrcl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

            }


        } catch (IOException ex) {
            Logger.getLogger(ConexionOrcl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void desconecta(Connection descon){
        try {
            descon.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOrcl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
