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
public class ProfesorControlador  implements ActionListener {

    final private UsuarioDAO usuario;
    private TestDAO testdao=null;
    final private VistaProfesor vistaProfesor;
    private VistaNuevoUsuario vnu=null;
    private VistaNuevoTest vnt=null;
    private Usuario creauser=null;
    private Usuario userprof;
    private Test test=null;

    public ProfesorControlador(UsuarioDAO u,Usuario us, VistaProfesor vp) throws NullPointerException{
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor = vp;
        vistaProfesor.setVisible(true);
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
        }
    }

    private void initEvents() throws NullPointerException{
        vistaProfesor.btnAnadirUsuario.setActionCommand("ADDEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
        vistaProfesor.btnAnadeTest.setActionCommand("ADDTEST");
        vistaProfesor.btnAnadeTest.addActionListener(this);

    }

    private void aniadeVistaUsuario() throws NullPointerException{
        vistaProfesor.setVisible(false);
        vnu= new VistaNuevoUsuario();
        vnu.setVisible(true);
        vnu.btnAnadir.setActionCommand("ADDu");
        vnu.btnAnadir.addActionListener(this);
        
    }

    private void aniadeUsuario() throws NullPointerException{
       
        creauser = new Usuario(vnu.tfDniUser.getText(),
                vnu.tfNombre.getText(), vnu.tfApellidos.getText(),
                vnu.pfPassword.getText(), vnu.rbSiPermiso.isSelected());
        usuario.insertaUsuario(creauser);
        //vnu.setVisible(false);
        vnu.tfNombre.setText("");
        vnu.tfDniUser.setText("");
        vnu.tfApellidos.setText("");
        vnu.pfPassword.setText("");

        vistaProfesor.setVisible(true);
        }
    private void aniadeNuevoTest()throws NullPointerException{
        vistaProfesor.setVisible(false);
        vnt=new VistaNuevoTest();
        vnt.setVisible(true);
        vnt.btnNuevoTest.setActionCommand("ADDt");
        vnt.btnNuevoTest.addActionListener(this);
        vnt.jTextAutor.setText(userprof.getDni());
        
    }
    private void aniadeTest()throws NullPointerException{
        
        testdao=new TestDAO();
        test= new Test(testdao.devuelveSequence(),vnt.jTextNombre.getText(),Integer.parseInt(vnt.jTextDuracion.getText()),
                            Integer.parseInt(vnt.jTextResta.getText()),userprof.getDni(),vnt.rbActivo.isSelected());
        System.out.println(testdao.devuelveSequence());
        testdao.insertaTest(test);
        vnt.jTextNombre.setText("");
        vnt.jTextDuracion.setText("");
        vnt.jTextResta.setText("");
        vistaProfesor.setVisible(true);

    }
    

}
