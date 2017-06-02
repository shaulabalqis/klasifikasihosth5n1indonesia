/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

/**
 *
 * @author Shaula Balqis Z
 */
public class aktivasiTanH1 {
    public static double tanH(double k){
        double hasil=(2.0000000000/(1+Math.exp(-2*k)))-1;
        return hasil;
    }
    public static double tanHDerivatif(double k){
        double hasil=1.0000000000-(tanH(k)*tanH(k));
        return hasil;
    }
    
}
