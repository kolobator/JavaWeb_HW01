/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;

import java.util.*;

/**
 *
 * @author KoLoBoK
 */
public class MultiOptionsQuestion extends Question{
    ArrayList<String> ans_options = new ArrayList<>();      // list of answer options

    MultiOptionsQuestion(int id, int level, String category, String question, String answer, ArrayList<String> ans_options) {
        this.id             = id;
        this.level          = level;
        this.category       = category;
        this.question       = question;
        this.correct_answer = answer;
        this.ans_options    = ans_options;
    }
}