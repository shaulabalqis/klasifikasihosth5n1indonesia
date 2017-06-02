/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

import static fungsi1.readFileFasta1.*;
import static fungsi1.init1.*;
import java.text.DecimalFormat;

/**
 *
 * @author Shaula Balqis Z
 */
public class sigmoidNormalization1 {
    public static double sigmoidnormalization(double x){
        //DecimalFormat df=new DecimalFormat("0.00000000");
        double xmod;
        xmod=(Math.exp(x)-Math.exp(-x))/(Math.exp(x)+Math.exp(-x));
        return xmod;
    }
    public static void startTraining(){
        for (int i=0; i<jumlahDataTraining; i++){
            for (int j=0; j<useLength+1;j++){
                for (int m=0; m<4; m++){ 
                    dataTraining[i][j][m]=sigmoidnormalization(dataTraining[i][j][m]);
                    //System.out.print(data[i][j][m]+"  ");
                }
            }
            for (int m=0; m<4; m++){ 
                    targetTraining[i][0][m]=sigmoidnormalization(targetTraining[i][0][m]);
                    targetTraining[i][1][m]=sigmoidnormalization(targetTraining[i][1][m]);
            }
        }
        //System.out.println();
        //System.out.println();
    }
    
    public static void startInputSeq(double data[][]){
        for (int j=0; j<useLength+1;j++){
                for (int m=0; m<4; m++){ 
                    data[j][m]=sigmoidnormalization(data[j][m]);
                }
        }
}
    
    public static void startInputTarget(double [][] target){
        for (int k=0; k<output; k++){
            for (int m=0; m<4; m++){ 
                    target[k][m]=sigmoidnormalization(target[k][m]);
            }
        }  
    }
    
    public static void startTest(){
        for (int i=0; i<jumlahDataTest; i++){
            for (int j=0; j<useLength+1;j++){
                for (int m=0; m<4; m++){ 
                    dataTest[i][j][m]=sigmoidnormalization(dataTest[i][j][m]);
                    //System.out.print(data[i][j][m]+"  ");
                }
            }
            for (int m=0; m<4; m++){ 
                    targetTest[i][0][m]=sigmoidnormalization(targetTest[i][0][m]);
                    targetTest[i][1][m]=sigmoidnormalization(targetTest[i][1][m]);
            }
        }
        //System.out.println();
        //System.out.println();
    }
    
}
