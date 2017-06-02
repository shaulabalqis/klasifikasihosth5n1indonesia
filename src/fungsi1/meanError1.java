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
public class meanError1 {
    public static void start(){
        for (int m=0; m<4; m++)
            fungsi1.init1.eTotal[m]=0;
        for (int i=0; i<fungsi1.readFileFasta1.jumlahDataTraining; i++){
            for (int m=0; m<4; m++)
                fungsi1.init1.eTotal[m]=fungsi1.init1.eTotal[m]+fungsi1.init1.e[i][m];
        }
        for (int m=0; m<4; m++)
            fungsi1.init1.eTotal[m]=(1.0/fungsi1.readFileFasta1.jumlahDataTraining)*fungsi1.init1.eTotal[m];
        fungsi1.init1.eTotalRata2=0;
        for (int m=0; m<4; m++)
            fungsi1.init1.eTotalRata2=fungsi1.init1.eTotalRata2+fungsi1.init1.eTotal[m];
        fungsi1.init1.eTotalRata2=(fungsi1.init1.eTotalRata2/4.0000000000000);
                
    }
    public static boolean cek(){
        boolean ulang=false;
        if (fungsi1.init1.eTotalRata2>fungsi1.init1.threshold)
            ulang=true;
        return ulang;
    }
    
}
