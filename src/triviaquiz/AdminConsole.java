/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;
import java.util.*;

/**
 *
 * @author Boaz
 */
public class AdminConsole {
    
    public void addQuestion(ArrayList<Question> qlist){
        
        Scanner s = new Scanner(System.in);
        Question q = null;
        
        String type = "" , question = "", category ="", answer = "";
        int level = 0, id;
        ArrayList<String> ans_options = new ArrayList<>();
        
        int numInput;
        int ind = qlist.size() + 1;
        
        // Ask user for question's type
        System.out.println("\nPlease choose the question type:\n1 - open question\n2 - multi-choice question\n3 - yes/no question\n");
        numInput = s.nextInt();
        while (numInput <= 0 || numInput > 3) {
            System.out.println("Error: wrong input!");
            System.out.println("\nPlease choose the question type:\n1 - open question\n2 - multi-choice question\n3 - yes/no question\n");
            numInput = s.nextInt();
        }
        switch(numInput){
            case 1 : type = "open";
                break;
            case 2 : type = "multi";
                break;
            case 3 : type = "yesno";
                break;
        }
        
        // Ask user for question's level
        System.out.println("Enter question's level:\n1 - easy\n2 - medium\n3 - hard\n");
        numInput = s.nextInt();
        switch(numInput){
            case 1 : level = 1;
                break;
            case 2 : level = 2;
                break;
            case 3 : level = 3;
        }
        
        // Ask user for question's category
        System.out.println("Type the question's category e.g. history/sport/politics...\n");
        s.nextLine();
        category = s.nextLine();
        
        // Ask user to enter the question and answer(s)
        System.out.println("Type in your question:\n");
        question = s.nextLine();        
        switch (type) {
            case "open":
                System.out.println("Type in the correct answer:");
                answer = s.nextLine();
                break;
            case "multi":
                boolean finished =false;
                int i=2;
                String option;
                System.out.println("Add answer option "+1+":");
                option =  s.nextLine();                
                ans_options.add(option);
                while(!finished){
                    System.out.println("Do you want to add another answer option? y/n");
                    option =  s.nextLine();
                    switch (option) {
                        case "n":
                            finished=true;
                            break;
                        case "y":
                            System.out.println("Option number "+i+":");
                            option =  s.nextLine();
                            ans_options.add(option);
                            i++;
                            break;
                        default:
                            System.out.println("Wrong input");
                            break;
                    }
                }
                System.out.println("Enter the correct answer:");
                answer = s.nextLine();
                break;
            case "yesno":
                System.out.println("Enter \"Yes\" or \"No\" for correct answer:\n");
                answer = s.nextLine();
                ans_options.add("Yes");
                ans_options.add("No");
                break;
        }
        
        id = ind;   // set the id for new question
        
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
        
        qlist.add(q);
    }
    
    
    public void showQuestions(ArrayList<Question> qlist){
        for (Question q : qlist) {
            System.out.println(q.id+" - "+q.question);
        }
    }
    
    
    public void deleteQuestions(ArrayList<Question> qlist, int i){
        for (int j = 0; j < qlist.size(); j++) {
            if(qlist.get(j).id==i){
                qlist.remove(j);
            }
        }
        
        // set new ids
        int id = 1;
        for (Question q : qlist) {
            q.id = id++;
        }
    }
    
}