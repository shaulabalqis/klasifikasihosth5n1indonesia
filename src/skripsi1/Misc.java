/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package skripsi1;

import java.io.*;
import java.text.DecimalFormat;

/**
 *
 * @author Shaula Balqis Z
 */
//import java.util.Random;
//import java.util.stream.*;
public class Misc {
    public static void main (String[] args) throws IOException{
        /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Masukkan FASTA :  ");
        String add=in.readLine();
        System.out.println(add);
        boolean human=false;
            char [] charAdd=add.toCharArray();
            int k,i=charAdd.length-1;
            if ((charAdd[i]=='n'||charAdd[i]=='N')&&(charAdd[i-1]=='a'||charAdd[i-1]=='A')&&(charAdd[i-2]=='m'||charAdd[i-2]=='M')&&(charAdd[i-3]=='u'||charAdd[i-3]=='U')&&(charAdd[i-4]=='h'||charAdd[i-4]=='H'))
               human=true;
        System.out.println("human = "+human);*/
        
        //fungsi1.separatingData1.randomizeOrder(34);
        DecimalFormat df=new DecimalFormat("0.00000000");
        double truePos=14,trueNeg=18,falsePos=1,falseNeg=1;
        double recall=truePos/(falseNeg+truePos);
        System.out.println(df.format(recall));
        
        /*Random r=new Random();
        double[] hasil=r.doubles(21, -0.5, 0.5).toArray();
        for(int i=0; i<hasil.length;i++){
            System.out.print(hasil[i]+"\t");
        }
        System.out.println();*/
    }
    
}
