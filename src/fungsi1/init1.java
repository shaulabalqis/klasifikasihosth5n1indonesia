/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;
import static fungsi1.readFileFasta1.*;

/**
 *
 * @author Shaula Balqis Z
 */
public class init1 {
    public static int inputKe, hidden1, hidden2, output, input, epochMax;
    public static double eTotalRata2;
    //public static int target[][]={{1,1,1,1},{-1,-1,-1,-1}};
    //public static double[][][] targetTraining;
    public static double []   eTotal;
    public static double  threshold,alpha;
    public static double[][][] w, v,u,du,  dw, dv;
    public static double [][] zin, z, yin,y, errorW,errorV,errorU,din1,din2, e,zz,zzin;
    //private static String[] args;
    public static void inisialisasi(double nilaiAlpha, int nilaiHidden1,int nilaiHidden2, int nilaiEpochmax, double nilaiToleransierror){
        output=2;input=fungsi1.readFileFasta1.useLength;hidden1=nilaiHidden1;hidden2=nilaiHidden2;//panjang seq min(input)=sesuai sequence yg paling pendek di data training
        epochMax=nilaiEpochmax;
        alpha=nilaiAlpha;
        threshold=nilaiToleransierror;
        //targetTraining=new double [jumlahDataTraining][output][4];
        eTotal=new double[4];
        w=new double [hidden2+1][output][4];
        u=new double [input+1][hidden1+1][4];
        v=new double [hidden1+1][hidden2+1][4];
        du=new double [input+1][hidden1+1][4];
        dw=new double [hidden2+1][output][4];
        dv=new double [hidden1+1][hidden2+1][4];
        zin=new double [hidden1+1][4];
        z=new double [hidden1+1][4];
        zzin=new double [hidden2+1][4];
        zz=new double [hidden2+1][4];
        yin=new double [output][4];
        y=new double [output][4];
        errorW=new double[output][4];
        din2=new double [hidden2+1][4];
        errorV=new double [hidden2+1][4];
        din1=new double [hidden1+1][4];
        errorU=new double [hidden1+1][4];
        e=new double [jumlahDataTraining][4];
        /*for (int n=0;n<jumlahDataTraining; n++){//input target
            for (int m=0; m<4; m++){
                targetTraining[n][0][m]=1;
                targetTraining[n][1][m]=-1;
            }
        }*/
        
    }
    
    public static void emptying(){
        w=null;
        u=null;
        v=null;
        du=null;
        dw=null;
        dv=null;
        zin=null;
        z=null;
        zzin=null;
        zz=null;
        yin=null;
        y=null;
        errorW=null;
        din2=null;
        errorV=null;
        din1=null;
        errorU=null;
        e=null;
    }
    
}
