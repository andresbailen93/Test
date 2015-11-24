/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaLogin;
import Vistas.VistaProfesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author andresbailen93
 */
public class ProfesorControlador implements ActionListener{
    final private UsuarioDAO usuario;
    final private VistaProfesor vistaProfesor;
    
    public ProfesorControlador(UsuarioDAO u, VistaProfesor vp) {
        usuario = (u == null) ? new UsuarioDAO() : u;
        vistaProfesor = vp;
        vistaProfesor.setVisible(true);
        initEvents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ANADEUSUARIO")){
            
            
        }
    }
    private void initEvents(){
        vistaProfesor.btnAnadirUsuario.setActionCommand("ANADEUSUARIO");
        vistaProfesor.btnAnadirUsuario.addActionListener(this);
    }
    private void aniadeUsuario(){
        
    }
    
}
