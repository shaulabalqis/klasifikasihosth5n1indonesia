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
public class feedForward1 {
    public static boolean hitungZ=false;
    public static int bagi;
    public static Thread[] t=new Thread[15];
    public static int indeksThread;
    public static void thread(){
        for (indeksThread=0; indeksThread<t.length; indeksThread++){
            if (indeksThread==0){
                t[indeksThread]=(new Thread()
                {int i=indeksThread;
                @Override
                public void run(){
                        //System.out.println("t"+i);
                        int a=1;
                        int b=(i+1)*bagi;
                        if (hitungZ==true){
                            hitungZ(a,b);
                        }
                }
                });t[indeksThread].start();
            }
            else if (indeksThread!=0 && indeksThread<(t.length-1)){
                t[indeksThread]=(new Thread()
                {int i=indeksThread;
                @Override
                public void run(){
                        //System.out.println("t"+i);
                        int a=i*bagi;
                        int b=(i+1)*bagi;
                        if (hitungZ==true){
                            hitungZ(a,b);
                        }
                }
                });t[indeksThread].start();
            }
            else{
                t[indeksThread]=(new Thread()
                {int i=indeksThread;
                @Override                
                public void run(){
                        //System.out.println("t"+i);
                        int a=i*bagi;
                        int b=hidden1+1;
                        if (hitungZ==true){
                            hitungZ(a,b);
                        }
                }
                });t[indeksThread].start();
                
            }
        }
                
    }
    public static void hitungZ(int a, int b){
        for (int m=0; m<4; m++)//inisialisasi z0 (z bias)
                z[0][m]=1;
        for (int h=a; h<b; h++){//cari nilai z, kec z0
            System.arraycopy(u[0][h], 0, zin[h], 0, 4);
            for (int i=0; i<input+1; i++){//ulang sebanyak input+1 untuk memperoleh tiap zin (input+1=termasuk bias)
               for (int m=0; m<4; m++) 
                    zin[h][m]=zin[h][m]+u[i][h][m]*data[inputKe][i][m];
            }
            for (int m=0; m<4; m++)
                z[h][m]=fungsi1.aktivasiBipolar1.sigmoid(zin[h][m]);//menggunakan fungsi aktivasi thd zin untuk memperoleh z
        }
    }
    public static void start() throws InterruptedException {
        ///////input ke hidden1///////
        for (int m=0; m<4; m++)//inisialisasi z0 (z bias)
                z[0][m]=1;
        for (int h=1; h<hidden1+1; h++){ //cari nilai z, kec z0. +1 karena ada bias
            System.arraycopy(u[0][h], 0, zin[h], 0, 4);
            for (int i=1; i<input+1; i++){//ulang sebanyak input+1 untuk memperoleh tiap zin (bias sudah dimasukkan di baris atas)
               for (int m=0; m<4; m++) 
                    zin[h][m]=zin[h][m]+(u[i][h][m]*dataTraining[inputKe][i][m]);
            }
            for (int m=0; m<4; m++)
                z[h][m]=fungsi1.aktivasiBipolar1.sigmoid(zin[h][m]);//menggunakan fungsi aktivasi thd zin untuk memperoleh z
        }
        ///////////////////////////////
        
        ///////hidden1 ke hidden2///////
        for (int m=0; m<4; m++)//inisialisasi zz0 (zz bias)
                zz[0][m]=1;
        for (int j=1; j<hidden2+1; j++){//cari nilai zz, kec zz0. +1 karena ada bias
            System.arraycopy(v[0][j], 0, zzin[j], 0, 4);
            for (int h=1; h<hidden1+1; h++){//ulang sebanyak hidden1+1 untuk memperoleh tiap zin (bias sudah dimasukkan di baris atas) 
                for (int m=0; m<4; m++) 
                    zzin[j][m]=zzin[j][m]+(v[h][j][m]*z[h][m]);
            }
            for (int m=0; m<4; m++)
                zz[j][m]=fungsi1.aktivasiBipolar1.sigmoid(zzin[j][m]);
        }
        ///////////////////////////////
        
        ///////hidden2 ke output///////
        //for (int m=0; m<4; m++)//inisialisasi error
        //    e[inputKe][m]=0;
        for (int k=0; k<output; k++){
            System.arraycopy(w[0][k], 0, yin[k], 0, 4); //menambahkan bobot node hidden2 terlebih dahulu
            for (int j=1; j<hidden2+1; j++){//ulang sebanyak hidden2+1 untuk memperoleh tiap yin (bias sudah dimasukkan di baris atas)
                for (int m=0; m<4; m++)
                    yin[k][m]=yin[k][m]+(zz[j][m]*w[j][k][m]);
            }
            for (int m=0; m<4; m++){
                y[k][m]=fungsi1.aktivasiBipolar1.sigmoid(yin[k][m]);//menggunakan fungsi aktivasi thd yin untuk memperoleh y
                //System.out.print(y[k][m]+"\t"+targetTraining[inputKe][k][m]+"\t");
            }
            //System.out.println();
            //for (int m=0; m<4; m++)//hitung error
            //    e[inputKe][m]=e[inputKe][m]+((targetTraining[inputKe][k][m]-y[k][m])*(targetTraining[inputKe][k][m]-y[k][m]));    
        }
        //for (int m=0; m<4; m++)//olah error
        //    e[inputKe][m]=(1.0/2.0)*e[inputKe][m];
        /////////////////////////////// 
        
        }
        
        
        /*bagi=(int)(hidden/t.length);
        hitungZ=true;
        thread();
        for (Thread t1 : t) {
            t1.join();
        }*/
}
