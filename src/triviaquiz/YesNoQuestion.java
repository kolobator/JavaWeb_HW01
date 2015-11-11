/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;

import java.util.ArrayList;

/**
 *
 * @author KoLoBoK
 */
public class YesNoQuestion extends MultiOptionsQuestion{

    public YesNoQuestion(int id, int level, String category, String question, String answer, ArrayList<String> ans_options) {
        super(id, level, category, question, answer, ans_options);
    }
    
}