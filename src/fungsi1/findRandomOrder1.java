/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import static fungsi1.readFileFasta1.*;
/**
 *
 * @author Shaula Balqis Z
 */

public class findRandomOrder1 {
    public static  int [] pedomanTotal;
    public static int [] pedomanTraining;
    
    public static int[] randomizeOrder(int size){
      int index, temp;
      int [] array=new int [size];
      for (int j=0; j<array.length; j++)
        array[j]=j;
      Random r = new Random();
      for (int i=array.length-1; i>0; i--){
        index = r.nextInt(i+1);
        temp = array[index];
        array[index] = array[i];
        array[i] = temp;
      }
      //for (int j=0; j<array.length; j++)
      //  System.out.print(array[j]+"\t");
      //System.out.println();
      return array;
    }
    
    public static void start(){
        try{
        String filename=Integer.toString(jumlahData)+","+Integer.toString(jumlahDataTraining)+".txt";
        File f=new File(filename);
        if (f.exists()){
            FileInputStream read =new FileInputStream(filename);
            BufferedReader baca=new BufferedReader(new InputStreamReader(read));
            char[] dataTotal=baca.readLine().toCharArray();
            //for (int k=0; k<dataTotal.length; k++)
            //    System.out.print(dataTotal[k]);
            //System.out.println();
            char[] dataTraining=baca.readLine().toCharArray();
            //String [] charDataTotal= new String[jumlahData];
            //String [] charDataTraining= new String[jumlahDataTraining];
            pedomanTotal= new int[jumlahData];
            pedomanTraining= new int[jumlahDataTraining];
            String temp="";int i=0;int j=0;
            while (i<jumlahData){
                if (dataTotal[j]=='.'){
                    //charDataTotal[i]=temp;
                    pedomanTotal[i]=Integer.parseInt(temp);
                    //System.out.print(pedomanTotal[i]+".");
                    temp="";
                    i++;
                }
                else
                    temp=temp+dataTotal[j];
                j++;
            }
            temp="";i=0;j=0;
            while (i<jumlahDataTraining){
                if (dataTraining[j]=='.'){
                    //charDataTraining[i]=temp;
                    pedomanTraining[i]=Integer.parseInt(temp);
                    temp="";
                    i++;
                }
                else
                    temp=temp+dataTraining[j];
                j++;
            }
        }
        else{
            f.createNewFile();
            pedomanTotal=randomizeOrder(jumlahData);
            pedomanTraining=randomizeOrder(jumlahDataTraining);
            PrintWriter pw = new PrintWriter(filename);
            pw.close();
            FileWriter fw=new FileWriter(filename);
            for (int i=0; i<jumlahData; i++){
                fw.write(Integer.toString(pedomanTotal[i])+".");
            }
            fw.write("\n");
            for (int i=0; i<jumlahDataTraining; i++){
                fw.write(Integer.toString(pedomanTraining[i])+".");
            }
            fw.close();
        }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    /*public static void main (String [] args){
        start();
    }*/
}
