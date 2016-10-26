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
Las secuencias generales son las siguientes:

- 1) Aplicacion de operaciones morfologicas en imagenes binarias para generar una figura
    de contorno.
- 2) Relleno del contorno en la imagen binaria.
- 3) Conteo de pixeles para conocer el area.
- 4) Aplicación de opraciones morfologicas para cerrar agujeros


*/

public class SecuenciasMorfologicas implements PlugInFilter {

    ImagePlus im; //variable de imagen interna, contiene el resultado final
    int cantidadPixeles; //variable de conteo de pixeles, es utilizada para calcular el area de la figura binaria

    public void setImagen(ImagePlus im){
        this.im=im;
    } //    
    
    public int getArea(){
        return this.cantidadPixeles;
    } //

    public ImagePlus getResultado(){
        return this.im;
    } //

    
    public int setup(String string, ImagePlus im) {
        this.im=im;        
        return DOES_ALL;
    }//fin setup
    
//    public void run() {
    public void run(ImageProcessor ip) {        
        //--------------------------------------------------------------        
        //-- Aplicacion de operaciones morfologicas
        //--------------------------------------------------------------        
        ImagePlus Iorig=im.crop();//imagen original        
        ImagePlus IB2 = Iorig.crop();//imagen binaria
        Prefs.blackBackground = false;
        IJ.run(IB2, "Find Edges", ""); //resaltar bordes

        ImagePlus IB3 = IB2.crop();
        IJ.run(IB3, "Make Binary", ""); //convertir a imagen binaria

        
        ImagePlus IB4 = IB3.crop();
        IJ.run(IB4, "Options...", "iterations=1 count=6 do=Dilate");
        
        ImagePlus IB5 =IB4.crop();
        
        //--------------------------------------------------------------        
        //-- Relleno de contorno
        //--------------------------------------------------------------                
        //--------------------------------------------------------------
        ImageProcessor iPIB5= IB5.getProcessor();
        //--------------------------------------------------------------
        int W = iPIB5.getWidth(); //tamano ancho
        int H = iPIB5.getHeight(); //tamano alto
        int banderaBlanco=0; //reinicia bandera
        int u=0; //columna
        int v=0; //fila
        int v1=0; //columna tope desde izquierda a derecha
        int v2=0; //columna tope desde derecha a izquierda
        int p=0; //variable de pixel
        int BLANCO=255; //color del pixel a buscar en imagenes binarias es 0 fondo y 255 valor
/*
El propósito de este algoritmo es el de crear una silueta bien formada de la fruta.
La imagen es una matriz, se procesa con dos bucles, el primero se para en una fila y recorre
desde izquierda a derecha hasta encontrar un pixel del borde
El segundo se para en la misma fila y recorre desde el tope de la derecha hasta
que encuentra el borde de la figura. 
Una vez que tiene ambos topes rellena con valores para formar la figura.
*/                
        
        //recorrido buscando el primer punto
        for(u = 0; u < H; u++){
            //busca de izquierda a derecha
            for(v1 = 0; v1 < W; v1++){
                p = iPIB5.getPixel(v1, u );
                if(p==BLANCO){
//                    IJ.log("PRIMERO--> encontro un 255 v1="+v1);
                    banderaBlanco=1; //encontro
                    break;
                }//if    
            }// for v=0
            
            if(banderaBlanco==1){
                //busca desde derecha a izquierda
                for(v2 = W; v2 > 0; v2--){
                    p = iPIB5.getPixel(v2, u);
                    if(p==BLANCO){
//                        IJ.log("SEGUNDO--> encontro un 255 v1="+v2);
                        break;
                    }//if    
                }// for v=0
//                IJ.log("Tengo los dos puntos u="+u+" v1="+v1+" , v2="+v2);
            }//bandera

            //-----------------------------------
            //rellena de bits la figura
            for(v = v1; v < v2; v++){
                iPIB5.putPixel(v, u, BLANCO);
            }// for v=0
        }// for u=0

        //-----------------------------------------------------------------
        //para completar se realiza una cerradura para rellenar agujeros
        //-----------------------------------------------------------------
        IJ.run(IB5, "Close-", "");
        //-----------------------------------------------------------------


        this.im=IB5.crop();

        //--------------------------------------------------------------        
        //-- Conteo de pixeles
        //--------------------------------------------------------------        
        //--------------------------------------------------------------
        ImageProcessor imP= im.getProcessor();
        //--------------------------------------------------------------
        cantidadPixeles=0;        
        for(u = 0; u < H; u++){
            //busca de izquierda a derecha
            for(v = 0; v < W; v++){
                p = imP.getPixel(v, u);
                if(p==BLANCO){
                    cantidadPixeles++;
                }//if                    
            }//for
        }//for
        //--------------------------------------------------------------

    }//run    
    
    
    
    
}//fin SecuenciasMorfologicas
