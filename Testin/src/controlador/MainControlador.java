/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vistas.VistaLogin;

/**
 *
 * @author csalas
 */
public class MainControlador {
    
    public static void main(String args[]) {
        LoginControlador lc = new LoginControlador(null, new VistaLogin());
    }
    
}
