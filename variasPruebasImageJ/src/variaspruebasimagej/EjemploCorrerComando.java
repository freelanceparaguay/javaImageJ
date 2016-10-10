/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variaspruebasimagej;


import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

/*
 Propone correr comandos sobre una imagen y devolver los resultados al 
programa principal.
Recibe una imagen y devuelve la imagen invertida

*/

public class EjemploCorrerComando implements PlugInFilter {
    ImagePlus im; //variable de imagen interna
    
    public int setup(String string, ImagePlus im) {
        this.im=im;        
        return DOES_ALL;
    }//fin setup
    
    public void run(ImageProcessor ip) {
        //im.unlock(); //desbloquear im, esto recomendo el libro de BURGUER, pero no funciono, por eso de desactivó
        IJ.run(im, "Invert", ""); //correr comando
        //im.lock(); //bloquear im       
    } //run    
}//fin correr comando
