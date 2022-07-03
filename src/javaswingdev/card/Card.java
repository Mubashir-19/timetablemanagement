package javaswingdev.card;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;


public class Card extends javax.swing.JPanel {



    public Card() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Area area = new Area(new RoundRectangle2D.Double(0, 20, getWidth(), getHeight() - 20, 10, 10));
        g2.setColor(getBackground());
        g2.fill(area);
        area.subtract(new Area(new Rectangle2D.Double(0, 20, getWidth(), getHeight() - 23)));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
    }



    public void setDescription(String description) {
        lbDescription.setText(description);
    }

    public String getDescription() {
        return lbDescription.getText();
    }

    public void setValues(String values) {
        lbValues.setText(values);
    }

    public String getValues() {
        return lbValues.getText();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbValues = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();

        lbValues.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbValues.setForeground(new java.awt.Color(128, 128, 128));
        lbValues.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbValues.setText("Search");

        lbDescription.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbDescription.setForeground(new java.awt.Color(153, 153, 153));
        lbDescription.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDescription.setText("Search from the records");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(lbValues, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbValues)
                .addGap(3, 3, 3)
                .addComponent(lbDescription)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbValues;
    // End of variables declaration//GEN-END:variables
}
