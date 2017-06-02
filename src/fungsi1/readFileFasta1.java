 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.sql.SQLException;
//import java.sql.Statement;

/**
 *
 * @author Shaula Balqis Z
 */
public class readFileFasta1 {
    public static String[] fileName={"h5n1-human-indonesia-m1-m2.fasta","h5n1-exceptHuman-indonesia-m1-m2.fasta"};
    public static String[] fileDefault={"h5n1-human-indonesia-m1-m2-default.fasta","h5n1-exceptHuman-indonesia-m1-m2-default.fasta"};
    public static BufferedReader baca;
    public static FileInputStream read;
    public static int trainingTrue,trainingFalse,testTrue,testFalse;
    public static int jumlahData, jumlahDataTraining,jumlahDataTest, length, jumlahTrue,jumlahFalse;
    public static String[][] dataRaw,dataRawTraining,dataRawTest, dataRaw1;
    public static String tempID;
    public static String tempSeg;
    public static boolean stopRead=false;
    public static String bridge;
    //public static double[][] defTarget={{1,0},{0,1}};
    public static double[][] defTarget={{1,-1},{-1,1}};
    //public static double def [][]={{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1},{0,0,0,0}};
    public static double def [][]={{1,-1,-1,-1},{-1,1,-1,-1},{-1,-1,1,-1},{-1,-1,-1,1},{-1,-1,-1,-1}};
    public static double[][][] data,dataFull,dataTraining,dataTest;
    public static int maxLength, minLength, useLength;
    public static double[][][] target,targetTraining,targetTest;
    //public static int jumlahData1, jumlahDataTraining1, jumlahTrue1,jumlahFalse1;
    
    public static void countData(){
        try{//2 karena ada 2 kelas, true or false
        boolean stop;int temp, panjang;
        for (int k=0; k<2; k++ ) {  
            stop=false;
            temp=0; //String disp; 
            char[] transf;
            FileInputStream filename=new FileInputStream (fileName[k]);
            BufferedReader count=new BufferedReader(new InputStreamReader(filename));
            while (stop==false){
                //System.out.println(".\n");
                String disp=count.readLine();
                if (disp.equals("STOP"))
                    stop=true;
                else{
                    transf=disp.toCharArray();
                    if ((transf.length>0) && (transf[0]=='>')){
                        //System.out.println("a\n");
                        temp++;String n="";
                        disp=count.readLine();
                        while (disp.toCharArray().length>0){
                            //System.out.print(disp.toCharArray().length+".");
                            n=n+disp;
                            disp=count.readLine();
                        }
                        transf=n.toCharArray();
                        panjang=transf.length;
                        if (panjang>=maxLength)
                            maxLength=panjang;
                        if (minLength==0||(panjang<=minLength))
                            minLength=panjang;
                        }
                }
            }
            if (k==0)
                jumlahTrue=temp;
            else
                jumlahFalse=temp;
            }
        jumlahData=jumlahTrue+jumlahFalse;
        System.out.println("Data host manusia:"+jumlahTrue+" Data host bukan manusia:"+jumlahFalse+" Jumlah data total:"+jumlahData+"\n");
        }
        catch(IOException e){
	    System.out.println(e+" countData");
	} 
    }
    
    public static void cekStop(){
        try{
            bridge=baca.readLine();
            //System.out.println("a: "+bridge);
            stopRead = bridge.equals("STOP");
        }
        catch(IOException e){
	    System.out.println(e+" di cekStop");
	}
    }
    
    public static void addID(){
        try{
            String disp;boolean stop=false;char[] notCut;tempID="";
            disp=bridge;
            notCut=disp.toCharArray();
            if (notCut.length==0)
                stopRead=true;
            else{
                int i=notCut.length-1;
                int j=0;
                while (stop==false){//identifikasi id 
                    if (notCut[j]=='>')//&&(notCut[j+1]=='g'))
                        stop=true;
                    else    
                        j++;
                }
                j=j+1;
                stop=false;
                while (stop==false){
                    if ((notCut[i]=='t')&&(notCut[i-1]=='S')&&(notCut[i-2]=='|'))
                        stop=true;
                    else
                        i--;
                }
                i=i-3;
                for (int k=j; k<=i; k++)
                    tempID=tempID+notCut[k];
            }
            //System.out.println("b: "+tempID);
        }
        catch(Exception e){
	    System.out.println(e+" di addID");
	}
    }
    
