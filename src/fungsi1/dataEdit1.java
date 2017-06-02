/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

import static fungsi1.readFileFasta1.fileName;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Shaula Balqis Z
 */
public class dataEdit1 {//kenapa dihapus? Karena disini belum dibedakan kelasnya. EDIT : sudah dibedakan kelasnya
    public static void dataDefault(){
        try{
          for (int k=0; k<2; k++){
            PrintWriter pw = new PrintWriter(fungsi1.readFileFasta1.fileName[k]);
            pw.close();
            FileReader fr=new FileReader(fungsi1.readFileFasta1.fileDefault[k]);
            FileWriter fw=new FileWriter(fungsi1.readFileFasta1.fileName[k]);
            int c = fr.read();
            while(c!=-1) {
                fw.write(c);
                c = fr.read();
            }
            fr.close();
            fw.close();
          }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static void dataAdd(String add){//backup isi file ke string, hapus file, isikan string+seq tambahan (karena harus disisipkan di tengah file)
        try{
            //menentukan kelas dari data yang dimasukkan//
            boolean human=false;
            char [] charAdd=add.toCharArray();
            int k=0,i=0;
            while(i<charAdd.length){
                if ((charAdd[i]=='H'||charAdd[i]=='h')&&(charAdd[i+1]=='u'||charAdd[i+1]=='U')&&(charAdd[i+2]=='m'||charAdd[i+2]=='M')&&(charAdd[i+3]=='a'||charAdd[i+3]=='A')&&(charAdd[i+4]=='n'||charAdd[i+4]=='N')){
                    human=true;
                    k=0;
                    break;
                }
                else{
                    k=1;
                    i++;
                }
            }
            System.out.println("human : "+human);
            //memasukkan data lama+data baru+stopping sentence ke file//
            boolean stop=false;String disp="", add1="", add2="";//add1=isi file, add2=stopping sentence (kalimat paling bawah di file yg menandakan untuk stop baca
            FileInputStream filename=new FileInputStream (fileName[k]);
            BufferedReader count=new BufferedReader(new InputStreamReader(filename));
            while (stop==false){
                disp=count.readLine();
                if (disp.equals("STOP"))
                    stop=true;
                else
                   add1=add1+disp+"\n"; 
            }
            add2=add2+disp+"\n";
            disp=count.readLine();
            add2=add2+disp+"\n";
            PrintWriter pw = new PrintWriter(fungsi1.readFileFasta1.fileName[k]);
            pw.close();
            FileWriter fw=new FileWriter(fungsi1.readFileFasta1.fileName[k]);
            fw.write(add1);
            fw.write(add+"\n\n");
            fw.write(add2);            
            fw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
        
    }
}
