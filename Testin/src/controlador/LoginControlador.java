/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaAlumno;
import Vistas.VistaLogin;
import Vistas.VistaProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author csalas
 */
public class LoginControlador implements ActionListener {
    final private UsuarioDAO usuario;
    final private VistaLogin vistaLogin;
    
    public LoginControlador(UsuarioDAO u, VistaLogin vl) {
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaLogin = vl;
        vistaLogin.setVisible(true);
        initEvents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("LOGIN")) {
            
            Usuario u = usuario.logginUser(vistaLogin.user.getText().toString(), vistaLogin.pass.getText().toString());
            System.out.println(u);
            if (u != null) {
                
                if (u.isEs_profesor()) {
                    VistaProfesor vp = new VistaProfesor();
                    vp.setVisible(true);
                }
                else {
                    VistaAlumno va = new VistaAlumno(u.getNombre());
                    va.setVisible(true);
                }
            }
        }
    }
    
    private void initEvents() {
        vistaLogin.btnConectar.setActionCommand("LOGIN");
        vistaLogin.btnConectar.addActionListener(this);
    }
    
    
    
}
