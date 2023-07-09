/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.forms.cams;

import com.database.cams.DatabaseManager;
import com.models.cams.Appointment;
import com.models.cams.Patient;
import com.models.cams.Prescription;
import com.models.cams.Staff;
import com.models.cams.TimeSlot;
import com.models.cams.User;
import com.shared.cams.Alert;
import com.shared.cams.Helper;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class GUIDashboard extends javax.swing.JFrame {

    /**
     * Creates new form GUIDashboard
     */
    static User user = null;
    
    public GUIDashboard(User user) {
        initComponents();
        
        this.user = user;
        lblLogger.setText("Logged in as: "+user.getName());
        
        btnUser.setVisible(user instanceof Staff);
        btnTimeSlot.setVisible(user instanceof Staff);
        btnAddAppt.setVisible(user instanceof Patient);
        btnConfirmAppt.setVisible(user instanceof Staff);
        btnAddPrescription.setVisible(user instanceof Staff);
        
        uiChangeMenu("appt");
        
        if(user instanceof Staff){
            GetTimeSlotList(true);
            GetUserList();            
        }
        GetAppointmentList(true);
        GetMedicalList(true);         
    }
    
    private void GetTimeSlotList(boolean hideColumn){        
        ArrayList<TimeSlot> slotList = DatabaseManager.getTimeSlotByValue(this,"");

        DefaultTableModel tableModel = (DefaultTableModel)tblTimeSlot.getModel();
        tableModel.setRowCount(0);
        
        if(hideColumn){
            TableColumnModel cm = tblTimeSlot.getColumnModel();
            cm.removeColumn(cm.getColumn(0));
        }       
        
        if(slotList != null && !slotList.isEmpty()){            
            for(TimeSlot slot:slotList){
                tableModel.addRow(new Object[]{slot.getTimeSlotId(),slot.getStart(),slot.getEnd()});
            }
        }
    }
    
    private void GetUserList(){        
        ArrayList<User> userList = DatabaseManager.getUserByValue(this,"","","");

        DefaultTableModel tableModel = (DefaultTableModel)tblUsers.getModel();
        tableModel.setRowCount(0);
        
        if(userList != null && !userList.isEmpty()){            
            for(User user:userList){
                tableModel.addRow(new Object[]{user.getId(),user.getName(),user.getEmail(),user.getContact(),(user instanceof Patient ? "Patient":"Staff")});
            }
        }
    }
    
    private void GetAppointmentList(boolean hideColumn){        
        ArrayList<Appointment> data = DatabaseManager.getAppointmentByValue(this,"",(user instanceof Staff)? "":user.getId(),"");

        DefaultTableModel tableModel = (DefaultTableModel)tblAppointments.getModel();
        tableModel.setRowCount(0);
        
        if(hideColumn){
            TableColumnModel cm = tblAppointments.getColumnModel();
            cm.removeColumn(cm.getColumn(0));
        } 
        
        if(data != null && !data.isEmpty()){            
            for(Appointment appt:data){
                ArrayList<User> pt = DatabaseManager.getUserByValue(this, appt.getPatientId(), "", "");
                ArrayList<User> doct = DatabaseManager.getUserByValue(this, appt.getDoctorId(), "", "");
                tableModel.addRow(new Object[]{appt.getAppointmentId(),appt.getDate(),pt.get(0).getName(),doct.get(0).getName(),appt.getStartTime(),appt.getEndTime(),appt.getApprovalStatus()});
            }
        }
    }
    
    private void GetMedicalList(boolean hideColumn){        
        ArrayList<Prescription> data = DatabaseManager.getPrescriptionByValue(this,"",(user instanceof Staff)? "":user.getId(),"");

        DefaultTableModel tableModel = (DefaultTableModel)tblMedical.getModel();
        tableModel.setRowCount(0);
        
        if(hideColumn){
            TableColumnModel cm = tblMedical.getColumnModel();
            cm.removeColumn(cm.getColumn(0));
        } 
        
        if(data != null && !data.isEmpty()){            
            for(Prescription presc:data){
                ArrayList<User> pt = DatabaseManager.getUserByValue(this, presc.getPatientId(), "", "");
                ArrayList<User> doct = DatabaseManager.getUserByValue(this, presc.getDoctorId(), "", "");
                tableModel.addRow(new Object[]{presc.getTransactionId(),presc.getDateGiven(),pt.get(0).getName(),doct.get(0).getName(),presc.getDescription()});
            }
        }
    }
    
    private void uiChangeMenu(String menuType){
        panelUsers.setVisible(menuType.toLowerCase().equals("us"));
        panelMedical.setVisible(menuType.toLowerCase().equals("md"));
        panelTimeSlot.setVisible(menuType.toLowerCase().equals("ts"));
        panelAppointments.setVisible(menuType.toLowerCase().equals("appt"));
        
        switch (menuType.toLowerCase()) {
            case "appt":
                lblHeader.setText("Appointments");
                break;
            case "md":
                lblHeader.setText("Medical History");
                break;
            case "us":
                lblHeader.setText("Users");
                break;
            case "ts":
                lblHeader.setText("Time Slot");
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblLogger = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelMedical = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMedical = new javax.swing.JTable();
        btnAddPrescription = new javax.swing.JButton();
        panelAppointments = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        btnCancelAppt = new javax.swing.JButton();
        btnConfirmAppt = new javax.swing.JButton();
        btnAddAppt = new javax.swing.JButton();
        panelUsers = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnDeleteUser = new javax.swing.JButton();
        btnEditUser = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        panelTimeSlot = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTimeSlot = new javax.swing.JTable();
        btnSlotDelete = new javax.swing.JButton();
        btnSlotEdit = new javax.swing.JButton();
        btnSlotAdd = new javax.swing.JButton();
        lblHeader = new javax.swing.JLabel();
        btnAppointment = new javax.swing.JButton();
        btnMedicalHistory = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnTimeSlot = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CAMS-Dashboard");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, 30));

        lblLogger.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLogger.setForeground(new java.awt.Color(255, 255, 0));
        lblLogger.setText("Logged in as: Guest");
        lblLogger.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblLoggerAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel2.add(lblLogger, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 70));

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMedical.setPreferredSize(new java.awt.Dimension(740, 270));
        panelMedical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMedical.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Patient", "Specialist", "Description"
            }
        ));
        jScrollPane2.setViewportView(tblMedical);

        panelMedical.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 210));

        btnAddPrescription.setText("Add");
        btnAddPrescription.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPrescriptionActionPerformed(evt);
            }
        });
        panelMedical.add(btnAddPrescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 70, -1));

        jPanel3.add(panelMedical, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        panelAppointments.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Patient", "Specialist", "Start", "End", "Status"
            }
        ));
        jScrollPane5.setViewportView(tblAppointments);

        panelAppointments.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 200));

        btnCancelAppt.setText("Cancel");
        btnCancelAppt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelAppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelApptActionPerformed(evt);
            }
        });
        panelAppointments.add(btnCancelAppt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, -1, -1));

        btnConfirmAppt.setText("Confirm");
        btnConfirmAppt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmAppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmApptActionPerformed(evt);
            }
        });
        panelAppointments.add(btnConfirmAppt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, -1));

        btnAddAppt.setText("Add");
        btnAddAppt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddAppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddApptActionPerformed(evt);
            }
        });
        panelAppointments.add(btnAddAppt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jPanel3.add(panelAppointments, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 740, 270));

        panelUsers.setPreferredSize(new java.awt.Dimension(740, 270));
        panelUsers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Id", "Full Name", "Email", "Contact", "Type"
            }
        ));
        jScrollPane3.setViewportView(tblUsers);

        panelUsers.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 210));

        btnDeleteUser.setText("Delete");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });
        panelUsers.add(btnDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, -1));

        btnEditUser.setText("Edit");
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });
        panelUsers.add(btnEditUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 60, -1));

        btnAddUser.setText("Add");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        panelUsers.add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 60, -1));

        jPanel3.add(panelUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        panelTimeSlot.setPreferredSize(new java.awt.Dimension(740, 270));
        panelTimeSlot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTimeSlot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Slot Id", "Start Time", "End Time"
            }
        ));
        jScrollPane4.setViewportView(tblTimeSlot);

        panelTimeSlot.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 210));

        btnSlotDelete.setText("Delete");
        btnSlotDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSlotDeleteActionPerformed(evt);
            }
        });
        panelTimeSlot.add(btnSlotDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, -1));

        btnSlotEdit.setText("Edit");
        btnSlotEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSlotEditActionPerformed(evt);
            }
        });
        panelTimeSlot.add(btnSlotEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, -1, -1));

        btnSlotAdd.setText("Add");
        btnSlotAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSlotAddActionPerformed(evt);
            }
        });
        panelTimeSlot.add(btnSlotAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, -1, -1));

        jPanel3.add(panelTimeSlot, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setText("Appointments");
        jPanel3.add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        btnAppointment.setText("Appointments");
        btnAppointment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppointmentActionPerformed(evt);
            }
        });
        jPanel3.add(btnAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 150, 36));

        btnMedicalHistory.setText("Medical History");
        btnMedicalHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMedicalHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicalHistoryActionPerformed(evt);
            }
        });
        jPanel3.add(btnMedicalHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 140, 36));

        btnUser.setText("Users");
        btnUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });
        jPanel3.add(btnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 150, 36));

        btnTimeSlot.setText("Time Slot");
        btnTimeSlot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimeSlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeSlotActionPerformed(evt);
            }
        });
        jPanel3.add(btnTimeSlot, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 150, 36));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 760, 360));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        GUILogin gLogin = new GUILogin();
        gLogin.setLocationRelativeTo(null);
        gLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppointmentActionPerformed
        // TODO add your handling code here:
        uiChangeMenu("appt");
    }//GEN-LAST:event_btnAppointmentActionPerformed

    private void btnMedicalHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicalHistoryActionPerformed
        // TODO add your handling code here:
        uiChangeMenu("md");
    }//GEN-LAST:event_btnMedicalHistoryActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        // TODO add your handling code here:
        uiChangeMenu("us");
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnTimeSlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeSlotActionPerformed
        // TODO add your handling code here:
        uiChangeMenu("ts");
    }//GEN-LAST:event_btnTimeSlotActionPerformed

    private void btnSlotAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSlotAddActionPerformed
        // TODO add your handling code here:
        GUISlotManager gSlot = new GUISlotManager(this,user,null);
        gSlot.setLocationRelativeTo(null);
        gSlot.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSlotAddActionPerformed

    private void btnSlotEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSlotEditActionPerformed
        // TODO add your handling code here:        
        if (user instanceof Staff){          
            TableModel tm = tblTimeSlot.getModel();
            int row = tblTimeSlot.getSelectedRow();
            
            if(row < 0){
                Alert.showAlert(this, "Warning","Please select record to edit", "w");
                return;
            }
            
            String searchKey = tm.getValueAt(tblTimeSlot.getSelectedRow(),0).toString();
            
            ArrayList<TimeSlot> slotInfo = DatabaseManager.getTimeSlotByValue(this,searchKey);
            
            if(slotInfo == null || slotInfo.isEmpty()){
                Alert.showAlert(this, "Warning","Please select record to edit", "w");
                return;
            }
        
            GUISlotManager gSlotManager = new GUISlotManager(this,user,slotInfo.get(0));
            gSlotManager.setLocationRelativeTo(null);
            gSlotManager.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnSlotEditActionPerformed

    private void btnSlotDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSlotDeleteActionPerformed
        // TODO add your handling code here:
        
        if (user instanceof Staff){          
            TableModel tm = tblTimeSlot.getModel();
            int row = tblTimeSlot.getSelectedRow();
            
            if(row < 0){
                Alert.showAlert(this, "Warning","Please select record to delete", "w");
                return;
            }
            
            String searchKey = tm.getValueAt(tblTimeSlot.getSelectedRow(),0).toString();
            
            ArrayList<TimeSlot> slotInfo = DatabaseManager.getTimeSlotByValue(this,searchKey);
            
            if(slotInfo == null || slotInfo.isEmpty()){
                Alert.showAlert(this, "Warning","Please select record to delete", "w");
                return;
            }
            
            int result = Helper.showConfirmDialog(this, "Do you want to delete slot details ?");
        
            if(result == JOptionPane.YES_OPTION){
                boolean isDeleted = DatabaseManager.deleteTimeSlot(this, slotInfo.get(0));
                
                if(isDeleted){
                    GetTimeSlotList(false);                    
                }
                
                Alert.showAlert(this, "Info","Record deleted successfully", "i");
            }        
        }
    }//GEN-LAST:event_btnSlotDeleteActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        GUIUserManager uSlot = new GUIUserManager(this,user,null);
        uSlot.setLocationRelativeTo(null);
        uSlot.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        // TODO add your handling code here:
        if (user instanceof Staff){          
            TableModel tm = tblUsers.getModel();
            int row = tblUsers.getSelectedRow();
            
            if(row < 0){
                Alert.showAlert(this, "Warning","Please select record to edit", "w");
                return;
            }
            
            String searchKey = tm.getValueAt(tblUsers.getSelectedRow(),0).toString();
            
            ArrayList<User> userInfo = DatabaseManager.getUserByValue(this,searchKey,"","");
            
            if(userInfo == null || userInfo.isEmpty()){
                Alert.showAlert(this, "Warning","Please select record to edit", "w");
                return;
            }
        
            GUIUserManager gUserManager = new GUIUserManager(this,user,userInfo.get(0));
            gUserManager.setLocationRelativeTo(null);
            gUserManager.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        // TODO add your handling code here:
        
        if (user instanceof Staff){          
            TableModel tm = tblUsers.getModel();
            int row = tblUsers.getSelectedRow();
            
            if(row < 0){
                Alert.showAlert(this, "Warning","Please select record to delete", "w");
                return;
            }
            
            String searchKey = tm.getValueAt(tblUsers.getSelectedRow(),0).toString();
            
            ArrayList<User> data = DatabaseManager.getUserByValue(this,searchKey,"","");
            
            if(data == null || data.isEmpty()){
                Alert.showAlert(this, "Warning","Please select record to delete", "w");
                return;
            }
            
            int result = Helper.showConfirmDialog(this, "Do you want to delete user details ?");
        
            if(result == JOptionPane.YES_OPTION){
                boolean isDeleted = DatabaseManager.deleteUser(this, data.get(0));
                
                if(isDeleted){
                    GetUserList();                    
                }
                
                Alert.showAlert(this, "Info","Record deleted successfully", "i");
            }        
        }
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void btnAddApptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddApptActionPerformed
        // TODO add your handling code here:
        GUIAppointmentManager gAppt = new GUIAppointmentManager(this,user,null);
        gAppt.setLocationRelativeTo(null);
        gAppt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddApptActionPerformed

    private void btnConfirmApptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmApptActionPerformed
        // TODO add your handling code here:
        
        if(user instanceof Staff){
            TableModel tm = tblAppointments.getModel();
            int row = tblAppointments.getSelectedRow();

            if(row < 0){
                Alert.showAlert(this, "Warning","Please select record to confirm", "w");
                return;
            }

            String searchKey = tm.getValueAt(tblAppointments.getSelectedRow(),0).toString();

            ArrayList<Appointment> data = DatabaseManager.getAppointmentByValue(this,searchKey,"","");

            if(data == null || data.isEmpty()){
                Alert.showAlert(this, "Warning","Please select record to confirm", "w");
                return;
            }

            if(data.get(0).getApprovalStatus().equals("Approved")){
                Alert.showAlert(this, "Warning","Record has already been confirmed", "w");
                return;
            }

            if(data.get(0).getApprovalStatus().equals("Cancelled")){
                Alert.showAlert(this, "Warning","Record has already been cancelled. It cannot be confirmed", "w");
                return;
            }

            int result = Helper.showConfirmDialog(this, "Do you want to confirm selected appointment ?");

            if(result == JOptionPane.YES_OPTION){
                data.get(0).setApprovalStatus("Approved");
                boolean isUpdated = DatabaseManager.updateAppointment(this, data.get(0));

                if(isUpdated){
                    GetAppointmentList(false);                    
                }

                Alert.showAlert(this, "Info","Record confirmed successfully", "i");
            }
        }   
    }//GEN-LAST:event_btnConfirmApptActionPerformed

    private void btnCancelApptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelApptActionPerformed
        // TODO add your handling code here:
        TableModel tm = tblAppointments.getModel();
        int row = tblAppointments.getSelectedRow();

        if(row < 0){
            Alert.showAlert(this, "Warning","Please select record to cancel", "w");
            return;
        }

        String searchKey = tm.getValueAt(tblAppointments.getSelectedRow(),0).toString();

        ArrayList<Appointment> data = DatabaseManager.getAppointmentByValue(this,searchKey,"","");

        if(data == null || data.isEmpty()){
            Alert.showAlert(this, "Warning","Please select record to cancel", "w");
            return;
        }
       
        if(data.get(0).getApprovalStatus().equals("Approved")){
            Alert.showAlert(this, "Warning","Record has already been confirmed. It cannot be cancelled", "w");
            return;
        }

        if(data.get(0).getApprovalStatus().equals("Cancelled")){
            Alert.showAlert(this, "Warning","Record has already been cancelled", "w");
            return;
        }

        int result = Helper.showConfirmDialog(this, "Do you want to cancel selected appointment ?");

        if(result == JOptionPane.YES_OPTION){
            data.get(0).setApprovalStatus("Cancelled");
            boolean isUpdated = DatabaseManager.updateAppointment(this, data.get(0));

            if(isUpdated){
                GetAppointmentList(false);                    
            }

            Alert.showAlert(this, "Info","Record cancelled successfully", "i");
        }
    }//GEN-LAST:event_btnCancelApptActionPerformed

    private void btnAddPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPrescriptionActionPerformed
        // TODO add your handling code here:
        GUIPrescriptionManager gAppt = new GUIPrescriptionManager(this,user,null);
        gAppt.setLocationRelativeTo(null);
        gAppt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddPrescriptionActionPerformed

    private void lblLoggerAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblLoggerAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblLoggerAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIDashboard(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAppt;
    private javax.swing.JButton btnAddPrescription;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnAppointment;
    private javax.swing.JButton btnCancelAppt;
    private javax.swing.JButton btnConfirmAppt;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMedicalHistory;
    private javax.swing.JButton btnSlotAdd;
    private javax.swing.JButton btnSlotDelete;
    private javax.swing.JButton btnSlotEdit;
    private javax.swing.JButton btnTimeSlot;
    private javax.swing.JButton btnUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblLogger;
    private javax.swing.JPanel panelAppointments;
    private javax.swing.JPanel panelMedical;
    private javax.swing.JPanel panelTimeSlot;
    private javax.swing.JPanel panelUsers;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTable tblMedical;
    private javax.swing.JTable tblTimeSlot;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}
