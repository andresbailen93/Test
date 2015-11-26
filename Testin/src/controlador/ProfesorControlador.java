/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaCrearPregunta;
import Vistas.VistaNuevoTest;
import Vistas.VistaNuevoUsuario;
import Vistas.VistaProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Categoria;
import modelo.Test;
import modelo.Usuario;
import modeloDAO.CategoriaDAO;
import modeloDAO.TestDAO;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author andresbailen93
 */
public class ProfesorControlador  implements ActionListener {

    final private UsuarioDAO usuario;
    private TestDAO testdao=null;
    private CategoriaDAO categodao=null;
    final private VistaProfesor vistaProfesor;
    private VistaNuevoUsuario vnu=null;
    private VistaNuevoTest vnt=null;
    private VistaCrearPregunta vcp=null;
    private Usuario creauser=null;
    private Usuario userprof;
    private Test test=null;

    public ProfesorControlador(UsuarioDAO u,Usuario us, VistaProfesor vp) throws NullPointerException{
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor = vp;
        vistaProfesor.setVisible(true);
        vistaProfesor.setLocationRelativeTo(null);
        userprof=us;
        initEvents();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ADDEUSUARIO":
                aniadeVistaUsuario();
                break;
            case "ADDu":
                aniadeUsuario();
                break;
            case "ADDTEST":
                aniadeNuevoTest();
                break;
            case "ADDt":
                aniadeTest();
                break;
            case "ADDPREGUNTA":
                aniadeNuevaPregunta();
                break;
            case "ADDp":
               //aniadePregunta();
                break;
            case"ADDcategoria":
                  aniadeCategoria();  
        }
    }

    private void initEvents() throws NullPointerException{
        vistaProfesor.btnAnadirUsuario.setActionCommand("ADDEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
        vistaProfesor.btnAnadeTest.setActionCommand("ADDTEST");
        vistaProfesor.btnAnadeTest.addActionListener(this);
        vistaProfesor.btnCreaPregunta.setActionCommand("ADDPREGUNTA");
        vistaProfesor.btnCreaPregunta.addActionListener(this);

    }

    private void aniadeVistaUsuario() throws NullPointerException{
        vnu= new VistaNuevoUsuario();
        vnu.setVisible(true);
        vnu.setLocationRelativeTo(null);
        vnu.btnAnadir.setActionCommand("ADDu");
        vnu.btnAnadir.addActionListener(this);
        
    }

    private void aniadeUsuario() throws NullPointerException{
       
        creauser = new Usuario(vnu.tfDniUser.getText(),
                vnu.tfNombre.getText(), vnu.tfApellidos.getText(),
                vnu.pfPassword.getText(), vnu.rbSiPermiso.isSelected());
        usuario.insertaUsuario(creauser);
        /*vnu.tfNombre.setText("");
        vnu.tfDniUser.setText("");
        vnu.tfApellidos.setText("");
        vnu.pfPassword.setText("");*/

      
        }
    private void aniadeNuevoTest()throws NullPointerException{
        vnt=new VistaNuevoTest();
        vnt.setVisible(true);
        vnt.setLocationRelativeTo(null);
        vnt.btnNuevoTest.setActionCommand("ADDt");
        vnt.btnNuevoTest.addActionListener(this);
        vnt.jTextAutor.setText(userprof.getDni());
        
    }
    private void aniadeTest()throws NullPointerException{
        testdao=new TestDAO();
        test= new Test(testdao.devuelveSequence(),vnt.jTextNombre.getText(),vnt.cbDuracion.getSelectedIndex()*60,
                            vnt.cbRestada.getSelectedIndex(),userprof.getDni(),vnt.rbActivo.isSelected());
       // System.out.println(testdao.devuelveSequence());
        testdao.insertaTest(test);
        vnt.jTextNombre.setText("");
        vnt.cbDuracion.setSelectedIndex(0);
        vnt.cbRestada.setSelectedIndex(0);
        //vistaProfesor.setVisible(true);
        vnt.setVisible(false);

    }
    private void aniadeNuevaPregunta()throws NullPointerException{
        vcp=new VistaCrearPregunta();
        testdao=new TestDAO();
        categodao=new CategoriaDAO();
        vcp.setVisible(true);
        vcp.setLocationRelativeTo(null);
        ArrayList<Test> lista_test=testdao.devuelveTestes(userprof);
        
        for(int i=0;i<lista_test.size();i++){
            vcp.cbSelecTestID.addItem(lista_test.get(i).getNombre());
        }
        
        ArrayList<Categoria> lista_cate=categodao.ListarCategorias();
        for(int i=0;i<lista_cate.size();i++){
            vcp.cbSelecTema.addItem(lista_cate.get(i).getNombre());
        }
        vcp.btnaddTema.setActionCommand("ADDcategoria");
        vcp.btnaddTema.addActionListener(this);
    }
    private void aniadeCategoria(){
        String categoria=vcp.tfAnadeTema.getText();
        int idcategoria=categodao.devuelveSequence();
        Categoria cat=new Categoria(idcategoria,categoria);
        categodao.InsertarCategoria(cat);
        vcp.cbSelecTema.addItem(cat.getNombre());
    }

    

}
