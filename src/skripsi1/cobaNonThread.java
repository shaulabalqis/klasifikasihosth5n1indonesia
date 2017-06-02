/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package skripsi1;

/**
 *
 * @author Shaula Balqis Z
 */
public class cobaNonThread {
    public static int m=1000;
    public static double [] input=new double [99999];
    public static double [][] first1=new double[m][m],first2=new double [m][m];
    public static double [][] second1=new double [m][m],second2=new double [m][m];
    public static double [][] multiply1=new double [m][m],multiply2=new double [m][m];
    public static double ans1, ans2;
    public static void start1(){
                System.out.println("run1");
                multiply1=startMultiply(first1, second1);
                start2();
                
    }
    
    public static void start2(){
                System.out.println("run2");
                multiply2=startMultiply(first2, second2);
            
    }
    public static void main (String[] args){
        System.out.println("Non thread");
        int c,d;
        for (c = 0; c < m; c++){
            for (d = 0; d < m; d++){
                first1[c][d] = Math.random();//randomize 1st matrix
                second1[c][d] = Math.random();//randomize 2nd matrix
                first2[c][d] = Math.random();//randomize 1st matrix
                second2[c][d] = Math.random();
            }
        }
        for (int k=0; k<input.length; k++)
            input[k]=Math.random();
        start1();
        
    }
    public static double[][] startMultiply(double[][] first, double[][] second){
        double[][] multiply=new double[m][m];
        int c,d;
        for (c = 0; c < m; c++) {
        int k;
        double sum = 0.0;
        for (d = 0; d < m; d++) {
            for (k = 0; k < m; k++) {
                sum = sum + first[c][k]*second[k][d];
                }
            multiply[c][d] = sum;
            sum = 0.0;
            }
        }
        return multiply;
    }
    
}
