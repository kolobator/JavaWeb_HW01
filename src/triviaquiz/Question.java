/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;
import java.io.Serializable;

/**
 *
 * @author Boaz
 */
public abstract class Question implements Serializable{
    int id;                        // the question number will be used in deletion
    
    String question;               // the question
    String correct_answer;         // the correct answer
    
    int level;                     // 1-easy 2-medium 3-hard
    String category;               // questions categorey: Sport, history ....
}