
package variaspruebasimagej;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class My_Inverter implements PlugInFilter{

    @Override
    public int setup(String string, ImagePlus ip) {
        return PlugInFilter.DOES_ALL;
    }

    @Override
    public void run(ImageProcessor ip) {
        int W = ip.getWidth();
        int H = ip.getHeight();
        for(int u = 0; u < W; u++){
            for(int v = 0; v < H; v++){
                int p = ip.getPixel(u, v);
                ip.putPixel(u, v, 255-p);
            }// for v=0
        }// for u=0
    } //run 
}//fin clase My_inverter
