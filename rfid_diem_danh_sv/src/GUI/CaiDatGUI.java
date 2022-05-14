/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.BoTriBUS;
import BUS.LopHocBUS;
import BUS.PhongHocBUS;
import DTO.LopHoc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author kieud
 */
public class CaiDatGUI extends javax.swing.JFrame {

    public String MaPH = null;
    public int tgVT = 0;
    public int tgVS = 0;

    /**
     * Creates new form CaiDatGUI
     */
    public CaiDatGUI() {
//        initComponents();
        ImageIcon logo = new ImageIcon("./src/icons/logo.jpg");
        setIconImage(logo.getImage());

        setTitle("Cài đặt");

        //đọc cau_hinh.conf
        Properties prop = readFileCauHinh();
        if (!prop.getProperty("MaPH").isEmpty()) {
            MaPH = prop.getProperty("MaPH").toString();
        }
        if (!prop.getProperty("VT").isEmpty()) {
            tgVT = Integer.valueOf(prop.getProperty("VT"));
        }
        if (!prop.getProperty("VT").isEmpty()) {
            tgVS = Integer.valueOf(prop.getProperty("VS"));
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //panel north
        JPanel panelN = new JPanel();

        //panel center
        JPanel panelC = new JPanel();
        panelC.setBackground(Color.white);
        panelC.setLayout(new GridBagLayout());

        GridBagConstraints gbcC = new GridBagConstraints();
        gbcC.fill = GridBagConstraints.HORIZONTAL;
        gbcC.gridx = 0;
        gbcC.gridy = 0;

        JLabel lbPH = new JLabel("Phòng học:");
        JLabel lbVT = new JLabel("Thời gian vào trễ được phép:");
        JLabel lbVS = new JLabel("Thời gian về sớm được phép: ");

        PhongHocBUS phBUS = new PhongHocBUS();
        String[] arrayPH = phBUS.GetAll().stream()
                .map(ph -> ph.getMaPH())
                .toArray(String[]::new);

        JComboBox cbPH = new JComboBox(arrayPH);
        cbPH.setBackground(Color.white);
        cbPH.setSelectedItem(MaPH);

        JTextField tfVT = new JTextField(String.valueOf(tgVT));
        JTextField tfVS = new JTextField(String.valueOf(tgVS));

        int top = 10, left = 3, bottom = 10, right = 3;
        Insets i = new Insets(top, left, bottom, right);
        gbcC.insets = i;

        panelC.add(lbPH, gbcC);
        gbcC.gridx += 1;
        panelC.add(cbPH, gbcC);
        gbcC.gridx = 0;
        gbcC.gridy += 1;
        panelC.add(lbVT, gbcC);
        gbcC.gridx += 1;
        panelC.add(tfVT, gbcC);
        gbcC.gridx = 0;
        gbcC.gridy += 1;
        panelC.add(lbVS, gbcC);
        gbcC.gridx += 1;
        panelC.add(tfVS, gbcC);

        //panel south
        JPanel panelS = new JPanel();
        panelS.setBackground(Color.white);

        JButton btn = new JButton("OK");
        btn.setBackground(Color.white);

        //add event button
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPH = "MaPH = " + cbPH.getSelectedItem().toString();
                String tgVT = "VT = " + tfVT.getText().toString();
                String tgVS = "VS = " + tfVS.getText().toString();
                try {
                    FileWriter fw = new FileWriter("./cau_hinh.conf", false);
                    fw.write(maPH);
                    fw.write("\r\n");
                    fw.write(tgVT);
                    fw.write("\r\n");
                    fw.write(tgVS);
                    fw.close();
                    BoTriBUS btBUS = new BoTriBUS();
                    String[] arrayLH = btBUS.getListByPHandNgay(cbPH.getSelectedItem().toString())
                            .stream()
                            .map(bt -> bt.getMaLH())
                            .toArray(String[]::new);
                    Main.ng.tgVT = Integer.valueOf(tfVT.getText().toString());
                    Main.ng.tgVS = Integer.valueOf(tfVS.getText().toString());
                    Main.ng.MaPH = cbPH.getSelectedItem().toString();
                    Main.ng.cbLH.removeAllItems();
                    if (arrayLH != null) {
                        for (String str : arrayLH) {
                            Main.ng.cbLH.addItem(str);
                        }
                    }
                    if (Main.ng.cbLH.getItemCount() > 0) {
                        LopHoc lh = new LopHocBUS().getLHByRoomandID(cbPH.getSelectedItem().toString(), Main.ng.cbLH.getSelectedItem().toString());
                        Main.ng.tfGioVao.setText(lh.getGioVao().toString());
                        Main.ng.tfGioRa.setText(lh.getGioRa().toString());
                    } else {
                        Main.ng.tfGioVao.setText("");
                        Main.ng.tfGioRa.setText("");
                    }
                    dispose();
                } catch (IOException ex) {
                    Logger.getLogger(CaiDatGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        panelS.add(btn);

        panel.add(panelC, BorderLayout.CENTER);
        panel.add(panelS, BorderLayout.SOUTH);
        add(panel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                dispose();
            }
        });
    }

    public Properties readFileCauHinh() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./cau_hinh.conf");
            prop.load(fis);
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prop;
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
            java.util.logging.Logger.getLogger(CaiDatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaiDatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaiDatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaiDatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaiDatGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
