/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaLogin;
import Vistas.VistaNuevoUsuario;
import Vistas.VistaProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author andresbailen93
 */
public class ProfesorControlador implements ActionListener {

    final private UsuarioDAO usuario;
    final private VistaProfesor vistaProfesor;
    final private VistaNuevoUsuario vistaNuevoUsuario;
    private VistaNuevoUsuario vnu=null;

    public ProfesorControlador(UsuarioDAO u, VistaProfesor vp) {
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor = vp;
        vistaProfesor.setVisible(true);
        vistaNuevoUsuario=null;
        initEvents();
    }
    public ProfesorControlador(UsuarioDAO u, VistaNuevoUsuario vnu){
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor=null;
        vistaNuevoUsuario = vnu;
        vistaNuevoUsuario.setVisible(true);
        initEvents(); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADDEUSUARIO")) {
            aniadeVistaUsuario();
        }
        if (e.getActionCommand().equals("ADDUSER")) {
            aniadeUsuario();
        }
    }

    private void initEvents() {
        vistaProfesor.btnAnadirUsuario.setActionCommand("ADDEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
        vistaNuevoUsuario.btnAnadir.setActionCommand("ADDUSER");
        vistaNuevoUsuario.btnAnadir.addActionListener(this);//REVISAAaaaaaaaaaaR
    }

    private void aniadeVistaUsuario() {
        vistaProfesor.setVisible(false);
        ProfesorControlador ctrProfesor = new ProfesorControlador(usuario,new VistaNuevoUsuario());
    }

    private void aniadeUsuario() {
        boolean es_prof = false;
        System.out.println("ENTROanieadeUsuarioTAMBIEN");

        if (vnu.rbSiPermiso.isSelected()) {
            es_prof = true;
        }
        Usuario user = new Usuario(vnu.tfDniUser.getText(),
                vnu.tfNombre.getText(), vnu.tfApellidos.getText(),
                vnu.tfPassword.getText(), es_prof);
        usuario.insertaUsuario(user);
        vnu.setVisible(false);
        vistaProfesor.setVisible(true);

    }

}