    public static void addSegment(){ //identifikasi sequence protein
        boolean stop=false;char [] cek;tempSeg="";
        try{
            String disp=baca.readLine();
            while (stop==false){
                cek=disp.toCharArray();
                if (cek.length==0)
                    stop=true;
                else{
                    tempSeg=tempSeg+disp;
                    disp=baca.readLine();                
                }
            }
            char [] temp=tempSeg.toCharArray();
            length=temp.length;
            /*if (length>=maxLength)
                maxLength=length;
            if (minLength==0||(length<=minLength))
                minLength=length;*/
            //minLength=500;
            //System.out.println(minLength+" "+maxLength);
        }
        catch(IOException e){
	    System.out.println(e+" di addSeg");
	}
        
    }
    public static void addSegmentValue(){
        int panjangFull=maxLength+1; //+1 untuk bias
        int panjang=useLength+1; //+1 untuk bias
        dataFull=new double[jumlahData][panjangFull][4];//+1 untuk bias
        data=new double[jumlahData][panjang][4];
        for (int i=0; i<jumlahData; i++){
            //////Transformasi bias//////
            for (int m=0; m<4; m++){//tambah bias
                dataFull[i][0][m]=1;
                data[i][0][m]=1;
            }
            //////Transformasi data penuh/////
            char [] temp=dataRaw[2][i].toCharArray();
            if (temp.length<maxLength){
                    int p,k=0;
                    while (k<temp.length){
                        if ((temp[k]=='A')||(temp[k]=='a'))
                            p=0;
                        else if ((temp[k]=='T')||(temp[k]=='t'))
                            p=1;
                        else if ((temp[k]=='G')||(temp[k]=='g'))
                            p=2;
                        else if ((temp[k]=='C')||(temp[k]=='c')||(temp[k]=='S')||(temp[k]=='s'))
                            p=3;
                        else
                            p=4;
                        System.arraycopy(def[p], 0, dataFull[i][k+1], 0, 4);
                        k++;
                    }
                    for (int j=k+1; j<panjangFull;j++){
                        System.arraycopy(def[4], 0, dataFull[i][j], 0, 4);//menambahkan nilai data kosong
                    }
                }
            else{
                for (int j=0; j<panjangFull-1; j++){
                    int p;
                    if ((temp[j]=='A')||(temp[j]=='a'))
                            p=0;
                        else if ((temp[j]=='T')||(temp[j]=='t'))
                            p=1;
                        else if ((temp[j]=='G')||(temp[j]=='g'))
                            p=2;
                        else if ((temp[j]=='C')||(temp[j]=='c')||(temp[j]=='S')||(temp[j]=='s'))
                            p=3;
                        else
                            p=4;
                    System.arraycopy(def[p], 0, dataFull[i][j+1], 0, 4);
                }
            }
            //////Transformasi data terpotong//////
            for (int j=0; j<panjang; j++){
                System.arraycopy(dataFull[i][j], 0, data[i][j],0, 4);
            }
        }
    }

