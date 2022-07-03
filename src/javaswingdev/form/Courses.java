package javaswingdev.form;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Courses extends javax.swing.JPanel {

    ResultSet rs;
    ResultSet mainrs;
    CRUDDB b = new CRUDDB();

    private int trueIndex;

    public Courses() {

        initComponents();
        init();
    }

    public Courses(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getTeacherData() {
        rs = b.getUsers("courses");
        table.fixTable(jScrollPane1);
        try {
            while (rs.next()) {
                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');
                table.addRow(new Object[]{rs.getString("courseId"), rs.getString("courseName"), rs.getString("courseInstructor"), rs.getString("department"), rs.getString("semester"), rs.getString("section"), rs.getString("courseType")});

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void init() {
        TableRowSorter< TableModel> rowSorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(rowSorter);

        NameSearchvar.getDocument().addDocumentListener(new DocumentListener() {

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

        getTeacherData();
        mainrs = b.getUsers("teachers");
        try {
            while (mainrs.next()) {
                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');

                courseInstructor.addItem(mainrs.getString("teachername"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        courseInstructor.addItemListener(new ItemChangeListener());
        //  init card data

        rs = b.getDistinctvalues("semesters");
        String tempDept;
        try {
            while (rs.next()) {
                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external'); 
                tempDept = rs.getString("department");

                coursedept.addItem(tempDept);

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        mainrs = b.getUsers("allcourses");
        try {
            while (mainrs.next()) {
                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');
                courseSubject.addItem(mainrs.getString("courseName"));


            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        courseSubject.addItemListener(new ItemChangeListener());
        coursedept.addItemListener(new ItemChangeListener());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mysectionVar = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        SubmitButton = new javax.swing.JButton();
        NameSearchvar = new javax.swing.JTextField();
        update_teachername = new javax.swing.JTextField();
        update_subject = new javax.swing.JTextField();
        update_education = new javax.swing.JTextField();
        update_rank = new javax.swing.JTextField();
        SearchNameLabel = new javax.swing.JLabel();
        searchEducationLabel = new javax.swing.JLabel();
        searchRankLabel = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        mysectionVar1 = new javax.swing.JLabel();
        courseInstructor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        courseSubject = new javax.swing.JComboBox<>();
        coursedept = new javax.swing.JComboBox<>();
        coursesem = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        courseId = new javax.swing.JLabel();
        coursesec = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        courseName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        courseType = new javax.swing.JComboBox<>();
        update_teachername1 = new javax.swing.JTextField();
        update_subject1 = new javax.swing.JTextField();
        update_education1 = new javax.swing.JTextField();
        update_rank1 = new javax.swing.JTextField();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        getSection = new javax.swing.JButton();

        mysectionVar.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        mysectionVar.setForeground(new java.awt.Color(102, 102, 102));
        mysectionVar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mysectionVar.setText("Sections");

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1161, 692));
        setOpaque(false);

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 153));
        jButton2.setText("Update");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.setContentAreaFilled(false);
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        SubmitButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });

        NameSearchvar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        NameSearchvar.setText("Search by Name or ID");
        NameSearchvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NameSearchvarMouseClicked(evt);
            }
        });
        NameSearchvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameSearchvarActionPerformed(evt);
            }
        });

        update_teachername.setText("teacher name");
        update_teachername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_teachernameActionPerformed(evt);
            }
        });

        update_subject.setText("subject");

        update_education.setText("education");

        update_rank.setText("rank");
        update_rank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_rankActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 51));
        jButton4.setText("Delete");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton4.setContentAreaFilled(false);
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        mysectionVar1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        mysectionVar1.setForeground(new java.awt.Color(102, 102, 102));
        mysectionVar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mysectionVar1.setText("Course Allocation");

        courseInstructor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        courseInstructor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                courseInstructorItemStateChanged(evt);
            }
        });
        courseInstructor.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                courseInstructorHierarchyChanged(evt);
            }
        });
        courseInstructor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseInstructorMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                courseInstructorMouseExited(evt);
            }
        });
        courseInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseInstructorActionPerformed(evt);
            }
        });
        courseInstructor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                courseInstructorPropertyChange(evt);
            }
        });

        jLabel1.setText("Course Id:  ");

        jLabel2.setText("Preferred Subject: ");

        jLabel3.setText("Course Instructor");

        jLabel4.setText("Course Name");

        courseSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));

        coursedept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));

        jLabel5.setText("Department");

        jLabel6.setText("Semester");

        courseId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setText("Section");

        courseName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        courseName.setText("jLabel7");

        jLabel7.setText("Course Type: ");

        courseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Visiting" }));

        update_teachername1.setText("teacher name");
        update_teachername1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_teachername1ActionPerformed(evt);
            }
        });

        update_subject1.setText("subject");

        update_education1.setText("education");

        update_rank1.setText("rank");
        update_rank1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_rank1ActionPerformed(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Id", "Course Name", "Course Instructor", "Department", "Semester", "Section", "Course Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true
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
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getSection.setBackground(new java.awt.Color(255, 51, 51));
        getSection.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getSection.setForeground(new java.awt.Color(255, 255, 255));
        getSection.setText("Get Sections");
        getSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getSectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchRankLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchEducationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SearchNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(446, 446, 446))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(coursesec, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(courseType, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(coursesem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(getSection, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(courseName, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(courseSubject, javax.swing.GroupLayout.Alignment.LEADING, 0, 167, Short.MAX_VALUE)
                                            .addComponent(courseInstructor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(coursedept, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(courseId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(NameSearchvar, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(update_rank1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(update_rank, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(update_education, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(update_teachername, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(update_subject, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(update_education1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(update_subject1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(update_teachername1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(mysectionVar1)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1053, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(courseId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(courseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(coursedept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(coursesem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(getSection))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(courseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(coursesec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(courseInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(courseName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addGap(44, 44, 44))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mysectionVar1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(SearchNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchEducationLabel)
                                .addGap(40, 40, 40)
                                .addComponent(searchRankLabel)
                                .addGap(2, 2, 2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(update_teachername1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_subject1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_education1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_rank1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(update_teachername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(update_rank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NameSearchvar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(7, 7, 7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NameSearchvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameSearchvarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_NameSearchvarActionPerformed

    private void update_teachernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_teachernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_teachernameActionPerformed

    private void update_rankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_rankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_rankActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String teachername = update_teachername.getText();
        String education = update_education.getText();
        String rank = update_rank.getText();
        String subject = update_subject.getText();

        b.UpdateTeacher(trueIndex + 1, teachername, subject, education, rank);

        ((DefaultTableModel) table.getModel()).setRowCount(0);
        getTeacherData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed

        int visiting = 0;
        int regular = 0;
        String teacher = courseInstructor.getSelectedItem().toString();
        for (int i = 0; i < table.getRowCount(); i++) {
            System.out.println(table.getValueAt(i, 2));
            if ((table.getValueAt(i, 2).toString()).equals(teacher)) {
                if ((table.getValueAt(i, 6).toString()).equals("Regular")) {
                    regular++;
                }
                if ((table.getValueAt(i, 6).toString()).equals("Visiting")) {
                    visiting++;
                }
            }

        }
        
        if ((courseType.getSelectedItem().toString().equals("Visiting")) && (visiting >= 3)) {
            
            JOptionPane.showMessageDialog(new JFrame(), "Visiting Course Load for " + teacher + " is full","Dialog",  JOptionPane.ERROR_MESSAGE);
        }else if((courseType.getSelectedItem().toString().equals("Regular")) && (regular >= 4)){
            JOptionPane.showMessageDialog(new JFrame(), "Regular Course Load for " + teacher + " is full","Dialog",  JOptionPane.ERROR_MESSAGE);    
        }else {
            String courseId1 = courseId.getText();
            String courseName1 = courseSubject.getSelectedItem().toString();
            String courseInstructor1 = courseInstructor.getSelectedItem().toString();
            String dept1 = coursedept.getSelectedItem().toString();
            String sem1 = coursesem.getSelectedItem().toString();
            String sec1 = coursesec.getSelectedItem().toString();

            int res = b.registerCourses(courseId1, courseName1, courseInstructor1, dept1, sem1, sec1, courseType.getSelectedItem().toString());
            
            if (res == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Data Already Exists","Dialog",  JOptionPane.ERROR_MESSAGE);

            }
            
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            getTeacherData();
        }
        
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.out.println(trueIndex+1);
        b.deleteData("courses", trueIndex+1);
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        getTeacherData();


    }//GEN-LAST:event_jButton4ActionPerformed

    private void courseInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseInstructorActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_courseInstructorActionPerformed

    private void courseInstructorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseInstructorMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_courseInstructorMouseClicked

    private void courseInstructorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseInstructorMouseExited
        // TODO add your handling code here:


    }//GEN-LAST:event_courseInstructorMouseExited
    
    class ItemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object item = event.getItem();
                
                if (courseInstructor.hasFocus()) {
                    if (item == "select") {
                        courseName.setText("");
                    }else {
                        mainrs = b.getUsers("teachers");
                        try {
                            while (mainrs.next()) {
                                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');
                                if (mainrs.getString("teachername").equals(item)) {
                                    //System.out.println(mainrs.getString("subject"));
                                    courseName.setText(mainrs.getString("subject"));
                                }

                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                } else if (courseSubject.hasFocus()) {
                    if (item == "select") {
                        courseId.setText("");
                    }else {
                        mainrs = b.getUsers("allcourses");
                        try {
                            while (mainrs.next()) {
                                //INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');
                                if (mainrs.getString("courseName").equals(item)) {
                                    courseId.setText(mainrs.getString("courseId"));
                                }

                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }else if( coursedept.hasFocus()){
                    System.out.println(item.toString());
                    coursesem.removeAllItems();
                    coursesec.removeAllItems();
                    mainrs = b.getMatchingSemesters(item);
                    
                    try {
                        while (mainrs.next()) {
                            coursesem.addItem(mainrs.getString("semester"));
                            

                        }
                    }catch (SQLException ex) {
                        System.out.println(ex.toString());
                    }
                    

                }
                

            }
        }

    }

    private void courseInstructorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_courseInstructorItemStateChanged
        // TODO add your handling code here:
        //mainrs = b.getUsers("teachers");

    }//GEN-LAST:event_courseInstructorItemStateChanged

//    private void courseInstructorChange(ListDataEvent evt) {
//        System.out.println(evt);
//    }
    private void courseInstructorHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_courseInstructorHierarchyChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_courseInstructorHierarchyChanged

    private void courseInstructorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_courseInstructorPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_courseInstructorPropertyChange

    private void NameSearchvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NameSearchvarMouseClicked
        // TODO add your handling code here:
        NameSearchvar.setText("");
    }//GEN-LAST:event_NameSearchvarMouseClicked

    private void update_teachername1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_teachername1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_teachername1ActionPerformed

    private void update_rank1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_rank1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_rank1ActionPerformed

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

    private void getSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getSectionActionPerformed
        // TODO add your handling code here:
        mainrs = b.getMatchingSections(coursedept.getSelectedItem().toString(), coursesem.getSelectedItem().toString());
        try {
            while (mainrs.next()) {
                            
                    coursesec.addItem(mainrs.getString("section"));

                }
            }catch (SQLException ex) {
                System.out.println(ex.toString());
            }
    }//GEN-LAST:event_getSectionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameSearchvar;
    private javax.swing.JLabel SearchNameLabel;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JLabel courseId;
    private javax.swing.JComboBox<String> courseInstructor;
    private javax.swing.JLabel courseName;
    private javax.swing.JComboBox<String> courseSubject;
    private javax.swing.JComboBox<String> courseType;
    private javax.swing.JComboBox<String> coursedept;
    private javax.swing.JComboBox<String> coursesec;
    private javax.swing.JComboBox<String> coursesem;
    private javax.swing.JButton getSection;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mysectionVar;
    private javax.swing.JLabel mysectionVar1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javax.swing.JLabel searchEducationLabel;
    private javax.swing.JLabel searchRankLabel;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField update_education;
    private javax.swing.JTextField update_education1;
    private javax.swing.JTextField update_rank;
    private javax.swing.JTextField update_rank1;
    private javax.swing.JTextField update_subject;
    private javax.swing.JTextField update_subject1;
    private javax.swing.JTextField update_teachername;
    private javax.swing.JTextField update_teachername1;
    // End of variables declaration//GEN-END:variables
}
