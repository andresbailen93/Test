/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;

/**
 *
 * @author andresbailen93
 */
public class CategoriaDAO {

    private Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    
    
    /**
     * Contructor de la clase CategoríaDAO, en el que al atributo de tipo Connection se asigna una conexión llamando a la clase ConexiónOrcl
     */
    public CategoriaDAO() {
         this.con = new ConexionOrcl().conecta();
    }
    
    /**
     * Procedimiento que inserta una nueva categoría en el BBDD
     * @param cat Recibe como parámetro un objeto de la clase Categoría
     */
    public void InsertarCategoria(Categoria cat){
        
        try {
            pstmt = con.prepareStatement("INSERT INTO CATEGORIA VALUES (?,?)");
            pstmt.clearParameters();
            pstmt.setString(2,cat.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pstmt=null;
        }
        
    }
    
    /**
     * Función que devuelve una lista con todos los objetos categorias que existen en la base de datos
     * @return ArrayList de tipos Categoria
     */
    public ArrayList<Categoria> ListarCategorias(){
        
        ArrayList<Categoria> lista_categorias = new ArrayList<>();
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORIA");
            
            Categoria cat;
            
            while(rs.next()){

                    cat = new Categoria(rs.getInt("ID_CATEGORIA"),rs.getString("NOMBRE"));
                    lista_categorias.add(cat);
                }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            stmt=null;
        }
        
       return lista_categorias; 
    }
    
    
    @Override
    protected void finalize() throws Throwable {
            super.finalize();
            con.close();
        
    }
    
    
    
    
    
    
}
