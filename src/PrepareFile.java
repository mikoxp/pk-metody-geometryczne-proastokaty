/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class PrepareFile {
   public static void changeChar(char a,char b,File fileIn,File fileOut) throws FileNotFoundException, IOException{
       FileReader fr = new FileReader(fileIn); 
       FileWriter fw=new FileWriter(fileOut);
       char[] p =new char[1];
       BufferedReader br = new BufferedReader(new FileReader(fileIn));
       while(fr.read(p)!=-1){
           if(p[0]!=a) {
               fw.write(p);
           } else {
               fw.write(b);
           }
           //System.out.print(p);
       }
       
        fr.close();
        fw.close();
    }
    
}
