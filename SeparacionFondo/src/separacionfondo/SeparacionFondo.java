/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package separacionfondo;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.io.Opener;
import ij.process.ImageProcessor;
import ij.IJ;

/**
 *
 * Octubre 2016
 * http://otroblogdetecnologias.blogspot.com
 * 
 * 
 * El programa toma una fotografia, aplica operaciones de morfologia matemática
 * a una imagen a color. Luego obtiene la silueta de la figura. Por último, modifica
 * una imagen a color dejando solamente la silueta de la fruta.
 * 
 * Esta secuencia de pasos es un paso intermedio para obtener contornos, areas en
 * procesamiento de imagenes, además sirve para analizar histogramas.
 * 
 * Funciona con imagenes de fondo blanco y de fondo negro.
 * - Aplica operaciones morfologicas para obtener una silueta de la fruta.
 * - Separa en una imagen con fondo negro a la fruta detectada.
 * -
 */
public class SeparacionFondo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //--------------------------------------------------------------------
        //-----------------------------------
        // Declaracion de Plugins
        //-----------------------------------
        
        //----------------------------------------------------
        SecuenciasMorfologicas secuencia=new SecuenciasMorfologicas();        
        CopiadoImagen copiar=new CopiadoImagen();

        //-----------------------------------------
        // Declaracion de variables iniciales
        //-----------------------------------------
        Opener opener = new Opener();                
        String directorioActualDefault = System.getProperty("user.dir"); //estableciendo el directorio del proyecto      

        //----------------------------------------------------------------------
        //descomentar según sea el ejemplo que se desee probar
        //----------------------------------------------------------------------
//        String imageFilePathDefault = directorioActualDefault+"/bdprueba/naranja-b-1.jpg"; //naranja buena
//        String imageFilePathDefault = directorioActualDefault+"/bdprueba/naranja-moho.jpg"; //funciona               
//        String imageFilePathDefault = directorioActualDefault+"/bdprueba/naranjas-podridas.jpg";//funciona        
//        String imageFilePathDefault = directorioActualDefault+"/bdprueba/naranja-p-2.jpg"; //naranja sin fondo    
        String imageFilePathDefault = directorioActualDefault+"/bdprueba/fru2.jpg";

        ImagePlus imagenColor= opener.openImage(imageFilePathDefault); //imagen color
        ImageProcessor imagenPColor= imagenColor.getProcessor();

        
        ImagePlus imagenBinaria; //=imagenDefault.crop();
        ImageProcessor imagenPBinaria;
        
        ImagePlus imagenFinal; //=imagenDefault.crop();
        
       
        //---------------------------------------------
        //--- Secuencia de operaciones morfologicas ---
        //---------------------------------------------        
        imagenColor.setTitle("1) Imagen a Color");
        imagenColor.show(); //mostrar primera imagen
        secuencia.setImagen(imagenColor);//inicializar con la imagen a color
        secuencia.run(imagenPColor); //lanzar proceso de secuencia

        imagenBinaria=secuencia.getResultado(); //resultadode silueta imagen binaria
//        imagenBinaria.updateAndDraw(); //update imagen

        imagenBinaria.setTitle("2) Imagen Binaria de Contorno");
        imagenBinaria.show(); //mostrar resultado de la imagen, binaria

        //---------------------------------------------
        //--- Copiar imagen utilizando silueta binaria ---
        //---------------------------------------------        
        copiar.setImages(imagenColor, imagenBinaria);//establece las imagenes        
        imagenPBinaria= imagenBinaria.getProcessor();
        copiar.run(imagenPBinaria); //pasa imagen binaria
   
        imagenFinal=copiar.getResultado(); //imagen copiada

        imagenFinal.setTitle("3) Imagen Final");
        imagenFinal.updateAndDraw();
        imagenFinal.show();


        //--------------------------------------------------------------------
    }//fin main
    
}//fin SeparacionFondo
