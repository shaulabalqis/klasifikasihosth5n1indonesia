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
import static fungsi1.init1.*;
import java.util.Random;
public class glorotInitialization1 {
    public static double randomize(double rangeMin,double rangeMax){
        Random r = new Random();
        double result = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return result;
    }
    public static double uniformSigmoid(int in, int out){
        double r=Math.sqrt(6.0000000000/(in+out));
        double ans=(1.000/12)*((r-(-r))*(r-(-r)));
        return ans;
    }
    public static double initStart1(int n){
        double ans=0;
        double a=-1*(1.0000000/(Math.sqrt(n)));
        double b=1.000000/(Math.sqrt(n));
        ans=randomize(a,b);
        return ans;
    }
    public static double initStart(int a, int b){
        double ans;
        ans=2/(a+b);
        return ans;
        
    }
    //public static void main (String[]args){
        
      //  System.out.println(initStart(982,20));
    //}
    
    public static void start(){
        //////init U/////////
        for (int h=1; h<hidden1+1;h++){
            for(int m=0; m<4; m++)
                u[0][h][m]=0;
            for (int i=1; i<input+1; i++){
                for(int m=0; m<4; m++)
                    u[i][h][m]=uniformSigmoid(input,hidden1);
            }
        }
        //////init V///////
        for (int j=1; j<hidden2+1; j++){
            for(int m=0; m<4; m++)
                v[0][j][m]=0;
            for (int h=1; h<hidden1+1;h++){
                for(int m=0; m<4; m++)
                    v[h][j][m]=uniformSigmoid(hidden1,hidden2);
            }
        }
        /////init W///////
        for (int k=0; k<output; k++){
            for(int m=0; m<4; m++)
                w[0][k][m]=0;
            for (int j=1; j<hidden2+1; j++){
                for(int m=0; m<4; m++)
                    w[j][k][m]=uniformSigmoid(hidden2,output);
            }
        }
    }
}
