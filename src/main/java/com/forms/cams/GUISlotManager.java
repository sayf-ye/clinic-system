/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.forms.cams;

import com.database.cams.DatabaseManager;
import com.models.cams.TimeSlot;
import com.models.cams.User;
import com.shared.cams.Alert;
import com.shared.cams.Helper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class GUISlotManager extends javax.swing.JFrame {

    /**
     * Creates new form GUISlotManager
     */
    
    
    static JFrame parentPage;
    static User user;
    static TimeSlot slot = null;    
    
    public GUISlotManager(JFrame parentPage,User user,TimeSlot slot) {
        initComponents();
        
        this.parentPage = parentPage;
        this.user = user;
        this.slot = slot;
        
        getTimeSlotList();
    }
    
    private void getTimeSlotList(){
        cmbSlotStart.removeAllItems();
        cmbSlotEnd.removeAllItems();
        ArrayList<String> slotList = Helper.getTimeSlotList();
        for(String slotInfo:slotList){
            cmbSlotStart.addItem(slotInfo);
            cmbSlotEnd.addItem(slotInfo);
        }
        cmbSlotStart.setSelectedItem(null);
        cmbSlotEnd.setSelectedItem(null);
        
        if(slot != null){
            cmbSlotStart.setSelectedItem(slot.getStart());
            cmbSlotEnd.setSelectedItem(slot.getEnd());
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbSlotEnd = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbSlotStart = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CAMS-Time Slot");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        btnSave.setText("Save");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setText("<< Back");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("End Time");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Start Time");

        jLabel2.setText("Complete the following:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmbSlotStart, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSlotEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(cmbSlotStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(cmbSlotEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addComponent(btnSave))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        GUILogin gLogin = new GUILogin();
        gLogin.setLocationRelativeTo(null);
        gLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        GUIDashboard gDashboard = new GUIDashboard(user);
        gDashboard.setLocationRelativeTo(null);
        gDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        
        String slot_start; 
        String slot_end; 
        
        if(cmbSlotStart.getSelectedItem() == null || cmbSlotEnd.getSelectedItem() == null){
           Alert.showAlert(this, "Warning", "Please select start time and end time of slot", "w");
           return;
        }
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        slot_start = cmbSlotStart.getSelectedItem().toString();
        slot_end = cmbSlotEnd.getSelectedItem().toString();
        
        String slot_start_dt = formatter.format(date) +" "+cmbSlotStart.getSelectedItem();
        String slot_end_dt = formatter.format(date) +" "+cmbSlotEnd.getSelectedItem();
        
        if(slot_start_dt.compareTo(slot_end_dt)>= 0){
            Alert.showAlert(this, "Warning", "Start time should not be greater or equal to end time", "w");
            return;
        }
        
        long checkDiff = Helper.getTimeDifference(slot_start,slot_end,"m");
        
        if(checkDiff <= 0){
            Alert.showAlert(this, "Warning", "Start time should not be greater or equal to end time", "w");
            return;
        }
        
        if(checkDiff > 30){
            Alert.showAlert(this, "Warning", "Time slot should not exceed 30 minutes", "w");
            return;
        }
        
        ArrayList<TimeSlot> curList = DatabaseManager.getTimeSlotByValue(this, "");
        boolean isExist = false;
        String msg = "";
        
        for(TimeSlot sl: curList){
            if(sl.getStart().equals(slot_start) || sl.getEnd().equals(slot_end)){
                if(slot == null){
                    msg = "Slot timing has conflict with another slot";
                    isExist = true;
                    break;
                }else{
                    if(!slot.getTimeSlotId().equals(sl.getTimeSlotId())){
                        msg = "Slot timing has conflict with another slot";
                        isExist = true;
                        break;
                    }
                }                    
            }   
        }
        
        if(isExist){
            Alert.showAlert(this, "Warning", msg, "w");
            return;
        }
        
        int result = Helper.showConfirmDialog(this, "Do you want to "+(slot != null ? "update":"create")+" slot details ?");
        
        if(result == JOptionPane.YES_OPTION){
            boolean add_update_result = false;
            
            TimeSlot addSlot;
        
            if(slot == null){
                addSlot= new TimeSlot(slot_start,slot_end);
                addSlot.setTimeSlotId("SLT"+Helper.formatDate("yyyyHHssMMmmdd", new Date()));
            }else{
                addSlot = slot;
                addSlot.setStart(slot_start);
                addSlot.setEnd(slot_end);
            }
            
            if(slot == null){
                ArrayList<TimeSlot> mySlot = new ArrayList<>();
                mySlot.add(addSlot);
                add_update_result = DatabaseManager.addTimeSlot(this,mySlot);
            }else{
                add_update_result = DatabaseManager.updateTimeSlot(this,addSlot);
            }   
            
            if(add_update_result){
                Alert.showAlert(this,"Info", "Time slot "+(slot != null ? "updated":"created")+" successfully", "i");
                GUIDashboard gDashboard = new GUIDashboard(user);
                gDashboard.setLocationRelativeTo(null);
                gDashboard.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(GUISlotManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUISlotManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUISlotManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUISlotManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUISlotManager(parentPage,user,slot).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbSlotEnd;
    private javax.swing.JComboBox<String> cmbSlotStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
