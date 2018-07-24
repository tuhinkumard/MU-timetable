/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.bean;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 *
 * @author MAHE
 */
public class TheDate {
    public String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
    public String change(String date){
        String first_four = date.substring(0, 4);
        String last_two = date.substring(8, 10);
        String middle = date.substring(4, 8);
        return last_two + middle + first_four;
        
        
    }
    
}
