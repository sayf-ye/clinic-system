/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database.cams;
import com.models.cams.Address;
import com.models.cams.Appointment;
import com.models.cams.Patient;
import com.models.cams.Prescription;
import com.models.cams.Staff;
import com.models.cams.TimeSlot;
import com.models.cams.User;
import com.shared.cams.Alert;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class DatabaseManager {
    static String userTable = "users.txt";   
    static String appointmentTable = "appointments.txt"; 
    static String timeSlotTable = "timeslots.txt";
    static String prescriptionTable = "prescriptions.txt";
    
    ///User functions
    public static boolean addUser(JFrame parentPage,ArrayList<User> userList){
        boolean result = false;
        try{
            if(createFile(parentPage,userTable)){
                ArrayList<User> currentUserList = getUserByValue(parentPage,"","","");
                
                currentUserList.addAll(userList); 
                
                if(deleteFile(parentPage,userTable)){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(userTable,true));  
                    
                    for(User user: currentUserList){
                        String line = user.getId()+"--"+user.getName()+"--"+user.getEmail()+"--"+user.getPassword()+"--"+user.getContact();                            
                        if(user instanceof Patient){
                            Address addr = ((Patient) user).getAddress();
                            line += "--"+addr.getCity()+"--"+addr.getState()+"--"+addr.getPostalCode();                            
                        }
                        bw.write(line);
                        bw.newLine();
                    }
                    
                    bw.close();  
                    result = true;
                }
                
            }
        }catch (Exception e) {
           Alert.showAlert(parentPage,"Error", "addUser: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }   
    
    public static ArrayList<User> getUserByValue(JFrame parentPage,String userId,String email,String userType){
        ArrayList<User> result = null;    
        BufferedReader br = null;   
        try{            
            if(createFile(parentPage,userTable)){
                result = new ArrayList<>(); 
                
                if(!checkEmptyFile(parentPage,userTable)){
                    
                    br = new BufferedReader(new FileReader(userTable));   
                    
                    String strCurrentLine;
                    
                    while ((strCurrentLine = br.readLine()) != null) {                        
                        String[] splittedData = strCurrentLine.split("--");
                        User us = null;
                        
                        if(splittedData.length == 5){     
                            us = new Staff(splittedData[1], splittedData[2], splittedData[3], splittedData[4]);                            
                            us.setId(splittedData[0]);                            
                        }else{
                            Address address = new Address();
                            address.setCity(splittedData[5]);
                            address.setState(splittedData[6]);
                            address.setPostalCode(splittedData[7]);
                            us = new Patient(address, splittedData[1], splittedData[2], splittedData[3], splittedData[4]);
                            us.setId(splittedData[0]);
                        }
                        
                        if(!userId.equals("") || !email.equals("") || !userType.equals("")){
                            if(!userId.equals("") && us.getId().equals(userId))
                            {
                                result.add(us);
                                break;
                            }

                            if(!email.equals("") && us.getEmail().equals(email))
                            {
                                result.add(us);
                                break;
                            }

                            if(!userType.equals(""))
                            {
                                if((us instanceof Staff) && userType.equals("staff")){
                                    result.add(us);
                                }   

                                if((us instanceof Patient) && userType.equals("patient")){
                                    result.add(us);
                                }
                            }
                        }else{
                            result.add(us);
                        }
                    }
                    
                    br.close();
                }
            }            
        } catch (Exception e) {
            Alert.showAlert(parentPage, "Error","getUserByValue: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }
    
    public static boolean updateUser(JFrame parentPage,User userInfo){
        boolean isUpdated = false; 
        boolean foundUser = false;
        try{
            ArrayList<User> currentUserList = getUserByValue(parentPage,"","",""); 
            ArrayList<User> newList = new ArrayList();
            
            if(currentUserList != null && !currentUserList.isEmpty()){
                for(User us:currentUserList){
                    if(us.getId().equals(userInfo.getId())){
                        newList.add(userInfo);
                        foundUser = true;
                    }else{
                        newList.add(us);
                    }
                }   
            }
            
            if(foundUser && deleteFile(parentPage,userTable) && createFile(parentPage,userTable)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(userTable,true));  
                    
                for(User user: newList){
                    String line = user.getId()+"--"+user.getName()+"--"+user.getEmail()+"--"+user.getPassword()+"--"+user.getContact();                            
                    if(user instanceof Patient){
                        Address addr = ((Patient) user).getAddress();
                        line += "--"+addr.getCity()+"--"+addr.getState()+"--"+addr.getPostalCode();                            
                    }
                    bw.write(line);
                    bw.newLine();
                }

                bw.close();  
                isUpdated = true;
            }
        } catch (Exception e) {
           Alert.showAlert(parentPage, "Error","updateUser: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return isUpdated;
    }
    
    public static boolean deleteUser(JFrame parentPage,User userInfo){
        boolean isDeleted = false; 
        try{
            ArrayList<User> currentUserList = getUserByValue(parentPage,"","",""); 
            ArrayList<User> newList = new ArrayList();
            
            if(currentUserList != null && !currentUserList.isEmpty()){
                for(User us:currentUserList){
                    if(!us.getId().equals(userInfo.getId())){
                        newList.add(us);
                    }
                }   
            }
            
            if(deleteFile(parentPage,userTable) && createFile(parentPage,userTable)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(userTable,true));  
                    
                for(User user: newList){
                    String line = user.getId()+"--"+user.getName()+"--"+user.getEmail()+"--"+user.getPassword()+"--"+user.getContact();                            
                    if(user instanceof Patient){
                        Address addr = ((Patient) user).getAddress();
                        line += "--"+addr.getCity()+"--"+addr.getState()+"--"+addr.getPostalCode();                            
                    }
                    bw.write(line);
                    bw.newLine();
                }

                bw.close();  
                isDeleted = true;
            }
        }catch (Exception e) {
           Alert.showAlert(parentPage,"Error", "deleteUser: Something went wrong. Please try again! If problem persist contact hospital managament", "");
        }
        return isDeleted;
    }
    
    ///Time Slot functions
    public static boolean addTimeSlot(JFrame parentPage,ArrayList<TimeSlot> slotList){
        boolean result = false;
        try{
            if(createFile(parentPage,timeSlotTable)){
                ArrayList<TimeSlot> currentList = getTimeSlotByValue(parentPage,"");
                
                currentList.addAll(slotList); 
                
                if(deleteFile(parentPage,timeSlotTable)){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(timeSlotTable,true));  
                    
                    for(TimeSlot slot: currentList){
                        String line = slot.getTimeSlotId()+"--"+slot.getStart()+"--"+slot.getEnd();
                        bw.write(line);
                        bw.newLine();
                    }
                    
                    bw.close();  
                    result = true;
                }
                
            }
        }catch (Exception e) {
           Alert.showAlert(parentPage,"Error", "addTimeSlot: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }   
    
    public static ArrayList<TimeSlot> getTimeSlotByValue(JFrame parentPage,String slotId){
        ArrayList<TimeSlot> result = null;    
        BufferedReader br = null;   
        try{            
            if(createFile(parentPage,timeSlotTable)){
                result = new ArrayList<>(); 
                
                if(!checkEmptyFile(parentPage,timeSlotTable)){
                    
                    br = new BufferedReader(new FileReader(timeSlotTable));   
                    
                    String strCurrentLine;
                    
                    while ((strCurrentLine = br.readLine()) != null) {                        
                        String[] splittedData = strCurrentLine.split("--");
                        TimeSlot slot = new TimeSlot(splittedData[1],splittedData[2]);
                        slot.setTimeSlotId(splittedData[0]);
                        
                        if(!slotId.equals("") && slot.getTimeSlotId().equals(slotId))
                        {
                            result.add(slot);
                            break;
                        }else{
                            result.add(slot);
                        }
                    }
                    
                    br.close();
                }
            }            
        } catch (Exception e) {
            Alert.showAlert(parentPage, "Error","getTimeSlotByValue: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }    
    
    public static boolean deleteTimeSlot(JFrame parentPage,TimeSlot slotInfo){
        boolean isDeleted = false; 
        try{
            ArrayList<TimeSlot> currentList = getTimeSlotByValue(parentPage,""); 
            ArrayList<TimeSlot> newList = new ArrayList();
            
            if(currentList != null && !currentList.isEmpty()){
                for(TimeSlot slot:currentList){
                    if(!slot.getTimeSlotId().equals(slotInfo.getTimeSlotId())){
                        newList.add(slot);
                    }
                }   
            }
            
            if(deleteFile(parentPage,timeSlotTable) && createFile(parentPage,timeSlotTable)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(timeSlotTable,true));  
                    
                for(TimeSlot slot: newList){
                    String line = slot.getTimeSlotId()+"--"+slot.getStart()+"--"+slot.getEnd();  
                    bw.write(line);
                    bw.newLine();
                }

                bw.close();  
                isDeleted = true;
            }
        }catch (Exception e) {
           Alert.showAlert(parentPage,"Error", "deleteTimeSlot: Something went wrong. Please try again! If problem persist contact hospital managament", "");
        }
        return isDeleted;
    }
    
    public static boolean updateTimeSlot(JFrame parentPage,TimeSlot slotInfo){
        boolean isUpdated = false; 
        boolean isFound = false;
        try{
            ArrayList<TimeSlot> currentList = getTimeSlotByValue(parentPage,""); 
            ArrayList<TimeSlot> newList = new ArrayList();
            
            if(currentList != null && !currentList.isEmpty()){
                for(TimeSlot slot:currentList){
                    if(slot.getTimeSlotId().equals(slotInfo.getTimeSlotId())){
                        newList.add(slotInfo);
                        isFound = true;
                    }else{
                        newList.add(slot);
                    }
                }   
            }
            
            if(isFound && deleteFile(parentPage,timeSlotTable) && createFile(parentPage,timeSlotTable)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(timeSlotTable,true));  
                    
                for(TimeSlot slot: newList){
                    String line = slot.getTimeSlotId()+"--"+slot.getStart()+"--"+slot.getEnd();  
                    
                    bw.write(line);
                    bw.newLine();
                }
                bw.close();  
                isUpdated = true;
            }
        } catch (Exception e) {
           Alert.showAlert(parentPage, "Error","updateTimeSlot: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return isUpdated;
    }
    
    ///Appointment functions
    public static boolean addAppointment(JFrame parentPage,ArrayList<Appointment> addList){
        boolean result = false;
        try{
            if(createFile(parentPage,appointmentTable)){
                ArrayList<Appointment> currentList = getAppointmentByValue(parentPage,"","","");
                
                currentList.addAll(addList); 
                
                if(deleteFile(parentPage,appointmentTable)){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(appointmentTable,true));  
                    
                    for(Appointment appt: currentList){
                        String line = appt.getAppointmentId()+"--"+appt.getPatientId()+"--"+appt.getDoctorId()+"--"+appt.getDate()+"--"+appt.getStartTime()+"--"+appt.getEndTime()+"--"+appt.getApprovalStatus();                            
                        
                        bw.write(line);
                        bw.newLine();
                    }
                    
                    bw.close();  
                    result = true;
                }
                
            }
        }catch (Exception e) {
           Alert.showAlert(parentPage,"Error", "addAppointment: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }   
    
    public static ArrayList<Appointment> getAppointmentByValue(JFrame parentPage,String apptId,String patientId,String doctorId){
        ArrayList<Appointment> result = null;    
        BufferedReader br = null;   
        try{            
            if(createFile(parentPage,appointmentTable)){
                result = new ArrayList<>(); 
                
                if(!checkEmptyFile(parentPage,appointmentTable)){
                    
                    br = new BufferedReader(new FileReader(appointmentTable));   
                    
                    String strCurrentLine;
                    
                    while ((strCurrentLine = br.readLine()) != null) {                        
                        String[] splittedData = strCurrentLine.split("--");
                        Appointment data = new Appointment(splittedData[1],splittedData[2], splittedData[3], splittedData[4], splittedData[5], splittedData[6]);
                        data.setAppointmentId(splittedData[0]);
                        
                        if(!apptId.equals("") || !patientId.equals("") || !doctorId.equals("")){
                            if(!apptId.equals("") && data.getAppointmentId().equals(apptId)){                            
                                result.add(data);
                                break;
                            }else if(!patientId.equals("") && data.getPatientId().equals(patientId)){
                                result.add(data);
                            }else if(!doctorId.equals("") && data.getDoctorId().equals(doctorId)){
                                result.add(data);
                            }
                        }else{
                            result.add(data);
                        }
                    }
                    
                    br.close();
                }
            }            
        } catch (Exception e) {
            Alert.showAlert(parentPage, "Error","getAppointmentByValue: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }
    
    public static boolean updateAppointment(JFrame parentPage,Appointment apptInfo){
        boolean isUpdated = false; 
        boolean isFound = false;
        try{
            ArrayList<Appointment> currentList = getAppointmentByValue(parentPage,"","",""); 
            ArrayList<Appointment> newList = new ArrayList();
            
            if(currentList != null && !currentList.isEmpty()){
                for(Appointment appt:currentList){
                    if(appt.getAppointmentId().equals(apptInfo.getAppointmentId())){
                        newList.add(apptInfo);
                        isFound = true;
                    }else{
                        newList.add(appt);
                    }
                }   
            }
            
            if(isFound && deleteFile(parentPage,appointmentTable) && createFile(parentPage,appointmentTable)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(appointmentTable,true));  
                    
                for(Appointment appt: newList){
                    String line = appt.getAppointmentId()+"--"+appt.getPatientId()+"--"+appt.getDoctorId()+"--"+appt.getDate()+"--"+appt.getStartTime()+"--"+appt.getEndTime()+"--"+appt.getApprovalStatus();                            
                    bw.write(line);
                    bw.newLine();
                }

                bw.close();  
                isUpdated = true;
            }
        } catch (Exception e) {
           Alert.showAlert(parentPage, "Error","updateAppointment: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return isUpdated;
    }    
    
    //Medical functions
    public static boolean addPrescription(JFrame parentPage,ArrayList<Prescription> addList){
        boolean result = false;
        try{
            if(createFile(parentPage,prescriptionTable)){
                ArrayList<Prescription> currentList = getPrescriptionByValue(parentPage,"","","");
                
                currentList.addAll(addList); 
                
                if(deleteFile(parentPage,prescriptionTable)){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(prescriptionTable,true));  
                    
                    for(Prescription presc: currentList){
                        String line = presc.getTransactionId()+"--"+presc.getPatientId()+"--"+presc.getDoctorId()+"--"+presc.getDateGiven()+"--"+presc.getDescription();                            
                        
                        bw.write(line);
                        bw.newLine();
                    }
                    
                    bw.close();  
                    result = true;
                }
                
            }
        }catch (Exception e) {
           Alert.showAlert(parentPage,"Error", "addPrescription: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }   
    
    public static ArrayList<Prescription> getPrescriptionByValue(JFrame parentPage,String transId,String patientId,String doctorId){
        ArrayList<Prescription> result = null;    
        BufferedReader br = null;   
        try{            
            if(createFile(parentPage,prescriptionTable)){
                result = new ArrayList<>(); 
                
                if(!checkEmptyFile(parentPage,prescriptionTable)){
                    
                    br = new BufferedReader(new FileReader(prescriptionTable));   
                    
                    String strCurrentLine;
                    
                    while ((strCurrentLine = br.readLine()) != null) {                        
                        String[] splittedData = strCurrentLine.split("--");
                        Prescription data = new Prescription(splittedData[1], splittedData[2], splittedData[3], splittedData[4]);
                        data.setTransactionId(splittedData[0]);
                        
                        if(!transId.equals("") || !patientId.equals("") || !doctorId.equals("")){
                            if(!transId.equals("") && data.getTransactionId().equals(transId)){                            
                                result.add(data);
                                break;
                            }else if(!patientId.equals("") && data.getPatientId().equals(patientId)){
                                result.add(data);
                            }else if(!doctorId.equals("") && data.getDoctorId().equals(doctorId)){
                                result.add(data);
                            }
                        }else{
                            result.add(data);
                        }
                    }
                    
                    br.close();
                }
            }            
        } catch (Exception e) {
            Alert.showAlert(parentPage, "Error","getPrescriptionByValue: Something went wrong. Please try again! If problem persist contact administrator", "");
        }
        return result;
    }
    
    public static boolean updatePrescription(JFrame parentPage,Prescription dataInfo){
        boolean isUpdated = false; 
        boolean isFound = false;
        try{
            ArrayList<Prescription> currentList = getPrescriptionByValue(parentPage,"","",""); 
            ArrayList<Prescription> newList = new ArrayList();
            
            if(currentList != null && !currentList.isEmpty()){
                for(Prescription presc:currentList){
                    if(presc.getTransactionId().equals(dataInfo.getTransactionId())){
                        newList.add(dataInfo);
                        isFound = true;
                    }else{
                        newList.add(presc);
                    }
                }   
            }
            
            if(isFound && deleteFile(parentPage,prescriptionTable) && createFile(parentPage,prescriptionTable)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(prescriptionTable,true));  
                    
                for(Prescription presc: newList){
                    String line = presc.getTransactionId()+"--"+presc.getPatientId()+"--"+presc.getDoctorId()+"--"+presc.getDateGiven()+"--"+presc.getDescription();                            
                    bw.write(line);
                    bw.newLine();
                }

                bw.close();  
                isUpdated = true;
            }
        } catch (Exception e) {
           Alert.showAlert(parentPage, "Error","updatePrescription: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return isUpdated;
    }   
    //COMMON FUNCTIONS    
    private static boolean createFile(JFrame parentPage,String fileName){
        boolean result = false;
        try{
            File myFile = new File(fileName);            
            result = myFile.exists() ? true : myFile.createNewFile();
        }catch(Exception ex){
            Alert.showAlert(parentPage, "Error","createFile: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return result;
    }
    
    private static boolean deleteFile(JFrame parentPage,String fileName){
        boolean result = false;
        try{
            File file = new File(fileName);
            result = file.delete() && createFile(parentPage,fileName);
        }catch(Exception ex){
            Alert.showAlert(parentPage, "Error","deleteFile: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return result;
    }
    
    private static boolean checkEmptyFile(JFrame parentPage,String fileName){
        boolean result = false;
        try{
            File myFile = new File(fileName);            
            result = myFile.length() == 0;
        }catch(Exception ex){
            Alert.showAlert(parentPage,"Error", "checkEmptyFile: Something went wrong. Please try again! If problem persist contact hospital management", "");
        }
        return result;
    }
}
