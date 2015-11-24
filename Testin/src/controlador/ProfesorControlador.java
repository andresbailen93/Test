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
    final private VistaNuevoUsuario vnu;

    public ProfesorControlador(UsuarioDAO u, VistaProfesor vp) {
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor = vp;
        vistaProfesor.setVisible(true);
        initEvents();
        vnu = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ANADEUSUARIO")) {
            System.out.println("ENTRO");
            aniadeVistaUsuario();
        }
        if (e.getActionCommand().equals("ANIADE")) {
            aniadeUsuario();
        }
    }

    private void initEvents() {
        vistaProfesor.btnAnadirUsuario.setActionCommand("ANADEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
        vnu.btnAnadir.setActionCommand("ANIADE");
        vnu.btnAnadir.addActionListener(this);//REVISAAaaaaaaaaaaR

    }

    private void aniadeVistaUsuario() {
        vistaProfesor.setVisible(false);
        vnu.setVisible(true);
    }

    private void aniadeUsuario() {
        boolean es_prof = false;
        if (vnu.rbSiPermiso.isSelected()) {
            es_prof = true;
        }
        Usuario user = new Usuario(vnu.tfDniUser.getText(),
                vnu.tfNombre.getText(), vnu.tfApellidos.getText(),
                vnu.tfPassword.getText(), es_prof);
        usuario.insertaUsuario(user);

    }

}
