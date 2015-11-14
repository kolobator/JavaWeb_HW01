/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 *
 * @author Boaz
 */
public class AdminConsole extends User{

    Scanner s = new Scanner(System.in);
    String input;    
    
    public AdminConsole(HashMap<String, ArrayList<Question>> questions, String path) throws FileNotFoundException, IOException {
        
        System.out.println("\nWelcome to Administrator Console!");

        input = "0";
        while((input.matches("\\d") && Integer.parseInt(input)!=4) || (!input.matches("\\d"))) {
            
            System.out.println("\n\nEnter one of the options:\n1 - Add a question\n2 - Delete a question\n3 - Show questions\n4 - Exit");
            input = s.nextLine();
            
            switch(input){
                        case "1" :
                            
                            addQuestion(questions);

                            while (input.equals("1")) {
                                
                                System.out.println("\nAdd another question? 1 - yes, 2 - no");
                                input = s.nextLine();
                                
                                if (input.equals("1")) addQuestion(questions);                              
                                if (input.equals("2")) break;
                                if (!input.equals("1") && !input.equals("2")) {
                                    System.out.println("\n[!] Wrong input.");
                                    input = "1";
                                }
                            }
                            
                            System.out.println("\nSave the questions list?  1 - yes, 2 - no");
                            input = s.nextLine();
                            
                            while (!input.equals("1") && !input.equals("2")) {                                
                                System.out.println("\n[!] Wrong input.");
                                System.out.println("\nSave the questions list?  1 - yes, 2 - no");
                                input = s.nextLine();
                            }
                            
                            if (Integer.parseInt(input)==1) {
                                FileOutputStream fout = new FileOutputStream(path);
                                ObjectOutputStream oout =  new ObjectOutputStream(fout);
                                oout.writeObject(questions);
                                oout.close();
                            }
                            break;
                        case "2" :
                            
                            input="1";
                            while(Integer.parseInt(input)==1){
                                if(!questions.isEmpty()){
                                    showQuestions(questions);
                                    
                                    System.out.println("\nEnter question number you'd like to delete:");
                                    input = s.nextLine();
                                    
                                    deleteQuestions(questions, Integer.parseInt(input));
                                    showQuestions(questions);
                                    
                                    System.out.println("\nDelete another question? 1 - yes, 2 - no");
                                    input = s.nextLine();
                                }
                                else {
                                    System.out.println("\nThe list is empty");
                                    input = "-1";
                                }
                            }

                            System.out.println("\nSave to file? 1 - yes, 2 - no");
                            input = s.nextLine();
                            
                            if (Integer.parseInt(input)==1) {
                                FileOutputStream fout = new FileOutputStream(path);
                                ObjectOutputStream oout =  new ObjectOutputStream(fout);
                                oout.writeObject(questions);
                                oout.close();
                            }
                            break;
                        case "3" :
                            
                            if(!questions.isEmpty()){
                                showQuestions(questions);
                            }
                            else System.out.println("\nThe list is empty");
                            break;
                    }
            
        }
        
        
    
    }
    
    
    private void addQuestion(HashMap<String, ArrayList<Question>> qlist){
        
        Scanner s = new Scanner(System.in);
        Question q = null;
        
        String type = "" , question = "", category ="", answer = "";
        int level = 0, id;
        ArrayList<String> ans_options = new ArrayList<>();
        
        String input;
        
        // Ask user for question's type
        System.out.println("\nPlease choose the question type:\n1 - open question\n2 - multi-choice question\n3 - yes/no question");
        input = s.nextLine();
        
        while (!input.matches("\\d") ||  (input.matches("\\d") && (Integer.parseInt(input)<1 || Integer.parseInt(input)>3)) ) {
            System.out.println("[!] Error: wrong input!");
            System.out.println("\nPlease choose the question type:\n1 - open question\n2 - multi-choice question\n3 - yes/no question");
            input = s.nextLine();
        }
        switch(input){
            case "1" : type = "open";
                break;
            case "2" : type = "multi";
                break;
            case "3" : type = "yesno";
                break;
        }
        
        // Ask user for question's level
        System.out.println("\nEnter question's level:\n1 - Easy\n2 - Medium\n3 - Hard");
        input = s.nextLine();
        while (!input.matches("\\d") ||  (input.matches("\\d") && (Integer.parseInt(input)<1 || Integer.parseInt(input)>3)) ) {            
            System.out.println("[!] Error: wrong input!");
            System.out.println("\nEnter question's level:\n1 - Easy\n2 - Medium\n3 - Hard");
        }
        switch(input){
            case "1" : level = 1;
                break;
            case "2" : level = 2;
                break;
            case "3" : level = 3;
        }
        
        // Ask user for question's category
        System.out.println("\nType the question's category e.g. history/sport/politics...");
        //s.nextLine();
        category = s.nextLine();
        
        // Ask user to enter the question and answer(s)
        System.out.println("\nType in your question:");
        question = s.nextLine();        
        switch (type) {
            case "open":
                System.out.println("\nType in the correct answer:");
                answer = s.nextLine();
                break;
            case "multi":
                boolean finished =false;
                int i=2;
                String option;
                System.out.println("\nAdd answer option "+1+":");
                option =  s.nextLine();                
                ans_options.add(option);
                while(!finished){
                    System.out.println("\nDo you want to add another answer option? y/n");
                    option =  s.nextLine();
                    switch (option) {
                        case "n":
                            finished=true;
                            break;
                        case "y":
                            System.out.println("\nOption number "+i+":");
                            option =  s.nextLine();
                            ans_options.add(option);
                            i++;
                            break;
                        default:
                            System.out.println("\n[!] Error: wrong input");
                            break;
                    }
                }
                System.out.println("\nEnter the correct answer:");
                answer = s.nextLine();
                break;
            case "yesno":
                System.out.println("\nEnter \"Yes\" or \"No\" for correct answer:");
                answer = s.nextLine();
                ans_options.add("Yes");
                ans_options.add("No");
                break;
        }
        
        if (!qlist.containsKey(category)) {
            qlist.put(category, new ArrayList<Question>());
        }
        
        id = qlist.get(category).size() + 1;   // set the id for new question       
        
        // Finally, create the questions according to chosen type
        switch(type){
            case "open":
                q = new OpenQuestion(id, level, category, question, answer);
                break;
            case "multi":
                q = new MultiOptionsQuestion(id, level, category, question, answer, ans_options);
                break;
            case "yesno":
                q = new YesNoQuestion(id, level, category, question, answer, ans_options);
        }
        
        
        if (qlist.containsKey(category)) {
            qlist.get(category).add(q);
        }else{
            qlist.put(category, new ArrayList<Question>());
            qlist.get(category).add(q);
        }
        
    }
    
    
    private void showQuestions(HashMap<String, ArrayList<Question>> qlist){
        System.out.println("");     // print empty line
        
        for (String key : qlist.keySet()) {
            System.out.println("> Category: "+key);
            for (Question q : qlist.get(key)) {
                System.out.println(q.id+" - "+q.question);
            }
        }

    }
    
    
    private void deleteQuestions(HashMap<String, ArrayList<Question>> qlist, int i){
        
        for (Map.Entry<String, ArrayList<Question>> entrySet : qlist.entrySet()) {
            String key = entrySet.getKey();
            ArrayList<Question> value = entrySet.getValue();
            for (Question question : value) {
                if (question.id==i) {
                    qlist.get(key).remove(question);
                }
            }
        }
        
        
        for (Map.Entry<String, ArrayList<Question>> entrySet : qlist.entrySet()) {
            String key = entrySet.getKey();
            ArrayList<Question> value = entrySet.getValue();
            // set new ids
            int id = 1;
            for (Question q : value) {
                q.id = id++;
            }
        }
        

    }
    
}