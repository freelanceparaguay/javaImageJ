package separacionfondo;


//import ij.IJ;
//import ij.ImagePlus;
//import ij.io.Opener;
import ij.plugin.filter.PlugInFilter;
//import ij.process.ImageProcessor;


import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.io.Opener;
import java.awt.*;
import ij.plugin.*;

/*
 * Octubre 2016
 * http://otroblogdetecnologias.blogspot.com

Recibe una imagen en color , la procesa y envia el resultado binario. La imagen
es una imagen binaria, se utiliza para el cálculo del área en pixeles.

*/

public class CopiadoImagen implements PlugInFilter {

    
    ImagePlus imColor; //imagen original
    ImagePlus imBinario; //imagen binaria recibida    

    //establece el valor de las dos imagenes, espera una imagen binaria que ayudara
    // a construir la silueta
    public void setImages(ImagePlus imColor, ImagePlus imBinario){
        this.imColor=imColor;
        this.imBinario=imBinario;
    }//establecer imagenes
    
    public ImagePlus getResultado(){
        return this.imColor.crop();
    }





    
    public int setup(String string, ImagePlus iBinario) {
//        this.imBinario=iBinario;                
        return DOES_ALL;
    }//fin setup
    
    public void run(ImageProcessor ip) {

        //apertura de imagen
//        Opener opener = new Opener();                
//        String directorioActualDefault = System.getProperty("user.dir"); //estableciendo el directorio del proyecto
//        String imageFilePathDefault = directorioActualDefault+"/bdprueba/naranja-b-1.jpg";

        //----------------------------------------------------------------
//        imColor= opener.openImage(imageFilePathDefault);//imagen original              
        //--------------------------------------------------------------
        //ImageProcessor iPBinario= imBinario.getProcessor(); //imagen binaria

        ImageProcessor iPBinario= imBinario.getProcessor(); //imagen binaria
        ImageProcessor iPColor= imColor.getProcessor(); //imagen color                

        //--------------------------------------------------------------
        int W = iPBinario.getWidth(); //tamano ancho
        int H = iPBinario.getHeight(); //tamano alto
        
        int banderaBlanco=0; //reinicia bandera
        int u=0; //columna
        int v=0; //fila
        int v1=0; //columna tope desde izquierda a derecha
        int v2=0; //columna tope desde derecha a izquierda
        int pBinario=0; //variable de pixel
        int pColor=0; //variable de pixel        
        
        int NEGRO=0; //color del pixel a buscar

        IJ.log("Valor "+" W="+W+" H="+H);
        
        for(u = 0; u < H; u++){ //H
            //busca de izquierda a derecha
            for(v1 = 0; v1 < W; v1++){ //W
                pBinario = iPBinario.getPixel(v1, u);

//                 String str = Integer.toHexString(num);
//                IJ.log("Escribiendo en negro"+" u="+u+" v="+v1+" pixel="+Integer.toHexString(pBinario));
                
                if(pBinario==NEGRO){
                    //copiar desde imagen color a binario
                    iPColor.putPixel(v1, u, pBinario); //poner pixel color en binario
//                    IJ.log("Escribiendo en color"+" u="+u+" v1="+v1+" pixel="+pColor+"  "+pBinario);
                }//if    
            }// for v=0
        }// for u=0
        
//        imColor.setProcessor(iPColor);
        //--------------------------------------------------------------
    }//run    
    
    
    
    
}//fin My_secuencia_area
