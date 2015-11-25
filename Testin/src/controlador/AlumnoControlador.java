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
import modelo.Examen;
import modelo.Test;
import modelo.Usuario;
import modeloDAO.ExamenDAO;
import modeloDAO.TestDAO;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author alejandroruiz
 */
class AlumnoControlador implements ActionListener{
    
    final private VistaAlumno va;
    private VistaSeleccionarTest vst;
    private TestDAO testDAO;
    private ExamenDAO examenDAO;
    private UsuarioDAO usuario;
    String dni;
    private Usuario alumno;
    
    public AlumnoControlador(UsuarioDAO u,String dni, VistaAlumno v){
        usuario = (u == null) ? new UsuarioDAO() : u;
        this.dni=dni;
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
                alumno = usuario.devuelveUsuario(dni);
                System.out.println(alumno);
                ArrayList<Examen> lista_examenes = new ExamenDAO().devolverExamenesAlumno(alumno);
                
                for(Examen ex:lista_examenes){
                    System.out.println(ex);
                }
                VistaResultados vr = new VistaResultados(lista_examenes.size(),lista_examenes);
                //vr.modeloTabla.addRow(lista_examenes);
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
    
   private void processSeleccionar(){
       ArrayList<Examen> listaExamenes = new ExamenDAO().devolverExamenesAlumno(null);
       
       
   }
    
}
