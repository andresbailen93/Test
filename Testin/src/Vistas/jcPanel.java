package Vistas;
import Vistas.jpComponente;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
/**
 * 
 */
public class jcPanel extends JPanel{

     public jcPanel()
    {
        this.setSize(300, 400);
        this.setVisible(true);
        
        this.setLayout( new FlowLayout() );
    }

    public void Mi_Componente(ButtonGroup buttongroup,String pregunta)
    {        
        //instancia nueva a componente
        jpComponente jpc = new jpComponente();
        buttongroup.add(jpc.radioRespuesta);
        jpc.radioRespuesta.setText(pregunta);
        this.add(jpc);//se añade al jpanel
        this.validate();
        
    }

   

}
