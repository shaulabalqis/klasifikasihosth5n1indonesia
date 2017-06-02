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
public class sigmoidDenormalization1 {
    public static double sigmoiddenormalization(double x){
        double xmod=(Math.log(Math.exp(2*x)+1)-x);
        return xmod;
    }
    
}
