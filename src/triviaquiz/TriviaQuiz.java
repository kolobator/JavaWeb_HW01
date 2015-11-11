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

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        // TODO code application logic here
        
        ArrayList<Question> questions = new ArrayList<Question>();
        
        Scanner s=new Scanner(System.in);
        int input;
        User user = new User();
        
        String path = ".\\Trivia.qa";
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }
        else if(f.length()!=0){
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fin);
            questions = (ArrayList<Question>)oin.readObject();
        }
        
        
        System.out.print("Welcome\n");
        System.out.print("Are you a player or an administrator?\nEnter 1 for player or 2 for administrator\n");
        input=s.nextInt();
        
        if(input==1){
            // start game console
        }
        else if(input==2){
            System.out.println("\nPlease enter 4 digit administrator password:");
            // check password length
            int password=s.nextInt();
            user = new User(password);
        }
        else{
            System.out.println("\nError: bad input! Exiting the game.");
            System.exit(-1);
        }
        
        
        if (user.type.equals("admin")) {
            // start admin console
            AdminConsole ac = new AdminConsole();
            
            System.out.println("\nWelcome to Administrator Console!\n\nEnter one of the options:");
            System.out.println("1 - Add a question\n2 - Delete a question\n3 - Show questions\n");
            
            input = s.nextInt();
            
            switch(input){
                case 1 :                     
                    while (input==1) {                        
                        ac.addQuestion(questions);
                        System.out.println("Add another question? 1 - yes, 2 - no");
                        input = s.nextInt();
                    }
                    System.out.println("Save the questions list?  1 - yes, 2 - no");
                    input = s.nextInt();
                    if (input==1) {
                        FileOutputStream fout = new FileOutputStream(path);
                        ObjectOutputStream oout =  new ObjectOutputStream(fout);
                        oout.writeObject(questions);
                        oout.close();
                    }
                    break;
                case 2 : 
                    input=1;
                    while(input==1){
                        if(!questions.isEmpty()){
                       
                            ac.showQuestions(questions);
                            System.out.println("Enter question number you'd like to delete:");
                            input = s.nextInt();
                            ac.deleteQuestions(questions, input);
                            ac.showQuestions(questions);
                            System.out.println("Delete another question? 1 - yes, 2 - no");
                            input = s.nextInt();
                        }
                        else System.out.println("The list is empty");
                    }
                    System.out.println("Save to file? 1 - yes, 2 - no");
                    input = s.nextInt();
                    if (input==1) {
                        FileOutputStream fout = new FileOutputStream(path);
                        ObjectOutputStream oout =  new ObjectOutputStream(fout);
                        oout.writeObject(questions);
                        oout.close();
                    }
                    break;
                case 3 : 
                    if(!questions.isEmpty()){
                        ac.showQuestions(questions);
                    }
                    else System.out.println("The list is empty");
                    break;
            }
        }   
    } 
}
