package Hilos;


import Dibujar.Dibujar;
import Deteccion.Deteccion;
import Camara.UtilizacionCam;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Janire Fernandez
 */
public class HiloCamara implements Runnable{

   
    private UtilizacionCam camara;
    private JPanel jPanel;
    private Mat imagen;
    private Thread hilo;
   
    
    /*QUE EL HILO SEA CAMARA-> Luego añadire realidad aumentada en el hilo*/
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Thread miHilo=Thread.currentThread();
        while(miHilo==hilo)
       // while(runnable)
                {
                    if(camara.capturaImagen())
                    {
                        imagen = camara.getImagen();
                        
                        Deteccion deteccion = new Deteccion();
                        Dibujar figura = new Dibujar();
                        //figura.dibujarRectangulo(deteccion.detectarOjoDerecho(imagen), imagen,jPanel);
                        //figura.dibujarRectangulo(deteccion.detectarOjoIzquierdo(imagen), imagen, jPanel);
                        figura.dibujarRectangulo(deteccion.detectarRostro(imagen), imagen,jPanel);
                        
                        camara.visualizacionWebCam(imagen, jPanel);
                        
                        
                    }
                    
                }
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
