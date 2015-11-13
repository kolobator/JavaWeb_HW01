/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author KoLoBoK
 */
public class PlayerConsole extends User{

        Scanner s = new Scanner(System.in);
        String input;
        String[] categories;
    
    PlayerConsole(HashMap<String, ArrayList<Question>> questions) {
       
        System.out.println("\nHello, player!");
        categories = chooseCategories(questions);
        
        ArrayList<Question> done = new ArrayList<>();
        Random rnd = new Random();
        
        int total = 0;
        for (int i = 0; i < categories.length; i++) {
            total += questions.get(categories[i]).size();
        }
        System.out.println("[+] To exit game enter q");
        
        while (done.size()!=total) {            

            int ind = rnd.nextInt(categories.length);

            int bound = questions.get(categories[ind]).size();

            Question q  = questions.get(categories[ind]).get(rnd.nextInt(bound));

            if (done.contains(q)) {
                continue;
            }

            done.add(q);

            String ans = "";
            System.out.println("\n\n"+q.question);
            if (q instanceof OpenQuestion) {
                System.out.println("\nYour answer: ");
                ans = s.nextLine();
                if (ans.equals("q")) break;
                if (ans.equals(q.correct_answer)) {
                    System.out.println("\nCorrect!");
                }else{
                    System.out.println("\nWrong. Correct answer: "+q.correct_answer);
                }
            }
            if (q instanceof MultiOptionsQuestion) {
                MultiOptionsQuestion mq = (MultiOptionsQuestion)q;
                for (int i = 0; i < mq.ans_options.size(); i++) {
                    System.out.println(i+1+" - "+mq.ans_options.get(i));
                }
                System.out.println("\nYour answer (enter option number):");
                ans = s.nextLine();
                if (ans.equals("q")) break;
                if (mq.ans_options.get(Integer.parseInt(ans)-1).equals(mq.correct_answer)) {
                    System.out.println("\nCorrect!");
                }else{
                    System.out.println("\nWrong. Correct answer: "+mq.correct_answer);
                }
            }
        }
        System.out.println("\n\nGame over :) ");
        
    }
    
    
    /**
     *
     * @param questions
     * @return
     */
    public static String[] chooseCategories(HashMap<String, ArrayList<Question>> questions){
        
        String input;
        Scanner s  = new Scanner(System.in);

        System.out.println("\nPlease choose one or more of the following categories, seperated with commas (e.g. 2,5,4):");
        
        String[] cats = new String[questions.keySet().size()];
        int i = 0;
        for (String cat : questions.keySet()) {
            System.out.println(i+1+" "+cat);
            cats[i] = cat;
            i++;
        }
        input = s.nextLine();
        String[] playerCats = input.split(",");
        String[] categories = new String[playerCats.length];
        
        System.out.println("\nYou've chosen the following categories:");
        for (int j = 0; j < playerCats.length; j++) {
            i = Integer.parseInt(playerCats[j]) - 1;
            System.out.print(cats[i]);
            if (j<playerCats.length-1) System.out.print(", ");
            categories[j] = cats[i];
        }
        System.out.println("");
        return categories;
    }
}
