/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triviaquiz;

/**
 *
 * @author Boaz
 */
public class User {
    private int password = 1234;
    public String type = "player";
    
    public User(){
    }
    public User(int pass){
        if(pass==password){
            type="admin";
        }
        else{
            System.out.println("Wrong password!\n");
        }
    }
    
}
