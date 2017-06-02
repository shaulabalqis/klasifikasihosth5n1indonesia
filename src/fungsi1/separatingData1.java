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
import static fungsi1.readFileFasta1.*;
import java.util.Random;

public class separatingData1 {
    public static double trainingPercentage,testPercentage;
    
    
    
    /*public static void main (String[] args){
        int []array=randomizeOrder(166);
        int []array2=randomizeOrder(132);
        for (int i=0; i<array.length; i++)
            System.out.print(array[i]+".");
        System.out.println();
        for (int i=0; i<array2.length; i++)
            System.out.print(array2[i]+".");
    }*/
    
    public static void randomizingAll(){
        int[] order=fungsi1.findRandomOrder1.pedomanTotal;
        double[][][]dataTemp=new double[jumlahData][useLength+1][4];
        double[][][]targetTemp=new double[jumlahData][2][4];
        String[][] dataRawTemp= new String [5][jumlahData];
        for (int i=0; i<jumlahData; i++){
            for (int j=0; j<useLength+1; j++){
                System.arraycopy(data[order[i]][j], 0, dataTemp[i][j], 0, 4);
            }
            for (int k=0; k<2; k++)
                System.arraycopy(target[order[i]][k], 0, targetTemp[i][k], 0, 4);
            for (int b=0; b<5; b++)
                dataRawTemp[b][i]=dataRaw[b][order[i]];
        }
        for (int i=0; i<jumlahData; i++){
            for (int j=0; j<useLength+1; j++){
                System.arraycopy(dataTemp[i][j],0, data[i][j],0,  4);
            }
            for (int k=0; k<2; k++)
                System.arraycopy(targetTemp[i][k],0, target[i][k],0,  4);
            for (int b=0; b<5; b++)
               dataRaw[b][i]=dataRawTemp[b][i];
        }
    }

    public static void randomizingTraining(){
        int[] order=fungsi1.findRandomOrder1.pedomanTraining;
        double[][][]dataTemp=new double[jumlahDataTraining][useLength+1][4];
        double[][][]targetTemp=new double[jumlahDataTraining][2][4];
        String[][] dataRawTemp= new String [5][jumlahDataTraining];
        for (int i=0; i<jumlahDataTraining; i++){
            for (int j=0; j<useLength+1; j++){
                System.arraycopy(dataTraining[order[i]][j], 0, dataTemp[i][j], 0, 4);
            }
            for (int k=0; k<2; k++)
                System.arraycopy(targetTraining[order[i]][k], 0, targetTemp[i][k], 0, 4);
            for (int b=0; b<5; b++)
                dataRawTemp[b][i]=dataRawTraining[b][order[i]];
        }
        for (int i=0; i<jumlahDataTraining; i++){
            for (int j=0; j<useLength+1; j++){
                System.arraycopy(dataTemp[i][j],0, dataTraining[i][j],0,  4);
            }
            for (int k=0; k<2; k++)
                System.arraycopy(targetTemp[i][k],0, targetTraining[i][k],0,  4);
            for (int b=0; b<5; b++)
               dataRawTraining[b][i]=dataRawTemp[b][i];
        }
    }
    
    /*public static void startTrainingAllTestAll(){
        dataRawTest=new String[5][jumlahDataTraining];
        dataTest=new double[jumlahDataTraining][useLength+1][4];
        targetTest=new double[jumlahDataTraining][2][4];
        jumlahDataTest=jumlahDataTraining;
        for (int i=0; i<jumlahDataTraining; i++){
            for (int j=0; j<5; j++){
                dataRawTest[j][i]=dataRawTraining[j][i];
            }
            for (int j=0; j<2; j++){
                System.arraycopy(targetTraining[i][j], 0, targetTest[i][j], 0, 4);
            }
            for (int j=0; j<useLength+1; j++)
                System.arraycopy(dataTraining[i][j], 0, dataTest[i][j], 0, 4);
        }
    }*/
    public static void start(){
        trainingPercentage=0.8;testPercentage=1-trainingPercentage; 
        trainingTrue= (int)(trainingPercentage*fungsi1.readFileFasta1.jumlahTrue);
        testTrue=fungsi1.readFileFasta1.jumlahTrue-trainingTrue;
        trainingFalse=(int)(trainingPercentage*fungsi1.readFileFasta1.jumlahFalse);
        testFalse=fungsi1.readFileFasta1.jumlahFalse-trainingFalse;
        jumlahDataTraining=trainingTrue+trainingFalse;
        jumlahDataTest=testTrue+testFalse;
        dataRawTraining=new String[5][jumlahDataTraining];
        dataRawTest=new String[5][jumlahDataTest];
        dataTraining=new double[jumlahDataTraining][useLength+1][4];
        dataTest=new double[jumlahDataTest][useLength+1][4];
        targetTraining=new double[jumlahDataTraining][2][4];
        targetTest=new double[jumlahDataTest][2][4];
        fungsi1.findRandomOrder1.start();
        randomizingAll();
        int a=0,b=0,aTrue=0,aFalse=0;//a untuk training, b untuk test
        for (int i=0; i<jumlahData; i++){
            if (dataRaw[4][i].equals("human")&&aTrue<trainingTrue){
                for (int j=0; j<useLength+1; j++){
                    System.arraycopy(data[i][j], 0, dataTraining[a][j], 0, 4);
                }
                for(int j=0;j<2;j++){
                    System.arraycopy(target[i][j], 0, targetTraining[a][j], 0, 4);
                }
                for (int j=0; j<5; j++)
                    dataRawTraining[j][a]=dataRaw[j][i];
                //System.out.print(i+".");
                a++;aTrue++;
            }
            else if (dataRaw[4][i].equals("human")&&aTrue>=trainingTrue){
                for (int j=0; j<useLength+1; j++){
                    System.arraycopy(data[i][j], 0, dataTest[b][j], 0, 4);
                }
                for(int j=0;j<2;j++){
                    System.arraycopy(target[i][j], 0, targetTest[b][j], 0, 4);
                }
                for (int j=0; j<5; j++)
                    dataRawTest[j][b]=dataRaw[j][i];
                b++;//bTrue++;
            }
            else if (dataRaw[4][i].equals("non human")&&aFalse<trainingFalse){
                for (int j=0; j<useLength+1; j++){
                    System.arraycopy(data[i][j], 0, dataTraining[a][j], 0, 4);
                }
                for(int j=0;j<2;j++){
                    System.arraycopy(target[i][j], 0, targetTraining[a][j], 0, 4);
                }
                for (int j=0; j<5; j++)
                    dataRawTraining[j][a]=dataRaw[j][i];
                //System.out.print(i+".");
                a++;aFalse++;
            }
            else if (dataRaw[4][i].equals("non human")&&aFalse>=trainingFalse){
                for (int j=0; j<useLength+1; j++){
                    System.arraycopy(data[i][j], 0, dataTest[b][j], 0, 4);
                }
                for(int j=0;j<2;j++){
                    System.arraycopy(target[i][j], 0, targetTest[b][j], 0, 4);
                }
                for (int j=0; j<5; j++)
                    dataRawTest[j][b]=dataRaw[j][i];
                b++;//bTrue++;
            }
        }
        randomizingTraining();
        //startTrainingAllTestAll();
    }

