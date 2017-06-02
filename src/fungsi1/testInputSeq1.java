/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;
import UI1.trainingProc1;
import static fungsi1.init1.*;
import static fungsi1.readFileFasta1.*;
import java.text.DecimalFormat;
/**
 *
 * @author Shaula Balqis Z
 */
public class testInputSeq1 {
    public static double[][] targetTestValue=new double[output][4];
    public static double[] eTest=new double[4] ;
    public static boolean cekError;
    public static String temp;
    public static void hitungError(){
        DecimalFormat df=new DecimalFormat("0.00000000");
        for (int m=0; m<4; m++)//inisialisasi error
            eTest[m]=0;
        for (int k=0; k<output; k++){
            for (int m=0; m<4; m++)//hitung error
                eTest[m]=eTest[m]+Math.pow((targetTestValue[k][m]-y[k][m]),2);
        }
        System.out.print("error\t");
        for (int m=0; m<4; m++){//olah error
            eTest[m]=(1.0/2.0)*eTest[m];
            System.out.print(eTest[m]+"\t");
            temp=temp+df.format(eTest[m])+"\t";
        }
        System.out.println();
        temp=temp+"\n";
    }
    
    public static void feedforward (double [][] dataTestValue){
        ///////////////////////////FEEDFORWARD///////////////////////////
                    
                    ///////input ke hidden1///////
                    for (int m=0; m<4; m++)//inisialisasi z0 (z bias)
                            z[0][m]=1;
                    for (int h=1; h<hidden1+1; h++){ //cari nilai z, kec z0. +1 karena ada bias
                        System.arraycopy(u[0][h], 0, zin[h], 0, 4);
                        for (int i=1; i<input+1; i++){//ulang sebanyak input+1 untuk memperoleh tiap zin (bias sudah dimasukkan di baris atas)
                            for (int m=0; m<4; m++) 
                                zin[h][m]=zin[h][m]+(u[i][h][m]*dataTestValue[i][m]);
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
                    for (int k=0; k<output; k++){
                        System.arraycopy(w[0][k], 0, yin[k], 0, 4); //menambahkan bobot node hidden2 terlebih dahulu
                        for (int j=1; j<hidden2+1; j++){//ulang sebanyak hidden2+1 untuk memperoleh tiap yin (bias sudah dimasukkan di baris atas)
                            for (int m=0; m<4; m++)
                                yin[k][m]=yin[k][m]+(zz[j][m]*w[j][k][m]);
                        }
                        for (int m=0; m<4; m++)
                            y[k][m]=fungsi1.aktivasiBipolar1.sigmoid(yin[k][m]);//menggunakan fungsi aktivasi thd yin untuk memperoleh y
                    }
                    //////////////////////////////////////////////////////////////////////
        
    } 
    
    public static void start(String dataString){
            //Thread thread = new Thread(new Runnable(){     
            //    @Override
            //    public void run(){
                    //Add Sequence Value//
                    char [] dataTest=dataString.toCharArray();
                    double[][] dataTestValue=new double[useLength+1][4];//+1 untuk bias
                    for (int k=0; k<useLength; k++){
                        dataTest[k]=Character.toUpperCase(dataTest[k]);//normalisasi menjadi uppercase
                    }
                    int p,n=0;
                    for (int m=0; m<4; m++)
                        dataTestValue[0][m]=1;//tambah bias
                    while (n<useLength){
                        if (dataTest[n]=='A')
                            p=0;
                        else if (dataTest[n]=='T')
                            p=1;
                        else if (dataTest[n]=='G')
                            p=2;
                        else if ((dataTest[n]=='C')||(dataTest[n]=='S'))
                            p=3;
                        else
                            p=4;
                        System.arraycopy(def[p], 0, dataTestValue[n+1], 0, 4);
                        n++;
                    }
                    fungsi1.sigmoidNormalization1.startInputSeq(dataTestValue);
                    feedforward (dataTestValue);
                    for (int k=0; k<2; k++){
                        System.out.print("y"+k+"\t");
                        for (int m=0; m<4; m++){
                            System.out.print(y[k][m]+"\t");
                        }
                        System.out.println();
                    }   
                    boolean hostHuman;
                    int indikasiTrue=0,indikasiFalse=0;
                    //double selisihTrue,selisihFalse;
                    for (int k=0; k<2; k++){
                        for (int m=0; m<4; m++){
                            if (y[0][m]>=0)
                                indikasiTrue++;
                            else if (y[0][m]<0)
                                indikasiFalse++;
                            else if (y[1][m]<0)
                                indikasiTrue++;
                            else if (y[1][m]>=0)
                                indikasiFalse++;
                        }
                        /*for (int m=0; m<4; m++){
                            System.out.print(y[k][m]+"\t");
                            if (k==0){
                                if(y[k][m]>=0){
                                    hostHuman=true;
                                }
                            }
                        }*/
                    }
                    hostHuman=indikasiTrue>=indikasiFalse;
                    temp=trainingProc1.detailProc.getText();
                    System.out.println("Keluaran\t"+hostHuman);
                    if (hostHuman==true)
                        temp=temp+"\n\nHasil Uji\n"+"Keluaran\t: Host Manusia";
                    else
                        temp=temp+"\n\nHasil Uji\n"+"Keluaran\t: Host Bukan Manusia";
                    
                    if (cekError==true){
                        System.out.println("Target\t"+trainingProc1.targetTrue.isSelected());
                        if (trainingProc1.targetTrue.isSelected()==true)
                            temp=temp+"\nTarget\t: Host Manusia\nKesalahan Klasifikasi :\n";
                        else
                            temp=temp+"\nTarget\t: Host Bukan Manusia\nKesalahan Klasifikasi :\n";
                        hitungError();
                    }
                    trainingProc1.detailProc.setText(temp);
                //}
            //});
            //thread.start();
    }
}
