/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kieud
 */
public class NhapGioGUI extends javax.swing.JPanel {

    /**
     * Creates new form NhapGioGUI
     */
    public NhapGioGUI() {
//        initComponents();
        setBackground(Color.WHITE);

        //tao layout chinh
        BorderLayout bdLayout = new BorderLayout();
        setLayout(bdLayout);
        
        //tao layout cho north
        JPanel panelN = new JPanel();
        panelN.setBackground(Color.BLACK);
        
        GridBagLayout gbLayoutN = new GridBagLayout();
        panelN.setLayout(gbLayoutN);
        
        JLabel lbTieuDe1 = new JLabel("RFID");
        lbTieuDe1.setHorizontalAlignment(JLabel.CENTER);
        lbTieuDe1.setFont(new Font("Verdana", Font.PLAIN, 30));
        lbTieuDe1.setForeground(new Color(255,222,76));
        
        JLabel lbTieuDe2 = new JLabel("Điểm danh sinh viên");
        lbTieuDe2.setHorizontalAlignment(JLabel.CENTER);
        lbTieuDe2.setFont(new Font("Verdana", Font.PLAIN, 20));
        lbTieuDe2.setForeground(new Color(255,222,76));
        
        GridBagConstraints gbcN = new GridBagConstraints();
        gbcN.fill = GridBagConstraints.HORIZONTAL;
        gbcN.gridx = 0;
        gbcN.gridy = 0;
        
        panelN.add(lbTieuDe1, gbcN);
        gbcN.gridy += 1;
        panelN.add(lbTieuDe2, gbcN);
        
        //Tao layout cho center
        JPanel panelC = new JPanel();
        panelC.setBackground(Color.WHITE);
        
        GridBagLayout gbLayoutC = new GridBagLayout();
        panelC.setLayout(gbLayoutC);
        
        JLabel lbNgay = new JLabel("Ngày");
        JLabel lbGioVao = new JLabel("Giờ vào");
        JLabel lbGioRa = new JLabel("Giờ ra");
        
        JLabel lbWarGioVao = new JLabel("*Hãy chọn giờ vào");
        lbWarGioVao.setForeground(Color.red);
        Font font = new Font(lbWarGioVao.getFont().getName(), Font.ITALIC, lbWarGioVao.getFont().getSize());
        lbWarGioVao.setFont(font);
        lbWarGioVao.setVisible(false);
        JLabel lbWarGioRa = new JLabel("*Hãy chọn giờ ra");
        lbWarGioRa.setForeground(Color.red);
        lbWarGioRa.setFont(font);
        lbWarGioRa.setVisible(false);
        
        DatePicker dpNgay = new DatePicker();
        dpNgay.setDate(LocalDate.now());
        dpNgay.setEnabled(false);
        
        TimePicker tpGioVao = new TimePicker();
        TimePicker tpGioRa = new TimePicker();
        
        GridBagConstraints gbcC = new GridBagConstraints();
        gbcC.fill = GridBagConstraints.HORIZONTAL;
        gbcC.gridx = 0;
        gbcC.gridy = 0;
        
        int top = 10, left = 3, bottom = 10, right = 3;
        Insets i = new Insets(top, left, bottom, right);
        gbcC.insets = i;
        
        panelC.add(lbNgay, gbcC);
        gbcC.gridx += 1;
        panelC.add(dpNgay, gbcC);
        gbcC.gridx = 0;
        gbcC.gridy += 1;
        panelC.add(lbGioVao, gbcC);
        gbcC.gridx += 1;
        panelC.add(tpGioVao, gbcC);
        gbcC.gridx += 1;
        panelC.add(lbWarGioVao, gbcC);
        gbcC.gridx = 0;
        gbcC.gridy += 1;
        panelC.add(lbGioRa, gbcC);
        gbcC.gridx += 1;
        panelC.add(tpGioRa, gbcC);
        gbcC.gridx += 1;
        panelC.add(lbWarGioRa, gbcC);
        
        //tao layout cho south
        JPanel panelS = new  JPanel();
        panelS.setBackground(Color.WHITE);
        
        FlowLayout fLayoutS = new FlowLayout();
        panelS.setLayout(fLayoutS);
        
        JButton bHuy = new JButton("Hủy");
        JButton bTiepTuc = new JButton("Tiếp tục");
        
        panelS.add(bHuy);
        panelS.add(Box.createRigidArea(new Dimension(20, 0)));
        panelS.add(bTiepTuc);
        
        //xu ly event
        bHuy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tpGioVao.setText("");
                tpGioRa.setText("");
            }
            
        });
        bTiepTuc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tpGioVao.getText().equals(null) || tpGioVao.getText().equals("")){
                    lbWarGioVao.setVisible(true);
                }
                if(tpGioRa.getText().equals(null) || tpGioRa.getText().equals(""))
                {
                    lbWarGioRa.setVisible(true);
                }
                if (!(tpGioVao.getText().equals(null) || tpGioVao.getText().equals("")) &&
                        !(tpGioRa.getText().equals(null) || tpGioRa.getText().equals(""))){
                    if(tpGioVao.getTime().compareTo(tpGioRa.getTime()) < 0){
                        Main.frame.remove(Main.ng);
                        Main.frame.add(Main.dd);
                        Main.frame.update(Main.frame.getGraphics());
                    } else {
                        String thongBao = "Giờ vào phải trước giờ ra.";
                        ThongBaoGUI tbGUI = new ThongBaoGUI(thongBao);
                    }
                }
            }
        
        });
        
        //them cac thanh phan vao panel chinh
        add(panelN, BorderLayout.NORTH);
        add(panelS, BorderLayout.SOUTH);
        add(panelC, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
