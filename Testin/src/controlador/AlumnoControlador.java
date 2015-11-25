/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaAlumno;
import Vistas.VistaProfesor;
import Vistas.VistaResultados;
import Vistas.VistaSeleccionarTest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import modelo.Test;
import modeloDAO.TestDAO;

/**
 *
 * @author alejandroruiz
 */
class AlumnoControlador implements ActionListener{
    
    final private VistaAlumno va;
    private VistaSeleccionarTest vst;
    private TestDAO testDAO;
    
    public AlumnoControlador(VistaAlumno v){
        this.va=v;
        va.setVisible(true);
        initEvents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SELECCIONAR":
                vst = new VistaSeleccionarTest();
                processTestList();
                vst.setVisible(true);
                break;
            case "RESULTADOS":
                VistaResultados vr = new VistaResultados(10);
                vr.setVisible(true);
                break;
        }
    }

    private void initEvents() {
        
        va.btnSeleccionar.setActionCommand("SELECCIONAR");
        va.btnSeleccionar.addActionListener(this);
        va.btnResultados.setActionCommand("RESULTADOS");
        va.btnResultados.addActionListener(this);
    }
    
    private void processTestList() {
        // Recuperamos los test activos
        ArrayList<Test> listaTest = new TestDAO().devuelveTestActivos();
        DefaultListModel<String> listaNombreTest = new DefaultListModel<>();
        for (Test t: listaTest) {
            listaNombreTest.addElement(t.getNombre());
        }
        vst.jListTest.setModel(listaNombreTest);
        vst.jListTest.setSelectedIndex(0);
    }
    
}
