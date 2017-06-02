/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

//import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author Shaula Balqis Z
 */

public class nguyenWidrowInitialization1 {
    public static double uBias, wBias, vBias, rangeMin, rangeMax;
    public static double temp;
    public static double [] uOld, uNew, wNew, vOld,vNew;
    
    public static double hitungBeta(int jumlah1, int jumlah2) {
        double beta;
        beta=0.7*Math.pow(jumlah2, (1.0/jumlah1));
        return beta;
    }
    public static double[] randomize(int size,double rangeMin,double rangeMax){
        Random r = new Random();
        double[] result = r.doubles(size, rangeMin, rangeMax).toArray();
        return result;
    }
    /*public static void initAbsU(){
        uOld=new double [init1.input];
        uOld=randomize(uOld.length,rangeMin, rangeMax);
       // for (int i=0; i<uOld.length; i++)
       //    uOld[i]=randomize(-0.5,0.5);
        double temp2=0;
        for (int j=0; j<uOld.length; j++)
            temp2=temp2+(Math.pow (uOld[j],2));
        temp=Math.sqrt(Math.abs(temp2));
    }
    public static void reInitU(){
        uNew=new double [init1.input];
        double beta=hitungBeta(fungsi1.init1.input, fungsi1.init1.hidden1);
        for (int i=0; i<uNew.length; i++){
          uNew[i]=(beta*uOld[i])/temp; 
        }
        
    }
    public static void initAbsV(){
        vOld=new double [init1.hidden1];
        vOld=randomize(vOld.length,rangeMin, rangeMax);
       // for (int i=0; i<uOld.length; i++)
       //    uOld[i]=randomize(-0.5,0.5);
        double temp2=0;
        for (int j=0; j<vOld.length; j++)
            temp2=temp2+(Math.pow (vOld[j], 2));
        temp=Math.sqrt(Math.abs(temp2));
    }
    public static void reInitV(){
        vNew=new double [init1.hidden1];
        double beta=hitungBeta(fungsi1.init1.hidden1, fungsi1.init1.hidden2);
        for (int i=0; i<vNew.length; i++){
          vNew[i]=(beta*vOld[i])/temp; 
        }
        
    }*/
    public static double[] initAbs(int jumlah1){
        temp=0;
        double [] arrayOld =new double [jumlah1];
        arrayOld=randomize(arrayOld.length,rangeMin, rangeMax);
        double temp2=0;
        for (int i=0; i<arrayOld.length; i++)
            temp2=temp2+(Math.pow (arrayOld[i], 2));
        temp=Math.sqrt(Math.abs(temp2));
        return arrayOld;
    }
    public static double [] reInit(double [] arrayOld,int jumlah1, int jumlah2){
        double [] arrayNew=new double [jumlah1];
        double beta=hitungBeta(jumlah1, jumlah2);
        for (int i=0; i<arrayNew.length; i++){
          arrayNew[i]=(beta*arrayOld[i])/temp; 
        }
        return arrayNew;
    }
    public static void initU(){
        uOld=null; uNew=null;
        uOld=initAbs(fungsi1.init1.input);
        uNew=reInit(uOld, fungsi1.init1.input, fungsi1.init1.hidden1);
    }
    public static void initV(){
        vOld=null; vNew=null;
        vOld=initAbs(fungsi1.init1.hidden1);
        vNew=reInit(vOld, fungsi1.init1.hidden1, fungsi1.init1.hidden2);
        //vNew=new double[init1.hidden1];
        //vNew=randomize(vNew.length,-0.5,0.5);
    }
    public static void initW(){
        wNew=new double[init1.hidden2];
        wNew=randomize(wNew.length,rangeMin, rangeMax);
    }
    
    public static void initBias(){
        double betaV, betaU;
        for (int k=0; k<fungsi1.init1.output; k++){
            fungsi1.init1.w[0][k]=randomize(4,rangeMin, rangeMax);
        }
        betaV=hitungBeta(fungsi1.init1.hidden1, fungsi1.init1.hidden2);
        for (int j=1; j<fungsi1.init1.hidden2+1; j++){
            fungsi1.init1.v[0][j]=randomize(4,-1*betaV,betaV);
        }
        betaU=hitungBeta(fungsi1.init1.input, fungsi1.init1.hidden1);
        for (int h=1; h<fungsi1.init1.hidden1+1; h++){
            fungsi1.init1.u[0][h]=randomize(4,-1*betaU,betaU);
        }
    }
    public static void start() {
        rangeMin=-0.5; rangeMax=0.5;
        for (int h=1; h<fungsi1.init1.hidden1+1; h++){ 
            initU();
            for (int i=1; i<fungsi1.init1.input+1;i++){//dari 1 karena 0 untuk bias
                for (int m=0; m<4; m++)
                    fungsi1.init1.u[i][h][m]=uNew[i-1];
            }
        }
        
        for (int j=1; j<fungsi1.init1.hidden2+1; j++){ 
            initV();
            for (int h=1; h<fungsi1.init1.hidden1+1; h++){//dari 1 karena 0 untuk bias
                for (int m=0; m<4; m++)
                    fungsi1.init1.v[h][j][m]=vNew[h-1];
            }
        }
        
        for (int k=0; k<fungsi1.init1.output; k++){
            initW();
            for (int j=1; j<fungsi1.init1.hidden2+1; j++){//dari 1 karena 0 untuk bias
                for (int m=0; m<4; m++)
                    fungsi1.init1.w[j][k][m]=wNew[j-1];
            }
        }
        initBias(); 
        /*System.out.println("W :");
        for (int j=0; j<fungsi.init.hidden+1; j++){//w=hidden ke output
            for (int k=0; k<fungsi.init.output; k++){
                for(int m=0; m<4; m++)
                    System.out.print(fungsi.init.w[j][k][m]+"\t");
            System.out.println();
            }
        }
        System.out.println("\n\nV :");
        for (int i=0; i<fungsi.init.input+1; i++){//v=input ke hidden
            for (int j=1; j<fungsi.init.hidden+1; j++){
                for(int m=0; m<4; m++)
                    System.out.print(fungsi.init.v[i][j][m]+"\t");
            System.out.println();
            }
        }*/
    } 
   
    
}
