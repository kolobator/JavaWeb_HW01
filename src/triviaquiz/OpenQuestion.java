/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;

/**
 *
 * @author KoLoBoK
 */
public class OpenQuestion extends Question{

    OpenQuestion(int id, int level, String category, String question, String answer) {
        this.id             = id;
        this.level          = level;
        this.category       = category;
        this.question       = question;
        this.correct_answer = answer;
    }
    
}