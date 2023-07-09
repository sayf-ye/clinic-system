/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shared.cams;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Helper {
    public static boolean validateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    
    public static String formatDate(String format,Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String result = dateFormat.format(date);
        return result;
    }
    
    public static String formatDateTime(String format,LocalDateTime date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String result = dateFormat.format(date);
        return result;
    }
    
    public static int showConfirmDialog(JFrame parentPage,String message){        
        return JOptionPane.showConfirmDialog(parentPage,message, "Confirmation",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
    }
    
    public static ArrayList<String> getTimeSlotList(){
        ArrayList<String> timeSlotList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        int startDate = cal.get(Calendar.DATE);
        while (cal.get(Calendar.DATE) == startDate && !df.format(cal.getTime()).equals("19:00")) {
            timeSlotList.add(df.format(cal.getTime()));
            cal.add(Calendar.MINUTE, 30);
        }
        return timeSlotList;
    }
    
    public static long getTimeDifference(String start,String end,String type){  
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date d1 = sdf.parse(start);
            Date d2 = sdf.parse(end);
            
            long diffInMillies = Math.abs(d2.getTime() - d1.getTime());

            switch (type.toLowerCase()) {
                case "m":
                    return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
                case "h":
                    return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                case "d":
                    return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                default:
                    return -1;
            }
        }catch(ParseException ex){
            return -1;
        }
    }
    
    public static ArrayList<String> getUserTypeList(){
        ArrayList<String> typeNames =  new ArrayList<>();  
        typeNames.add("Staff"); 
        typeNames.add("Patient");   
        return typeNames;
    }
}
