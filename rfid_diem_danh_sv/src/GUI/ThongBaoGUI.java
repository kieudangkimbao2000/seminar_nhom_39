/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author kieud
 */
public class ThongBaoGUI extends javax.swing.JFrame {
    public static int MESSAGE=0;
    public static int WARNING=1;
    public static int ERROR=2;
    /**
     * Creates new form ThongBaoGUI
     */
    public ThongBaoGUI(String thongBao, int type) {
//        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.setBackground(Color.white);
        
        //layout row 1
        JLabel lbThongBao = new JLabel(thongBao);
        if(type == 1)
        {
            ImageIcon icon = new ImageIcon("./src/icons/warning_icon.jpg");
            lbThongBao = new JLabel(thongBao, icon, JLabel.RIGHT);
        }
        Font font = new Font("Arial", Font.PLAIN, 16);
        lbThongBao.setFont(font);
        lbThongBao.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        //layout row 2
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.white);
        JButton btn = new JButton("OK");
        panel2.add(btn);
        
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panel.add(lbThongBao);
        panel.add(panel2);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                dispose();
            }
        });
        
        add(panel);
        pack();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    public static void main(String[] args)
    {
        
    }
}
