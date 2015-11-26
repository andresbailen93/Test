/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Utilidades.Messages;
import Vistas.VistaAlumno;
import Vistas.VistaLogin;
import Vistas.VistaProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
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
        vistaLogin.setLocationRelativeTo(null);       
        vistaLogin.toFront();
        
        initEvents();
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException{
        if (e.getActionCommand().equals("LOGIN")) {
            
            Usuario u = usuario.logginUser(vistaLogin.user.getText(), vistaLogin.pfPassword.getText());//He quitado los toString era redundante
            //System.out.println(u);
            if (u != null) {
                
                if (u.isEs_profesor()) {
                    ProfesorControlador pc= new ProfesorControlador(usuario, u,new VistaProfesor());
                    vistaLogin.dispose();
                }
                else {
                    AlumnoControlador ac = new AlumnoControlador(u, new VistaAlumno(u.getNombre()));    
                    vistaLogin.dispose();
                }
            }
            else {
                JOptionPane.showMessageDialog(vistaLogin, Messages.getString("msg_error"),null,JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getActionCommand().equals("ESPAÑOL")){
           Messages.setLocale(new Locale("es","ES"));
           
           
       }else if(e.getActionCommand().equals("INGLES")){
           Messages.setLocale(new Locale("en","UK"));
          
       }
    }
    
    private void initEvents() {
        
        vistaLogin.btnConectar.setActionCommand("LOGIN");
        vistaLogin.btnConectar.addActionListener(this);
        vistaLogin.btnIdiomaES.setActionCommand("ESPAÑOL");
        vistaLogin.btnIdiomaES.addActionListener(this);
        vistaLogin.btnIdiomaUK.setActionCommand("INGLES");
        vistaLogin.btnIdiomaUK.addActionListener(this);
        
    }
    
    
    
}
