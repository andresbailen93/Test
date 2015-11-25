/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaAlumno;
import Vistas.VistaHacerTest;
import Vistas.VistaProfesor;
import Vistas.VistaResultados;
import Vistas.VistaSeleccionarTest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.DefaultListModel;

import javax.swing.JOptionPane;
import modelo.Examen;
import modelo.Pregunta;
import modelo.Respuesta;

import modelo.Examen;

import modelo.Test;
import modelo.Usuario;
import modeloDAO.ExamenDAO;

import modeloDAO.PreguntaDAO;
import modeloDAO.RespuestaDAO;

import modeloDAO.TestDAO;
import modeloDAO.UsuarioDAO;

/**
 *
 * @author alejandroruiz
 */
class AlumnoControlador implements ActionListener{
    
    final private VistaAlumno va;
    private VistaSeleccionarTest vst;
    private VistaHacerTest vht;
    private TestDAO testDAO;

    private RespuestaDAO respuestaDAO;

    private ExamenDAO examenDAO;
    private UsuarioDAO usuarioDAO;
    String dni;
    private Usuario alumno;

    private ArrayList<Test> listaTest;
    private ArrayList<Pregunta> listaPreguntas;
    private int preguntaActual = 0; // Indica la pregunta por la que va el test
    private ArrayList<String> listaRespuestasUsuario;
    private Test testActual;
    private Usuario usuario;
    
    public AlumnoControlador(Usuario u, VistaAlumno v){
       this.va=v;
        va.setVisible(true);
        initEvents();
        this.usuario = u; 
    }

    public AlumnoControlador(UsuarioDAO u,String dni, VistaAlumno v){
        usuarioDAO = (u == null) ? new UsuarioDAO() : u;
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
                vst.btnSeleccionarTest.setActionCommand("SELECCIONAR_TEST");
                vst.btnSeleccionarTest.addActionListener(this);
                processTestList();
                vst.setVisible(true);
                break;
            case "RESULTADOS":
                alumno = usuarioDAO.devuelveUsuario(dni);
                System.out.println(alumno);
                ArrayList<Examen> lista_examenes = new ExamenDAO().devolverExamenesAlumno(alumno);
                VistaResultados vr = new VistaResultados(lista_examenes.size());
                
                for(Examen ex:lista_examenes){
                    System.out.println(ex);
                    String dni = ex.getDni();
                    String id_test = Integer.toString(ex.getId_test());
                    String fecha = new SimpleDateFormat("dd-MM-yyyy").format(ex.getFecha());
                    String aciertos= Integer.toString(ex.getAciertos());
                    String fallos=Integer.toString(ex.getFallos());
                    String nota = Double.toString(ex.getNota());
                    Object[] row ={dni,id_test,fecha,aciertos,fallos,nota};
                    vr.modeloTabla.addRow(row);
                    
                    
                }
                
                vr.setVisible(true);
                
                break;
            case "SELECCIONAR_TEST":
                vht = new VistaHacerTest();
                listaRespuestasUsuario = new ArrayList<>();
                testActual = listaTest.get(vst.jListTest.getSelectedIndex());
                // Recuperamos las preguntas que tiene el test seleccionado
                listaPreguntas = new PreguntaDAO().getPreguntasFromTest(testActual);
                processPregunta();
                vht.setVisible(true);
                vht.btnSiguiente.setActionCommand("SIGUIENTE_PREGUNTA");
                vht.btnSiguiente.addActionListener(this);
                break;
            case "SIGUIENTE_PREGUNTA":
                processPregunta();
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
        testDAO = new TestDAO();
        listaTest = testDAO.devuelveTestActivos(usuario);
        DefaultListModel<String> listaNombreTest = new DefaultListModel<>();
        for (Test t: listaTest) {
            listaNombreTest.addElement(t.getNombre());
        }
        vst.jListTest.setModel(listaNombreTest);
        vst.jListTest.setSelectedIndex(0);
    }
    
    private void processPregunta() {
        // numero respuesta, texto_primera_pregunta
        if (preguntaActual == 0) {
            ArrayList<Respuesta> listaRespuestas = new RespuestaDAO().devuelveRespuesta(listaPreguntas.get(preguntaActual));
            vht.setPregunta(listaPreguntas.get(preguntaActual).getTexto(), listaRespuestas, listaPreguntas.size(), preguntaActual);
            preguntaActual++;
        }
        else if (listaPreguntas.size() > preguntaActual) {
            if (vht.mi_panel.respSeleccionada != null) {
                listaRespuestasUsuario.add(vht.mi_panel.respSeleccionada);
                ArrayList<Respuesta> listaRespuestas = new RespuestaDAO().devuelveRespuesta(listaPreguntas.get(preguntaActual));
                vht.setPregunta(listaPreguntas.get(preguntaActual).getTexto(), listaRespuestas, listaPreguntas.size(), preguntaActual);
                preguntaActual++;
            }
        }
        else
            if (vht.mi_panel.respSeleccionada != null) {
                listaRespuestasUsuario.add(vht.mi_panel.respSeleccionada);        
                corregirTest();
            }
    }
    
    private void corregirTest() {
        int correctas = 0;
        // Para cada pregunta
        respuestaDAO = new RespuestaDAO();
        for (int i=0; i<listaPreguntas.size(); i++) {
            // Obtenemos su respuesta correcta
            Respuesta r = respuestaDAO.getRespuestaCorrecta(listaPreguntas.get(i));
            //System.out.println("RESPUESTA = "+r.getTexto());
            //System.out.println("RESPUESTA USUARIO = "+listaRespuestasUsuario.get(i));
            // Si la respuesta es correcta
            if (r.getTexto().equals(listaRespuestasUsuario.get(i))) 
                correctas++;
        }
        calcularNota(correctas, listaPreguntas.size());
    }
    
    private void calcularNota(int correctas, int numPreguntas) {
        int falladas = numPreguntas-correctas;
        double nota;
        // Comprobamos la configuración del test
        Test t = testDAO.getTest(testActual.getId_test());
        if (t.getResta() == 0)
            nota = correctas*10/numPreguntas;
        else // Hay que agregar la cantidad que resta, este caso resta 1 bien por cada fallo
            nota = (correctas-falladas)*10/numPreguntas;
        
        vht.setVisible(false);
        vht.dispose();
        // DefaultListModel model = (DefaultListModel) vst.jListTest.getModel();
        //model.remove(vst.jListTest.getSelectedIndex());
        JOptionPane.showMessageDialog(vst, "La calificación del test es: "+nota,null,JOptionPane.INFORMATION_MESSAGE);
        preguntaActual = 0;
        // Guardamos el examen realizado
        
        Examen e = new Examen(usuario.getDni(), t.getId_test(), new Date(Calendar.getInstance().getTime().getTime()), correctas, falladas, nota);
        new ExamenDAO().crearExamen(e);
    }

   private void processSeleccionar(){
       ArrayList<Examen> listaExamenes = new ExamenDAO().devolverExamenesAlumno(null);
       
       
   }

    
}
