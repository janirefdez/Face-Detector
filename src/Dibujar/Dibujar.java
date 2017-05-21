package Dibujar;


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
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Janire Fernandez
 */
public class Dibujar {
    
    
    
    
    public Dibujar()
    {
        
    }
    
    public void dibujarElipse(Rect[] arrayCaras, Mat imagen,JPanel jPanel1)
    {
         for (int i = 0; i < arrayCaras.length; i++) 
         {
            /*DIBUJA UN ELIPSE A LO QUE CORRESPONDE LA CARA*/
            /*Point para hayar el centro de la imagen y de esta forma poder realizar un circulo*/
            Point center = new Point((arrayCaras[i].x + arrayCaras[i].width * 0.5),(arrayCaras[i].y + arrayCaras[i].height * 0.5));
            /*Se multiplica por 0.5 porquee es dividir a la mitad, de esta forma se calcula el punto central*/
           
            Imgproc.ellipse(imagen,center,new Size(arrayCaras[i].width * 0.5, arrayCaras[i].height * 0.5), 
                                            0, /*angulo inicio*/
                                            0, /*angulo final*/
                                            360, 
                                            new Scalar(255, 0, 255), 4, 8, 0); /*color de la elipse que se dibuja*/
   
        }
    }
    
    public void dibujarRectangulo(Rect[] arrayCaras,Mat imagen,JPanel jPanel1)
    {
        for (int i = 0; i < arrayCaras.length; i++) 
        {
          
            Imgproc.rectangle(imagen,
                    new Point(arrayCaras[i].x, arrayCaras[i].y),
                    new Point(arrayCaras[i].x + arrayCaras[i].width, arrayCaras[i].y + arrayCaras[i].height),
                    new Scalar(255, 0, 255), 4, 8, 0);
             
        }
        
          /*String filename = "Detection.png";
          System.out.println(String.format("Writing %s", filename));
          Imgcodecs.imwrite(filename, imagen);*/          
    }
    
     public void dibujarCirculo(Rect[] arrayCaras, Mat imagen,JPanel jPanel1)
    {
       for (int i = 0; i < arrayCaras.length; i++) 
        {
             Point center = new Point((arrayCaras[i].x + arrayCaras[i].width * 0.5),(arrayCaras[i].y + arrayCaras[i].height * 0.5));
             Scalar s = new Scalar(255,0,0);
            Imgproc.circle(imagen, center, 250, s, 3);
        }
    }
        
   
    
}

