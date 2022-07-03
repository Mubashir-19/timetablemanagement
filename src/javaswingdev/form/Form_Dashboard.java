package javaswingdev.form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Form_Dashboard extends javax.swing.JPanel {
    ResultSet rs;
    CRUDDB b = new CRUDDB();
    
    private int trueIndex;
    
    
    
    public Form_Dashboard() {
        
        initComponents();
        init();
    }

    public Form_Dashboard(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    private void generateTable() {
        //b.SortClasses();
        rs = b.getRandomData();
        
        int num = 1;
        table.fixTable(jScrollPane1);
        try{
            while (rs.next()) {
                
                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');
                if (rs.getString("type").equals("Lab") == true) {
                    
                    table.addRow(new Object[]{num,rs.getString("timing_from") + " - " + rs.getString("timing_to") , rs.getString("section") + " - " + rs.getString("semester"), rs.getString("courseName"), rs.getString("Lab"),rs.getString("department"), rs.getString("courseInstructor"), rs.getString("day")});
                
                }else {
                    table.addRow(new Object[]{num,rs.getString("timing_from") + " - " + rs.getString("timing_to"), rs.getString("section") + " - " + rs.getString("semester"), rs.getString("courseName"), rs.getString("room"),rs.getString("department"), rs.getString("courseInstructor"), rs.getString("day")});

                }
                
                num++;
            }
        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void init() {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(rowSorter);
        
        NameSearchvar.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = NameSearchvar.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = NameSearchvar.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });
        
        generateTable();
        

        //  init card data

    }
    
    //INSERT INTO `timings` (`id`, `from`, `to`, `shift`, `classLab`) VALUES (NULL, '9:00', '10:30', 'morning', 'class');
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        NameSearchvar = new javax.swing.JTextField();
        SearchNameLabel = new javax.swing.JLabel();
        searchEducationLabel = new javax.swing.JLabel();
        searchRankLabel = new javax.swing.JLabel();
        mysectionVar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "From - To", "Semester", "Course Name", "Department", "Type", "Course Instructor", "Day"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        NameSearchvar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        NameSearchvar.setText("Search by Name or ID");
        NameSearchvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameSearchvarActionPerformed(evt);
            }
        });

        mysectionVar.setFont(new java.awt.Font("Impact", 0, 48)); // NOI18N
        mysectionVar.setForeground(new java.awt.Color(102, 102, 102));
        mysectionVar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mysectionVar.setText("DashBoard");

        jButton1.setText("rearrange");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("reArrange");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SearchNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchEducationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchRankLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(529, 529, 529))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(NameSearchvar, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(61, 61, 61)
                .addComponent(mysectionVar, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(mysectionVar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(51, 51, 51)))
                .addComponent(SearchNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchEducationLabel)
                .addGap(11, 11, 11)
                .addComponent(NameSearchvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchRankLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void NameSearchvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameSearchvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameSearchvarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        
        trueIndex = table.getRowSorter().convertRowIndexToModel(table.getSelectedRow());
        //String tb1_id = table.getModel().getValueAt(table.getSelectionModel(), 0).toString();
        String tb1_teachername = table.getModel().getValueAt(trueIndex, 1).toString();
        String tb1_subject = table.getModel().getValueAt(trueIndex, 2).toString();
        String tb1_education = table.getModel().getValueAt(trueIndex, 3).toString();
        String tb1_rank = table.getModel().getValueAt(trueIndex, 4).toString();

        update_teachername.setText(tb1_teachername);
        update_rank.setText(tb1_subject);
        update_subject.setText(tb1_education);
        update_education.setText(tb1_rank);
        
        jButton2.setEnabled(true);
        jButton4.setEnabled(true);
        
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        b.reArrange();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        b.arrangeClasses();
        
    }//GEN-LAST:event_jButton2ActionPerformed
    

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameSearchvar;
    private javax.swing.JLabel SearchNameLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mysectionVar;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javax.swing.JLabel searchEducationLabel;
    private javax.swing.JLabel searchRankLabel;
    private javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
