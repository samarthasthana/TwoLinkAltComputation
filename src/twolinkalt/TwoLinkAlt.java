/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twolinkalt;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Sam
 */
public class TwoLinkAlt {

    /**
     * @param args the command line arguments
     */
    
    public double genExpo(){
        double val=0.0;
        Random rnd =new Random();
        return rnd.nextDouble();

    }
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);        
        Integer mVal,choice,incoming;
        double initVal=0.0,stepVal=0.0;
        System.out.println("Enter the value of m");
        mVal=scan.nextInt();// can be used to set the value of number of overflow paths 
        //mVal=10;
        double[][] BsArr=new double[mVal][2]; // initialize the Bs array for the required dimensions
        double [] Pm= new double [mVal+2];
        double [] pass=new double [mVal];
        System.out.println("Enter 1 to define constant Bs value, 2 to define a gradually dec Bs value");
        choice=scan.nextInt();
        if(choice==1){
            System.out.println("Enter the constant Bs value for all links");
            initVal=scan.nextDouble();
            for(int i=0;i<mVal;i++){
                for(int j=0;j<2;j++){
                BsArr[i][j]=initVal;
                }            
            }
            BsArr[0][1]=0;// as there is no second link in the primary path
        }else{
            if(choice==2){
                System.out.println("Enter the initial value of Bs");
                initVal=scan.nextDouble();
                System.out.println("Enter the decreasing step value");
                stepVal=scan.nextDouble();
                if(stepVal*(double)((2*mVal)-1)> initVal){ System.out.println(" Bs value cannot be neg, illegal step value entered");
                return ;
                }
                BsArr[0][0]=initVal;
                BsArr[0][1]=0;
                int k=1;
                for(int i=1;i<mVal;i++){
                    for(int j=0;j<2;j++){
                    BsArr[i][j]=initVal-(stepVal*(k));
                    k++;
                    }            
                }
            }
        }
     for(int i=0;i<mVal;i++){
         for(int j=0;j<2;j++){
         System.out.printf("B %d %d = %f \n",i,j,BsArr[i][j]);
         }
     }   
     Pm[1]=initVal; // as P1 = B i-j
     for(int i=2;i<mVal+1;i++){
         Pm[i]=Pm[i-1]*(1-((1-BsArr[i-1][0])*(1-BsArr[i-1][1])));
     }
     for(int j=1;j<mVal+1;j++){
         System.out.printf("P%d = %f \n",j,Pm[j]);
     }
     System.out.printf("L i-j =%f", Pm[mVal]);
    }// void main 
}
