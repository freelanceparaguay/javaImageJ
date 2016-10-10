/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variaspruebasimagej;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;



public class Ejercicio_3_2 implements PlugInFilter{
    ImagePlus im;
    
    
    public int setup(String string, ImagePlus im) {
        this.im=im;
        return DOES_8G+NO_CHANGES;
    }//setup

    public void run(ImageProcessor ip) {
        //pbtener histograma
        int [] histograma= ip.getHistogram();
        int K=histograma.length;
        
        //crear imagen de histograma
        ImageProcessor hip=new ByteProcessor(K,100);
        hip.setValue(255);
        hip.fill();

        //---------------------------------
        
        for(int i=0; i < 255; i++){
            int cantidad=histograma[i];
            IJ.log("["+i+"]"+Integer.toString(histograma[i]));            
            
            for(int j=0; j< cantidad; j++){
                hip.putPixel(i, j, 0);
            }//for j
        }// for i
        //---------------------------------        
        //colocar titulos
        //String imTitle=im.getShortTitle();
        String imTitle=" no lo se";        
        String histTitle="Histograma "+ imTitle;
        
        //dibujar histograma de imagenes
        ImagePlus him=new ImagePlus(imTitle,hip);
        him.show();
        
        
/*        for(int u = 0; u < w; u++){
            for(int v = 0; v < h; v++){
                int p = ip.getPixel(u, v);
                ip.putPixel(u, v, 255-p);
            }
        }
*/

}  


    
}//Ejercicio_3_2
