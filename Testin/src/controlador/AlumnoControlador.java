/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaAlumno;
import Vistas.VistaSeleccionarTest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alejandroruiz
 */
class AlumnoControlador implements ActionListener{
    
    final private VistaAlumno va;
    
    public AlumnoControlador(VistaAlumno v){
        this.va=v;
        va.setVisible(true);
        initEvents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SELECCIONAR")){
            
            VistaSeleccionarTest vst = new VistaSeleccionarTest();
            vst.setVisible(true);
        }
    }

    private void initEvents() {
        va.btnSeleccionar.setActionCommand("SELECCIONAR");
        va.btnSeleccionar.addActionListener(this);
        va.btnResultados.setActionCommand("RESULTADOS");
        va.btnResultados.addActionListener(this);
    }
    
}