    /**
     *
     */
    public static void baca(){
    try{
                minLength=0;maxLength=0;useLength=0;
                String kelas;
                countData();
                dataRaw=new String[5][jumlahData];
                int countData=0;
                for (int k=0; k<2; k++){
                    read=new FileInputStream (fileName[k]);
                    baca=new BufferedReader(new InputStreamReader(read));
                    cekStop();
                    while (stopRead==false){
                        addID();  
                        dataRaw[1][countData]=bridge;//1 untuk ID lengkap
                        dataRaw[0][countData]=tempID;//0 untuk ID organisme
                        addSegment();                                    
                        dataRaw[2][countData]=tempSeg;//2 untuk baris sequence
                        dataRaw[3][countData]=String.valueOf(length);//3 untuk panjang deret
                        if (k==0){
                            kelas="human";
                        }
                        else{
                            kelas="non human";
                        }
                        dataRaw[4][countData]=kelas;//4 untuk target data
                        cekStop();                    
                        countData++;
                    }
                }
                System.out.println("Panjang data minimum:"+minLength+" Panjang data maksimum:"+maxLength+"\n");
                useLength=minLength;
                fungsi1.init1.output=2;
                addSegmentValue(); 
                target=new double [jumlahData][2][4];
                for (int i=0; i<jumlahData; i++){
                        if(dataRaw[4][i].equals("human")){
                            for (int m=0; m<4; m++){
                                target[i][0][m]=defTarget[0][0];
                                target[i][1][m]=defTarget[0][1];
                            }
                        }
                        else{
                            for (int m=0; m<4; m++){
                                target[i][0][m]=defTarget[1][0];
                                target[i][1][m]=defTarget[1][1];
                            }
                        }
                        //temp++;
                }
                fungsi1.separatingData1.start();
                System.out.println("Data latih host manusia:"+trainingTrue+" Data latih host bukan manusia:"+trainingFalse+" Jumlah data latih total:"+jumlahDataTraining);
                System.out.println("Data uji host manusia:"+testTrue+" Data uji host bukan manusia:"+testFalse+" Jumlah data uji total:"+jumlahDataTest+"\n\n");
        }
	catch(FileNotFoundException e){
	    System.out.println(e+" di baca");
	}
    
    }
    /*public static void baca1() {
    try{
                minLength=0;maxLength=0;useLength=0;
                String kelas; int masuk=0;
                //String sql;
                //fungsi1.DBFunction1.connectDB();
                //Statement stmt=fungsi1.DBFunction1.conn.createStatement();
                //sql="delete from dataraw";//bersihin database
                //stmt.executeUpdate(sql);
                countData();//hitung data terlebih dahulu
                System.out.println(jumlahTrue1+" "+jumlahFalse1+" jumlah data : "+jumlahDataTraining1);
                int countData=0;
                dataRaw1=new String[5][jumlahDataTraining1];
                for (int k=0; k<2; k++){
                    //countData=0;
                    read=new FileInputStream (fileTrainingName[k]);
                    baca=new BufferedReader(new InputStreamReader(read));
                    cekStop();
                    while (stopRead==false){
                        dataRaw1[1][countData]=bridge;//1 untuk ID lengkap
                        addID();  
                        dataRaw1[0][countData]=tempID;//0 untuk ID organisme
                        addSegment();//System.out.println(countData);                                      
                        dataRaw1[2][countData]=tempSeg;//2 untuk baris sequence
                        dataRaw1[3][countData]=String.valueOf(length);//3 untuk panjang sequence
                        //System.out.println(countData+1);
                        //for (int i=0; i<3; i++)
                        //       System.out.println(data[i][countData]);
                        //System.out.println();
                        if (k==0){
                            kelas="human";
                        }
                        else{
                            kelas="non human";
                        }
                        dataRaw1[4][countData]=kelas;//4 untuk target data
                        //sql="insert into dataraw values ('"+bridge+"','"+tempID+"','"+tempSeg+"','"+kelas+"','"+length+"')";
                        //stmt.executeUpdate(sql);
                        cekStop();                    
                        countData++;
                    }
                }
                useLength=fungsi1.dataFilter1.lengthCriteria(minLength, maxLength);
                for (int i=0; i<jumlahDataTraining1; i++){
                    if (Integer.parseInt(dataRaw1[3][i])>=useLength){
                        masuk++;
                    }
                }
                //sql="DELETE FROM skripsi1.dataraw WHERE dataraw.PanjangDeret < "+useLength+"";
                //stmt.executeUpdate(sql);
                jumlahDataTraining=masuk;
                jumlahTrue=0; jumlahFalse=0;
                int temp=0;
                targetTraining=new double [jumlahDataTraining][2][4];
                dataRaw=new String[5][jumlahDataTraining];
                for (int i=0; i<jumlahDataTraining1; i++){
                    if (Integer.parseInt(dataRaw1[3][i])>=useLength){
                        for (int j=0; j<5; j++){
                            dataRaw[j][temp]=dataRaw1[j][i];
                        }
                        if(dataRaw1[4][i].equals("human")){
                            jumlahTrue++;
                            for (int m=0; m<4; m++){
                                targetTraining[temp][0][m]=1;
                                targetTraining[temp][1][m]=-1;
                            }
                        }
                        else{
                            jumlahFalse++;
                            for (int m=0; m<4; m++){
                                targetTraining[temp][0][m]=-1;
                                targetTraining[temp][1][m]=1;
                            }
                        }
                        temp++;
                    }
                }
                addSegmentValue();
                System.out.println("Data host manusia:"+jumlahTrue+" Data host bukan manusia:"+jumlahFalse+" Jumlah data total:"+jumlahDataTraining+"\nPanjang data minimum:"+minLength+" Panjang data maksimum:"+maxLength);
                //System.out.println(maxLength);
                //for (int i=0; i<maxLength+1; i++){
                //    for (int j=0; j<4; j++){
                //        System.out.print(data[92][i][j]+"\t");
                //    }
                //    System.out.println();
                //}
        }
	catch(FileNotFoundException e){
	    System.out.println(e+" di baca1");
	}
    
    } */  
    
    
}
