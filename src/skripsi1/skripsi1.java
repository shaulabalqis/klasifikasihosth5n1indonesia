/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package skripsi1;

import java.util.Scanner;
import java.io.InputStreamReader;
import java.sql.SQLException;
/**
 *
 * @author Shaula Balqis Z
 */
public class skripsi1 {
    public static Scanner in=new Scanner(new InputStreamReader(System.in));
    public static UI1.start1 prim;
        public static void main(String args[]) throws ClassNotFoundException{
        fungsi1.readFileFasta1.baca();
        fungsi1.sigmoidNormalization1.startTraining();
        fungsi1.sigmoidNormalization1.startTest();
        prim=new UI1.start1();
        //fungsi1.init1.inisialisasi();
        //fungsi1.sigmoidNormalization1.start();
        //fungsi1.initBobotNguyenWidrow1.main(args);
        prim.viewJumlahData.setText(String.valueOf(fungsi1.readFileFasta1.jumlahDataTraining));
        prim.viewJumlahDataUji.setText(String.valueOf(fungsi1.readFileFasta1.jumlahDataTest));
//        fungsi.init.inisialisasi();
        prim.setVisible(true);
        //fungsi.readFileFasta.baca();
    
}
    
}
