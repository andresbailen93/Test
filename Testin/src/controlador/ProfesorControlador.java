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
    private VistaNuevoUsuario vnu=null;

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
            case "ADD":
                aniadeUsuario();
                break;
        }
    }

    private void initEvents() {
        vistaProfesor.btnAnadirUsuario.setActionCommand("ADDEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
        vnu.btnAnadir.setActionCommand("ADD");
        vnu.btnAnadir.addActionListener(this);
    }

    private void aniadeVistaUsuario() {
        vistaProfesor.setVisible(false);
        vnu= new VistaNuevoUsuario();
        vnu.setVisible(true);
        vnu.btnAnadir.setActionCommand("ADD");
        vnu.btnAnadir.addActionListener(this);
        
    }

    private void aniadeUsuario() {
        Boolean es_prof = false;
        if (vnu.rbSiPermiso.isSelected()) {
            es_prof = true;
        }
        Usuario user = new Usuario(vnu.tfDniUser.getText(),
                vnu.tfNombre.getText(), vnu.tfApellidos.getText(),
                vnu.tfPassword.getText(), es_prof);
        usuario.insertaUsuario(user);
        //vnu.setVisible(false);
        vistaProfesor.setVisible(true);
        }
    

}
