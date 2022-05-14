/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.DiemDanhBUS;
import BUS.SinhVienBUS;
import DTO.DiemDanh;
import DTO.SinhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kieud
 */
public class ChiTietBaoCaoGUI extends javax.swing.JFrame {

    public SinhVienBUS svBUS = new SinhVienBUS();
    public DiemDanhBUS ddBUS = new DiemDanhBUS();

    /**
     * Creates new form ChiTietBaoCaoGUI
     */
    public ChiTietBaoCaoGUI(String MaSV, LocalDate begin, LocalDate end) {
        ImageIcon logo = new ImageIcon("./src/icons/logo.jpg");
        setIconImage(logo.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Báo cáo");

        //panel chinh
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        
        panel.setLayout(new BorderLayout());

        //layout north
        JPanel panelN = new JPanel();
        panelN.setBackground(Color.black);
        panelN.setLayout(new BoxLayout(panelN, BoxLayout.Y_AXIS));
        panelN.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        JLabel lbMaSV = new JLabel("Mã sinh viên: " + MaSV);
        lbMaSV.setFont(new Font("Verdana", Font.BOLD, 20));
        lbMaSV.setForeground(new Color(255, 222, 76));
        lbMaSV.setBorder(new EmptyBorder(0, 10, 0, 0));
        SinhVien sv = svBUS.GetByID(MaSV);
        JLabel lbHoTen = new JLabel("Họ tên: " + sv.getHoTen());
        lbHoTen.setFont(new Font("Verdana", Font.BOLD, 20));
        lbHoTen.setForeground(new Color(255, 222, 76));
        lbHoTen.setBorder(new EmptyBorder(0, 10, 0, 0));
        
        panelN.add(lbMaSV);
        panelN.add(lbHoTen);

        //layout center
        JPanel panelC = new JPanel();
        panelC.setBackground(Color.white);
        String[] cols = {"Ngày", "Giờ vào", "Giờ ra", "Vi phạm"};
//        String[][] rows = new String[Main.ddBUS.ChiTietBaoCaoDD(MaSV, begin, end).size()][4];
//        int i = 0;
//        for(DiemDanh dd : Main.ddBUS.ChiTietBaoCaoDD(MaSV, begin, end))
//        {
//            if(dd.isVang())
//            {
//                rows[i][0] = dd.getGioVao().toLocalDate().toString();
//                rows[i][3] = "Vắng";
//            }
//            else
//            {
//                rows[i][0] = dd.getGioVao().toLocalDate().toString();
//                rows[i][1] = dd.getGioVao().toLocalTime().toString();
//                rows[i][2] = dd.getGioVao().toLocalTime().toString();
//                if(dd.isVaoTre() && !dd.isVeSom())
//                {
//                    rows[i][3] = "Vào trễ";
//                }
//                if(!dd.isVaoTre() && dd.isVeSom())
//                {
//                    rows[i][3] = "Về sớm";
//                }
//                if(dd.isVaoTre() && dd.isVeSom())
//                {
//                    rows[i][3] = "Vào trễ + về sớm";
//                }
//            }
//            i++;
//        }
        
        DefaultTableModel model = new DefaultTableModel();
        
        JTable tbl = new JTable(model);
        
        for (int i = 0; i < cols.length; i++) {
            model.addColumn(cols[i]);
        }
        
        for (DiemDanh dd : Main.ddBUS.ChiTietBaoCaoDD(MaSV, begin, end)) {
            String viPham = "";
            if (dd.isVang()) {
                viPham = "Vắng";
            }
            
            if (dd.isVaoTre() && !dd.isVeSom()) {
                viPham = "Vào trễ";
            }
            if (!dd.isVaoTre() && dd.isVeSom()) {
                viPham = "Về sớm";
            }
            if (dd.isVaoTre() && dd.isVeSom()) {
                viPham = "Vào trễ + về sớm";
            }
            if(dd.getGioRa() != null)
            {
                String[] rows = {dd.getGioVao().toLocalDate().toString(), dd.getGioVao().toLocalTime().toString(),
                   dd.getGioRa().toLocalTime().toString(), viPham};
                model.addRow(rows);
            }
            else
            {
                String[] rows = {dd.getGioVao().toLocalDate().toString(), dd.getGioVao().toLocalTime().toString(),
                    null, viPham};
                model.addRow(rows);
            }
        }
        
        JScrollPane sp = new JScrollPane(tbl);
        sp.getViewport().setBackground(new Color(250, 250, 250));
        
        panelC.add(sp);
        
        panel.add(panelN, BorderLayout.NORTH);
        panel.add(panelC, BorderLayout.CENTER);

        //add event close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                dispose();
            }
        });

        //add vao frame
        add(panel);
        
        pack();
        setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(ChiTietBaoCaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietBaoCaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietBaoCaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietBaoCaoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
