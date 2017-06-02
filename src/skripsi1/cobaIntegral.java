/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package skripsi1;

/**
 *
 * @author Shaula Balqis Z
 */
public class cobaIntegral {
    public static void main(String[]args){
        double x=1;
        double turunan=fungsi1.sigmoidNormalization1.sigmoidnormalization(x);
        double integral=fungsi1.sigmoidDenormalization1.sigmoiddenormalization(turunan)+(x-fungsi1.sigmoidDenormalization1.sigmoiddenormalization(turunan));
        System.out.println(turunan+"\t"+integral);
    }
    
}
