/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shared.cams;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Alert {
    
    public static void showAlert(JFrame parent,String title,String message,String messageType){
        
        if(null != messageType.toLowerCase())switch (messageType.toLowerCase()) {
            case "i":
                JOptionPane.showMessageDialog(parent,message,title,JOptionPane.INFORMATION_MESSAGE);
                break;
            case "w":
                JOptionPane.showMessageDialog(parent,message,title,JOptionPane.WARNING_MESSAGE);    
                break;
            default:
                JOptionPane.showMessageDialog(parent,message,title,JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
