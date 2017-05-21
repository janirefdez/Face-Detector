/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Camara.UtilizacionCam;
import Deteccion.Deteccion;
import Dibujar.Dibujar;
import javax.swing.JPanel;
import org.opencv.core.Mat;

/**
 *
 * @author Janire Fernandez
 */
public class HiloRealidadAumentada implements Runnable{

    private UtilizacionCam camara;
    //private boolean runnable;
    private JPanel jPanel;
    private Mat imagen;
    private Thread hilo;
    
    @Override
    public void run() {
        
        Thread miHilo=Thread.currentThread();
        while(miHilo==hilo)
       // while(runnable)
                {
                    if(!imagen.empty())
                    {
                        Dibujar figura = new Dibujar();
                        Deteccion deteccion = new Deteccion();
                        figura.dibujarRectangulo(deteccion.detectarOjoDerecho(imagen), imagen,jPanel);
                        figura.dibujarRectangulo(deteccion.detectarOjoIzquierdo(imagen), imagen, jPanel);
                        figura.dibujarRectangulo(deteccion.detectarRostro(imagen), imagen,jPanel);
                        
                        camara.visualizacionWebCam(imagen, jPanel);
                        
                    }
                    
                }
       
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public void start()
    {
        if(hilo==null)
        {
           hilo=new Thread(this);
           hilo.start();
        }
        
    }
    
    public void stop()
    {
        if(hilo!=null)
        {
           hilo.stop();
           hilo=null;
        }
    }
   
   
     public UtilizacionCam getCamara()
    {
        return camara;
    }
    
    public void setCamara(UtilizacionCam camara)
    {
        this.camara=camara;
    }
    
    public JPanel getJPanel()
    {
        return jPanel;
    }
    
    public void setJPanel(JPanel jPanel)
    {
        this.jPanel=jPanel;
    }
    
    public Mat getImagen()
    {
        return imagen;
    }
    
    public void setImagen(Mat imagen)
    {
        this.imagen=imagen;
    }
}
