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
import UI1.trainingProc1;
import static fungsi1.init1.*;
import static fungsi1.readFileFasta1.*;
import java.text.DecimalFormat;
public class testDataDefault1 {
    public static String temp;
    public static double beta;
    public static boolean[][] hasil;
    public static double[][] eTest;
    public static double[] eTestRata2;
    public static int truePos,trueNeg,falsePos,falseNeg;
    public static double accuracy,recall,precission,TNR,gMeans1,gMeans2,fMeasure;
    public static DecimalFormat df=new DecimalFormat("0.00000000");
    public static void feedforward(int x){
    ///////////////////////////FEEDFORWARD///////////////////////////
                    
                    ///////input ke hidden1///////
                    for (int m=0; m<4; m++)//inisialisasi z0 (z bias)
                            z[0][m]=1;
                    for (int h=1; h<hidden1+1; h++){ //cari nilai z, kec z0. +1 karena ada bias
                        System.arraycopy(u[0][h], 0, zin[h], 0, 4);
                        for (int i=1; i<input+1; i++){//ulang sebanyak input+1 untuk memperoleh tiap zin (bias sudah dimasukkan di baris atas)
                            for (int m=0; m<4; m++) 
                                zin[h][m]=zin[h][m]+(u[i][h][m]*dataTest[x][i][m]);
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
    
    public static void hitungError(int x){
        for (int m=0; m<4; m++)//inisialisasi error
            eTest[x][m]=0;
        for (int k=0; k<output; k++){
            for (int m=0; m<4; m++)//hitung error
                eTest[x][m]=eTest[x][m]+((targetTest[x][k][m]-y[k][m])*(targetTest[x][k][m]-y[k][m]));
        }
        System.out.print("error\t");
        for (int m=0; m<4; m++){//olah error
            eTest[x][m]=(1.0/2.0)*eTest[x][m];
            System.out.print(eTest[x][m]+"\t");
            //temp=temp+eTest[m]+"\t";
        }
        System.out.println();
        //temp=temp+"\n";
        //trainingProc1.detailProc.setText(temp);
    }
    
    public static void hitungErrorRata2(){
        DecimalFormat df=new DecimalFormat("0.00000000");
        eTestRata2=new double [4];
        temp=temp+"Rata-rata Kesalahan Klasifikasi :\n";
        for (int m=0; m<4; m++){
            eTestRata2[m]=0;
            for (int i=0; i<jumlahDataTest; i++){
                eTestRata2[m]=eTestRata2[m]+eTest[i][m];
            }
            eTestRata2[m]=eTestRata2[m]/jumlahDataTest;
            temp=temp+df.format(eTestRata2[m])+"\t";
        }
        temp=temp+"\n";
    }
    
    public static void hitungMatriksKejadian(int dataKe){
        //for (int dataKe=0; dataKe<jumlahDataTest; dataKe++){
            if (hasil[dataKe][0]==true&&hasil[dataKe][1]==true)
                truePos++;
            else if (hasil[dataKe][0]==true&&hasil[dataKe][1]==false)
                falsePos++;
            else if (hasil[dataKe][0]==false&&hasil[dataKe][1]==true)
                falseNeg++;
            else if (hasil[dataKe][0]==false&&hasil[dataKe][1]==false)
                trueNeg++;
        //}
        /*hitungRecall();
        hitungPrecission();
        hitungAccuracy();
        hitungGMeans1();
        hitungGMeans2();
        hitungFMeasure();
        System.out.println("\nR\t:\t"+recall+"\nP\t:\t"+precission+"\nA\t:\t"+accuracy+"\nGM1\t:\t"+gMeans1+"\nGM2\t:\t"+gMeans2+"\nFM\t:\t"+fMeasure);*/
    }
    public static void hitungPerforma(){
        hitungAccuracy();
        hitungRecall();
        hitungPrecission();        
        hitungTNR();
        hitungFMeasure();
        //System.out.println("\nR\t:\t"+recall+"\nP\t:\t"+precission+"\nA\t:\t"+accuracy+"\nGM1\t:\t"+gMeans1+"\nGM2\t:\t"+gMeans2+"\nFM\t:\t"+fMeasure);
    }
    
    public static void hitungAccuracy(){
        accuracy=((double)truePos+(double)trueNeg)/((double)trueNeg+(double)falsePos+(double)falseNeg+(double)truePos);
        temp=temp+"Accuracy\t: "+df.format(accuracy)+"\n";
    }
    public static void hitungRecall(){
        recall=(double)truePos/((double)falseNeg+(double)truePos);
        temp=temp+"Recall\t: "+df.format(recall)+"\n";
    }
        public static void hitungPrecission(){
        precission=(double)truePos/((double)falsePos+(double)truePos);
        temp=temp+"Precission\t: "+df.format(precission)+"\n";
    }
    public static void hitungTNR(){
        TNR=(double)trueNeg/((double)trueNeg+(double)falsePos);
        temp=temp+"TNR\t: "+df.format(TNR)+"\n";
    }
    public static void hitungFMeasure(){
        fMeasure=(2/((1/precission)+(1/recall)));
        temp=temp+"F-Measure\t: "+df.format(fMeasure)+"\n";
    }
    public static void hitungGMeans1(){
        gMeans1=Math.sqrt(recall*precission);
        temp=temp+"g-Means1\t: "+df.format(gMeans1)+"\n";
    }
    public static void hitungGMeans2(){
        TNR=(double)trueNeg/((double)trueNeg+(double)falsePos);
        gMeans2=Math.sqrt(recall*TNR);
        temp=temp+"g-Means2\t: "+df.format(gMeans2)+"\n";
    }
    public static void start(){
        //fungsi1.sigmoidNormalization1.startTest();
        hasil=new boolean[jumlahDataTest][2];//indeks 0 untuk keluaran, indeks 1 untuk target
        eTest=new double[jumlahDataTest][4];
        boolean hostHuman;
        //boolean sama=true;
        System.out.println("Jumlah data test :\t"+jumlahDataTest+"\n");
        temp=trainingProc1.detailProc.getText();
        truePos=0;trueNeg=0;falsePos=0;falseNeg=0;beta=100;
        for (int dataKe=0; dataKe<jumlahDataTest; dataKe++){
            feedforward (dataKe);
            hitungError(dataKe);
            for (int k=0; k<2; k++){
                System.out.print("y"+k+"\t");
                for (int m=0; m<4; m++){
                    System.out.print(y[k][m]+"\t");
                }
                System.out.println();
            }    
            //double[] output={0,0};
            int indikasiTrue=0,indikasiFalse=0;
            //double selisihTrue,selisihFalse;
            //for (int k=0; k<2; k++){
                /*for (int m=1; m<4; m++){
                    if ((y[k][m]<0)&&(y[k][m-1]>=0) || (y[k][m]>=0)&&(y[k][m-1]<0))
                        sama=false;//cek apakah tanda pada masing2 node keluaran sama
                }*/
                /*for (int m=0; m<4; m++){
                    output[k]=output[k]+y[k][m];
                }
                output[k]=output[k]/4.0000000;*/
                for (int m=0; m<4; m++){
                    //selisihTrue=Math.pow(fungsi1.sigmoidNormalization1.sigmoidnormalization(1)-y[k][m],2);
                    //selisihFalse=Math.pow((y[k][m]-fungsi1.sigmoidNormalization1.sigmoidnormalization(-1)),2);
                    if (y[0][m]>=0)
                        indikasiTrue++;
                    else if (y[0][m]<0)
                        indikasiFalse++;
                    else if (y[1][m]<0)
                        indikasiTrue++;
                    else if (y[1][m]>=0)
                        indikasiFalse++;
                }
            //}
            //if (sama==true){
                hostHuman=indikasiTrue>=indikasiFalse;
                hasil[dataKe][0]=hostHuman;
                hasil[dataKe][1]=dataRawTest[4][dataKe].equals("human");
                hitungMatriksKejadian(dataKe);
                System.out.println("Keluaran:Target\t"+hasil[dataKe][0]+"\t"+hasil[dataKe][1]);
                System.out.println();
            //}
            //else{
            //    temp=temp+"\n\nHasil "+dataKe+" tidak diketahui";
            //    trainingProc1.detailProc.setText(temp);
            //    break;
            //}
        }
        System.out.println("\nTP  :\t"+truePos+"\nTN  :\t"+trueNeg+"\nFN  :\t"+falseNeg+"\nFP  :\t"+falsePos);
        //if (sama==true){
            temp=temp+"\n\nHasil Uji\n\n";
            temp=temp+("TP\t: "+truePos+"\nTN\t: "+trueNeg+"\nFN\t: "+falseNeg+"\nFP\t: "+falsePos+"\n\n");
            hitungPerforma();
            hitungErrorRata2();            
            trainingProc1.detailProc.setText(temp);
        //}
    }
}