    /*public static void start1 (){
        trainingPercentage=0.8;testPercentage=1-trainingPercentage; 
        trainingTrue= (int)(trainingPercentage*fungsi1.readFileFasta1.jumlahTrue);
        testTrue=fungsi1.readFileFasta1.jumlahTrue-trainingTrue;
        trainingFalse=(int)(trainingPercentage*fungsi1.readFileFasta1.jumlahFalse);
        testFalse=fungsi1.readFileFasta1.jumlahFalse-trainingFalse;
        jumlahDataTraining=trainingTrue+trainingFalse;jumlahDataTest=testTrue+testFalse;
        //System.out.println(jumlahDataTraining+" "+jumlahDataTest+" "+trainingTrue+" "+trainingFalse);
        dataRawTraining=new String[5][jumlahDataTraining];
        dataRawTest=new String[5][jumlahDataTest];
        dataTraining=new double[jumlahDataTraining][useLength+1][4];
        dataTest=new double[jumlahDataTest][useLength+1][4];
        targetTraining=new double[jumlahDataTraining][2][4];
        targetTest=new double[jumlahDataTest][2][4];
        //int j;
            int a,b,i=0;
                for (b=0; b<testTrue;b++){//buat assign testTrue
                    for (int j=0; j<=useLength;j++){
                        System.arraycopy(data[i][j], 0, dataTest[b][j], 0, 4);
                    }
                    for (int j=0; j<2;j++){
                        System.arraycopy(target[i][j], 0, targetTest[b][j], 0, 4);
                    }
                    for (int j=0; j<5;j++){
                        dataRawTest[j][b]=dataRaw[j][i];
                    }
                    i++;
                }
                for (a=0; a<trainingTrue;a++){//buat assign trainingTrue
                    for (int j=0; j<=useLength;j++){
                        System.arraycopy(data[i][j], 0, dataTraining[a][j], 0, 4);
                    }
                    for (int j=0; j<2;j++){
                        System.arraycopy(target[i][j], 0, targetTraining[a][j], 0, 4);
                    }
                    for (int j=0; j<5;j++){
                        dataRawTraining[j][a]=dataRaw[j][i];
                    }
                    i++;
                }
                for (b=testTrue; b<jumlahDataTest;b++){//buat assign testFalse
                    for (int j=0; j<=useLength;j++){
                        System.arraycopy(data[i][j], 0, dataTest[b][j], 0, 4);
                    }
                    for (int j=0; j<2;j++){
                        System.arraycopy(target[i][j], 0, targetTest[b][j], 0, 4);
                    }
                    for (int j=0; j<5;j++){
                        dataRawTest[j][b]=dataRaw[j][i];
                    }
                    i++;
                }
                for (a=trainingTrue; a<jumlahDataTraining;a++){//buat assign trainingFalse
                    for (int j=0; j<=useLength;j++){
                        System.arraycopy(data[i][j], 0, dataTraining[a][j], 0, 4);
                    }
                    for (int j=0; j<2;j++){
                        System.arraycopy(target[i][j], 0, targetTraining[a][j], 0, 4);
                    }
                    for (int j=0; j<5;j++){
                        dataRawTraining[j][a]=dataRaw[j][i];
                    }
                    i++;
                }
                randomizingTraining();
    }*/
}
