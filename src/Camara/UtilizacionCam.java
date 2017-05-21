package Camara;


import Deteccion.Deteccion;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Janire Fernandez
 */
public class UtilizacionCam {
    
    
     private VideoCapture camara;
     private Mat imagen;
     
     
     public UtilizacionCam()
     {
        imagen = new Mat();
     }
     
     /*POR DEFECTO TIENE PRIORIDAD LA CAMARA EXTERNA QUE LA INTERNA.= inicioCamara
       En caso de preferencia se podrían utilizar las clases inicioCamaraInterna o inicioCamaraExterna en Pprincipal*/
     public void inicioCamara()
     {
         if(inicioCamaraExterna())
         {
             System.out.println("SE ABRIO LA CAMARA EXTERNA");
         }else{
             System.out.println("NO HAY DISPOSITIVO DE IMAGEN EXTERNO");
             if(inicioCamaraInterna())
             {
                System.out.println("SE ABRIO LA CAMARA INTERNA");
             }else{
              System.out.println("NO HAY DISPOSITIVO DE IMAGEN INTERNO");
             }
         }
     }
     
     public boolean inicioCamaraExterna()
    {
        boolean comprobacion= false;
        camara = new VideoCapture(1);//device=1 para camara externa
        if(camara.isOpened())
        {
            comprobacion=true;
        }
        return comprobacion;
    }
      
      public boolean inicioCamaraInterna()
      {
          boolean comprobacion = false;
          camara = new VideoCapture(0);//device = 0 para camara interna
          if(camara.isOpened())
          {
            comprobacion=true;
          }
          return comprobacion;
      }
      public boolean capturaImagen()
    {
        //Se captura la imagen detectada por la camara y se guarda en la variable imagen
        boolean comprobacion = false;
        camara.read(imagen);
        if(!imagen.empty())
        {
            comprobacion=true;
        }
        return comprobacion;
    }
      
       public void visualizacionWebCam(Mat imagen,JPanel jPanel1)
    {
        /*Necesito convertir Mat a MatOfByte para luego substraer los bytes y poder hacer casting a (BufferedImage)*/
            
            Graphics g = jPanel1.getGraphics();
            /*Para almacenar la imagen en bytes*/
            MatOfByte mem = new MatOfByte();
            /*La función comprime la imagen y la almacena en la memoria buffer como bytes (MatOfBytes)
            imencode(String ext, Mat img, MatOfByte buf)
            ext -Extensión de archivo que define el formato de salida*/
            Imgcodecs.imencode(".bmp",imagen, mem); 
            
        try {    
            
            /*Se subtraen los bytes */
            Image imag = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            BufferedImage buff = (BufferedImage) imag;
            /*Visualización webcam sobre el panel*/
            g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null);
            
        } catch (IOException ex) {
            Logger.getLogger(Deteccion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
   //getters y setters
    public void setMatImagen(Mat imagen)
    {
        this.imagen = imagen;
    }
    
    public Mat getImagen()
    {
        return this.imagen;
    }
}
