/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;

import java.util.Scanner;
import java.util.*;
import java.io.*;
/**
 *
 * @author Boaz
 */
public class TriviaQuiz {
     
    public static boolean welcome(){
        
        int password = 1234;
        boolean isAdmin = false;
    
        Scanner s = new Scanner(System.in);
        int input;
        
        System.out.println("\nWelcome to TriviaQuiz !");
        input = -1;
        while (input < 0 || input > 3) {            
            
            System.out.println("Are you a player or an administrator? Enter one of the options:\n1 - Player\n2 - Administrator\n0 - Exit TriviaQuiz");
            input=s.nextInt();
        
            switch(input){
                case 0 : 
                    System.out.println("Bye!");
                    System.exit(input);
                    break;
                case 1 : isAdmin = false;
                    break;
                case 2 :
                    System.out.println("\nPlease enter 4 digit administrator password:");
                    int pass = s.nextInt();
                    if (pass==password) {
                        isAdmin = true;
                    }
                    break;
                default :
                    System.out.println("\n[!] Error: bad input!");
                    break;
            }
        }
        
        return isAdmin;
    }
    

    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        // TODO code application logic here
        
        HashMap<String, ArrayList<Question>> questions  = new HashMap<String, ArrayList<Question>>();
        User user;
                
        String path = ".\\Trivia.qa";
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }
        else if(f.length()!=0){
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fin);
            questions = (HashMap<String, ArrayList<Question>>)oin.readObject();
        }
        
        // Main loop to make sure we exit game only if user enters 0
        while (true) {            
                
            boolean isAdmin = welcome();
            
            if (isAdmin){
                user = new AdminConsole(questions, path);
            }else{
                user  = new PlayerConsole(questions);
            }
            
        }
        

    }
}
