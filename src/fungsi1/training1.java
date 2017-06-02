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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
public class training1 {
    public static int epoch;
    public static double time;
    public static timer1 s = new timer1();
    public static UI1.trainingProc1 proses=new UI1.trainingProc1();
    public static Thread thread;
    public static void main (String args[]){
        DecimalFormat df=new DecimalFormat("0.00000000");
        DecimalFormat df1=new DecimalFormat("0");
        proses=new UI1.trainingProc1();
        proses.detailUji.setVisible(false);
        proses.detailTraining.setText("Mohon tunggu ...");
        proses.alpha.setText(df.format(fungsi1.init1.alpha));
        proses.hiddenNeuron1.setText(df1.format(fungsi1.init1.hidden1));
        proses.hiddenNeuron2.setText(df1.format(fungsi1.init1.hidden2));
        proses.setVisible(true);
        proses.minError.setText(df.format(fungsi1.init1.threshold));
    }
    public static void start(){
            thread = new Thread(new Runnable(){     
            @Override
            public void run(){
                UI1.trainingProc1.klikStop=false;
                System.out.println("\n__________TRAINING__________\n");
                fungsi1.nguyenWidrowInitialization1.start();
                boolean ulang=true;int i;
                epoch=1;
                s.start();
                while (ulang==true){
                    for (i=0; i<fungsi1.readFileFasta1.jumlahDataTraining; i++){
                        try {
                            fungsi1.init1.inputKe=i;
                            fungsi1.feedForward1.start();
                            fungsi1.backward1.start();
                            fungsi1.updateBobot1.start();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(training1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    fungsi1.meanError1.start();
                    ulang=fungsi1.meanError1.cek();
                    proses.updating();
                    //fungsi1.init1.inputKe++;
                    if ((epoch>=fungsi1.init1.epochMax)||(ulang==false)){//||UI1.trainingProc1.klikStop==true){
                        s.stop();
                        ulang=false;
                        if (epoch>=fungsi1.init1.epochMax)
                            proses.detailTraining.setText
                            ("Pelatihan data selesai. Epoch = maksimal epoch");
                        else
                            proses.detailTraining.setText
                            ("Pelatihan data selesai. Rata-rata kesalahan <= ambang kesalahan");
                        //proses.detailTraining.setVisible(true);
                        proses.dataTest.setEnabled(true);
                        proses.testDataDefault.setEnabled(true);
                        proses.testInputSeq.setEnabled(true);
                        //System.out.println("done");
                        System.out.println("epoch :\t"+epoch);
                        System.out.print("error\t");
                        for (int m=0; m<4; m++)
                            System.out.print(fungsi1.init1.eTotal[m]+"\t");
                        System.out.println();
                    }
                    else
                        epoch++;
                }
                /*proses.epoch.setText(String.valueOf(epoch));
                String error="";
                for (int m=0; m<4; m++)
                    error=error+(String.valueOf(fungsi1.init1.eTotal[m]))+"\t";
                proses.error.setText(error);
                proses.setVisible(true);*/
                
            }
            
        });
        thread.start();
    }
}
