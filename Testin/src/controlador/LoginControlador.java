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
import javax.swing.JOptionPane;
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
            
            Usuario u = usuario.logginUser(vistaLogin.user.getText(), vistaLogin.pass.getText());//He quitado los toString era redundante
            //System.out.println(u);
            if (u != null) {
                
                if (u.isEs_profesor()) {
                    ProfesorControlador pc= new ProfesorControlador(usuario, new VistaProfesor());
                }
                else {
                    //System.out.println("entra");
                    AlumnoControlador ac = new AlumnoControlador(usuario,vistaLogin.user.getText(),new VistaAlumno(u.getNombre()));    
                }
            }
            else {
                JOptionPane.showMessageDialog(vistaLogin, "Usuario/Contraseña incorrecta",null,JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void initEvents() {
        vistaLogin.btnConectar.setActionCommand("LOGIN");
        vistaLogin.btnConectar.addActionListener(this);
    }
    
    
    
}
