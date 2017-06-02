/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;
import static fungsi1.init1.*;
import static fungsi1.readFileFasta1.*;
/**
 *
 * @author Shaula Balqis Z
 */
public class backward1 {
    public static void start(){
        //System.out.println("backward start"+inputKe);
        
        //////////hitung MSE///////////
        for (int m=0; m<4; m++)//inisialisasi error
            e[inputKe][m]=0;
        for (int k=0; k<output; k++){
            for (int m=0; m<4; m++)//hitung error
                e[inputKe][m]=e[inputKe][m]+(Math.pow((targetTraining[inputKe][k][m]-y[k][m]),2));    
        }
        for (int m=0; m<4; m++)//olah error
            e[inputKe][m]=(1.0/2.0)*e[inputKe][m];
        ///////////////////////////////
        
        
        ///////output ke hidden2///////
        for (int k=0; k<output; k++){//hitung error dan perubahan bobot output ke hidden2
            for (int m=0; m<4; m++){
                errorW[k][m]=(targetTraining[inputKe][k][m]-y[k][m])*fungsi1.aktivasiBipolar1.sigmoidDerivatif(yin[k][m]);//hitung error output (selisih antara target dengan hasil yg diperoleh)
                dw[0][k][m]=alpha*errorW[k][m];//hitung perubahan bobot bias dari hidden2 ke output
            }
            for (int j=1; j<hidden2+1; j++){
                for (int m=0; m<4; m++)
                    dw[j][k][m]=alpha*errorW[k][m]*zz[j][m];//hitung perubahan bobot unit dari hidden2 ke output
            }
        }
        ///////////////////////////////
        
        ///////hidden2 ke hidden1///////
        for (int j=1; j<hidden2+1; j++){
            for (int m=0; m<4; m++)
                din2[j][m]=0;
            for (int k=0; k<output; k++){
                for (int m=0; m<4; m++)
                    din2[j][m]=din2[j][m]+errorW[k][m]*w[j][k][m];//hitung masukkan hidden2
            }
            for (int m=0; m<4; m++)
                errorV[j][m]=din2[j][m]*fungsi1.aktivasiBipolar1.sigmoidDerivatif(zzin[j][m]);//hitung error hidden2 ke hidden1
            for (int m=0; m<4; m++)
                dv[0][j][m]=alpha*errorV[j][m]; //hitung perubahan bobot bias hidden1 ke hidden2
            for (int h=1;h<hidden1+1; h++){//dari 1 karena untuk bias sudah diassign di baris atas
                for (int m=0; m<4; m++)
                    dv[h][j][m]=alpha*errorV[j][m]*z[h][m];//hitung perubahan bobot unit dari hidden1 ke hidden2
            }
        }
        ///////////////////////////////
        
        ///////hidden1 ke input///////
        for (int h=1; h<hidden1+1; h++){
            for (int m=0; m<4; m++)
                din1[h][m]=0;
            for (int j=1; j<hidden2; j++){
                for (int m=0; m<4; m++)
                    din1[h][m]=din1[h][m]+errorV[j][m]*v[h][j][m];//hitung masukkan hidden1
            }
            for (int m=0; m<4; m++)
                errorU[h][m]=din1[h][m]*fungsi1.aktivasiBipolar1.sigmoidDerivatif(zin[h][m]);//hitung error hidden2 ke hidden1
            for (int m=0; m<4; m++)
                du[0][h][m]=alpha*errorU[h][m]; //hitung perubahan bobot bias input ke hidden1
            for (int i=1;i<input+1; i++){//dari 1 karena untuk bias sudah diassign di baris atas
                for (int m=0; m<4; m++)
                    du[i][h][m]=alpha*errorU[h][m]*dataTraining[inputKe][i][m];//hitung perubahan bobot unit dari hidden1 ke hidden2
            }
        }
        ///////////////////////////////
    }
}
