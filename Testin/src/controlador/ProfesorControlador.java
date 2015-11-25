/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaNuevoTest;
import Vistas.VistaNuevoUsuario;
import Vistas.VistaProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Test;
import modelo.Usuario;
import modeloDAO.TestDAO;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author andresbailen93
 */
public class ProfesorControlador implements ActionListener {

    final private UsuarioDAO usuario;
    final private TestDAO testdao=null;
    final private VistaProfesor vistaProfesor;
    private VistaNuevoUsuario vnu=null;
    private VistaNuevoTest vnt=null;
    private Usuario user=null;

    public ProfesorControlador(UsuarioDAO u, VistaProfesor vp) {
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor = vp;
        vistaProfesor.setVisible(true);
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
        }
    }

    private void initEvents() {
        vistaProfesor.btnAnadirUsuario.setActionCommand("ADDEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
        vistaProfesor.btnAnadeTest.setActionCommand("ADDTEST");
        vistaProfesor.btnAnadeTest.addActionListener(this);
        vnu.btnAnadir.setActionCommand("ADDu");
        vnu.btnAnadir.addActionListener(this);
        vnt.btnNuevoTest.setActionCommand("ADDt");
        vnt.btnNuevoTest.addActionListener(this);
    }

    private void aniadeVistaUsuario() {
        vistaProfesor.setVisible(false);
        vnu= new VistaNuevoUsuario();
        vnu.setVisible(true);
        vnu.btnAnadir.setActionCommand("ADDu");
        vnu.btnAnadir.addActionListener(this);
        
    }

    private void aniadeUsuario() {
       
        user = new Usuario(vnu.tfDniUser.getText(),
                vnu.tfNombre.getText(), vnu.tfApellidos.getText(),
                vnu.pfPassword.getText(), vnu.rbSiPermiso.isSelected());
        usuario.insertaUsuario(user);
        //vnu.setVisible(false);
        vnu.tfNombre.setText("");
        vnu.tfDniUser.setText("");
        vnu.tfApellidos.setText("");
        vnu.pfPassword.setText("");

        vistaProfesor.setVisible(true);
        }
    private void aniadeNuevoTest(){
        vistaProfesor.setVisible(false);
        vnt=new VistaNuevoTest();
        vnt.setVisible(true);
        vnt.btnNuevoTest.setActionCommand("ADDt");
        vnt.btnNuevoTest.addActionListener(this);
        
    }
    private void aniadeTest(){
        vnt.jTextAutor.setText(user.getDni());
        Test test= new Test(4,vnt.jTextNombre.getText(),Integer.parseInt(vnt.jTextDuracion.getText()),
                            Integer.parseInt(vnt.jTextResta.getText()),vnt.jTextAutor.getText(),vnt.rbActivo.isSelected());
        testdao.insertaTest(test);
        vnt.jTextNombre.setText("");
        vnt.jTextDuracion.setText("");
        vnt.jTextResta.setText("");
        vistaProfesor.setVisible(true);

    }
    

}
