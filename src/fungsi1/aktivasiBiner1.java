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
public class aktivasiBiner1 {
    public static double sigmoid(double k){
        double hasil=1.0000000000/(1+Math.exp(-1*k));
        return hasil;
    }
    public static double sigmoidDerivatif(double k){
        double hasil=sigmoid(k)*(1-sigmoid(k));
        return hasil;
    }
    
}
