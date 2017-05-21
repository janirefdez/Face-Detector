package Deteccion;



import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import static org.opencv.objdetect.Objdetect.CASCADE_SCALE_IMAGE;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Janire Fernandez
 */
public class Deteccion {
    
    private String cara_haarcascades;
    private String ojos_haarcascades;
    private String ojoDerecho_haarcascades;
    private String ojoIzquierdo_haarcascades;
    private CascadeClassifier detector;
    /*Imagen escala de grises: variable tipo matriz*/
    private Mat imagen_gris = new Mat();
    
    public Deteccion()
    {
        cara_haarcascades = "C:\\Users\\Janire Fernandez\\Documents\\Programas instalados (JANIRE)\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt2.xml";
        ojos_haarcascades="C:\\Users\\Janire Fernandez\\Documents\\Programas instalados (JANIRE)\\opencv\\sources\\data\\haarcascades\\haarcascade_eye.xml";
        ojoDerecho_haarcascades="C:\\Users\\Janire Fernandez\\Documents\\Programas instalados (JANIRE)\\opencv\\sources\\data\\haarcascades\\haarcascade_righteye_2splits.xml";
        ojoIzquierdo_haarcascades="C:\\Users\\Janire Fernandez\\Documents\\Programas instalados (JANIRE)\\opencv\\sources\\data\\haarcascades\\haarcascade_lefteye_2splits.xml";
    }
    
    private void convertirGrisYEcualizar(Mat imagen)
    {
        Imgproc.cvtColor(imagen, imagen_gris, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(imagen_gris, imagen_gris);
    }
    
    
    
    private Rect[] detectar(Mat imagen)
    {
       MatOfRect rectImag = new MatOfRect();
       Rect[] rectImagArray;
       convertirGrisYEcualizar(imagen);
       detector.detectMultiScale(imagen_gris, rectImag); 
       /*Los objetos detectados se devuelven como una lista de rectángulos.*/
       rectImagArray=rectImag.toArray();//[][][][][]
       return rectImagArray;
       
    }
   public Rect[] detectarRostro(Mat imagen)
    {
        detector = new CascadeClassifier(cara_haarcascades);
     
        Rect[] arrayCaras = detectar(imagen);
        /*imprimimos las caras detectasas*/
        System.out.println("CARAS ENCONTRADAS: "+arrayCaras.length);//CANTIDAD DE CARAS ENCONTRADAS
         
         return arrayCaras;
    }

   public Rect[] detectarOjos(Mat imagen)
   {
        detector = new CascadeClassifier(ojos_haarcascades);
       
        Rect[] arrayOjos=detectar(imagen);
        
        System.out.println("OJITOS ENCONTRADOS: "+arrayOjos.length);
        return arrayOjos;
   }
   
   public Rect[] detectarOjoDerecho(Mat imagen)
   {
       detector = new CascadeClassifier(ojoDerecho_haarcascades);
       
       return detectar(imagen);
   }
   
   public Rect[] detectarOjoIzquierdo(Mat imagen)
   {
       detector = new CascadeClassifier(ojoIzquierdo_haarcascades);
        return detectar(imagen);
   }
}
