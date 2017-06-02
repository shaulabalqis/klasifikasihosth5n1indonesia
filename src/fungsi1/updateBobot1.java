/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;
import static fungsi1.init1.*;

/**
 *
 * @author Shaula Balqis Z
 */
public class updateBobot1 {
    public static void start(){
        //System.out.println("update bobot"+inputKe);
        for (int h=0; h<=hidden1; h++){
            for (int j=1; j<=hidden2; j++){
                for (int m=0; m<4; m++)
                v[h][j][m]=v[h][j][m]+dv[h][j][m];
            }
        }
        for (int j=0; j<=hidden2; j++){
                for (int k=0; k<output; k++){
                    for (int m=0; m<4; m++)
                    w[j][k][m]=w[j][k][m]+dw[j][k][m];
                }
        }
        for (int i=0; i<=input; i++){
            for (int h=1; h<=hidden1; h++){
                for (int m=0; m<4; m++)
                u[i][h][m]=u[i][h][m]+du[i][h][m];
            }
        }
        
    }
}
