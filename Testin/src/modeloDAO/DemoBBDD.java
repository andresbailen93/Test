/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

/**
 *
 * @author andresbailen93
 */
public class DemoBBDD {
    public static void main(String[] args) {
        ConexionOrcl conec= new ConexionOrcl();
        conec.conecta();
        System.out.println(conec);
        conec.desconecta(conec.getCon());
        System.out.println(conec);
    }
}
