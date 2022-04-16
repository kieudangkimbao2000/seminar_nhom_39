/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.DiemDanhBUS;
import BUS.SinhVienBUS;
import DTO.DiemDanh;
import DTO.SinhVien;
import com.example.sdksamples.SampleProperties;
import com.impinj.octane.AntennaConfigGroup;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.ReaderMode;
import com.impinj.octane.ReportConfig;
import com.impinj.octane.ReportMode;
import com.impinj.octane.Settings;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kieud
 */
public class DiemDanhGUI extends javax.swing.JPanel {
    public SinhVienBUS svBUS = new SinhVienBUS();
    public DiemDanhBUS ddBUS = new DiemDanhBUS();
    public static JPanel panelSS = new JPanel();
    public static JPanel panelHD = new JPanel();
    public JScrollPane spSS;
    public JScrollPane spHD;
    
    /**
     * Creates new form DiemDanhGUI
     */
    public DiemDanhGUI() {
//        initComponents();
        setBackground(Color.WHITE);
        
        //tao layout cho panel chinh
        GridBagLayout gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //tao layout si so
//        JPanel panelSS = new JPanel();
        panelSS.setBorder(new CompoundBorder(new TitledBorder("Sỉ số"), new EmptyBorder(4, 4, 4, 4)));
        panelSS.setLayout(new BoxLayout(panelSS, BoxLayout.Y_AXIS));
        panelSS.setBackground(Color.white);
        
        Box boxesSS[] = new Box[2];
        boxesSS[0] = Box.createHorizontalBox();
        boxesSS[1] = Box.createHorizontalBox();
        
//        boxesSS[0].createGlue();
//        boxesSS[1].createGlue();
        
        panelSS.add(boxesSS[0]);
        panelSS.add(boxesSS[1]);
        
        JPanel panelSS1 = new JPanel();
        panelSS1.setBackground(Color.white);
        JPanel panelSS2 = new JPanel();
        panelSS2.setLayout(new BorderLayout());
        panelSS2.setBorder(new EmptyBorder(26, 0, 0, 0));
        panelSS2.setBackground(Color.white);
        
        String[] cols = {"Mã SV", "Họ tên"};
        String[][] rowsSS = new String[svBUS.getSVNotInDD().size()][2];
        int i = 0;
        for(SinhVien sv : svBUS.getSVNotInDD()){
            rowsSS[i][0] = sv.getMaSV();
            rowsSS[i][1] = sv.getHoTen();
            i++;
        }
        
        JTable tableSS = new JTable(rowsSS, cols);
        tableSS = new JTable(rowsSS, cols);
        tableSS.setBackground(Color.white);
        
        JScrollPane spSS = new JScrollPane(tableSS);
        spSS = new JScrollPane(tableSS);
        spSS.getViewport().setBackground(new Color(250, 250, 250));
        
        String ss = "Sỉ số: " + svBUS.getSVNotInDD().size();
        JLabel lbSS = new JLabel(ss);
        lbSS.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        panelSS1.add(spSS);
        panelSS2.add(lbSS, BorderLayout.WEST);
        
        boxesSS[0].add(panelSS1);
        boxesSS[1].add(panelSS2);
        
        //tao layout hien dien
//        JPanel panelHD = new JPanel();
        panelHD.setBorder(new CompoundBorder(new TitledBorder("Hiện diện"), new EmptyBorder(4, 4, 4, 4)));
        panelHD.setLayout(new BoxLayout(panelHD, BoxLayout.Y_AXIS));
        panelHD.setBackground(Color.white);
        
        Box boxesHD[] = new Box[3];
        boxesHD[0] = Box.createHorizontalBox();
        boxesHD[1] = Box.createHorizontalBox();
        boxesHD[2] = Box.createHorizontalBox();
        
        panelHD.add(boxesHD[0]);
        panelHD.add(boxesHD[1]);
        panelHD.add(boxesHD[2]);
        
        JPanel panelHD1 = new JPanel();
        panelHD1.setBackground(Color.white);
        JPanel panelHD2 = new JPanel();
        panelHD2.setBackground(Color.white);
        JPanel panelHD3 = new JPanel();
        panelHD3.setBackground(Color.white);
        
        String[] colsHD = {"Mã SV", "Họ tên", "Vi phạm"};        
        String[][] rowsHD = new String[ddBUS.GetAllDDonDate().size()][3];
        i = 0;
        for(DiemDanh dd : ddBUS.GetAllDDonDate()){
            SinhVien sv = svBUS.GetByID(dd.getMaSV());
            String color;
            if(dd.getGioRa() == null)
            {
                rowsHD[i][0] = setColor(dd.getMaSV(), "blue");
                rowsHD[i][1] = setColor(sv.getHoTen(), "blue");
                if(dd.isVaoTre())
                {
                    rowsHD[i][2] = setColor("Vào trễ", "blue"); 
                }
            }
            if(dd.getGioRa() != null)
            {
                rowsHD[i][0] = setColor(dd.getMaSV(), "green");
                rowsHD[i][1] = setColor(sv.getHoTen(), "green");
                if(dd.isVaoTre() && !dd.isVeSom())
                {
                    rowsHD[i][2] = setColor("Vào trễ", "green"); 
                }
                if(!dd.isVaoTre() && dd.isVeSom())
                {
                    rowsHD[i][2] = setColor("Về sớm", "green"); 
                }
                if(dd.isVaoTre() && dd.isVeSom())
                {
                    rowsHD[i][2] = setColor("Vào trễ + về sớm", "green"); 
                }
            }
            i++;
        }
        
        JTable tableHD = new JTable(rowsHD, colsHD){
            
        };
        tableHD.setBackground(Color.white);
        JScrollPane spHD = new JScrollPane(tableHD);
        spHD.getViewport().setBackground(new Color(250, 250, 250));

        ImageIcon iconBlue = new ImageIcon("./src/icons/icon_blue.png");
        ImageIcon iconGreen = new ImageIcon("./src/icons/icon_green.png");

        EmptyBorder eb = new EmptyBorder(0, 10, 0, 0);
        
        JLabel lbBlue = new JLabel("Đã điểm danh vào", iconBlue, JLabel.RIGHT);
        JLabel lbGreen = new JLabel("Đã điểm danh ra", iconGreen, JLabel.RIGHT);
        lbBlue.setBorder(eb);
        lbGreen.setBorder(eb);
        
        String hd = "Hiện diện: " + ddBUS.GetAllDDonDate().size();
        JLabel lbHD = new JLabel(hd);
        lbHD.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        panelHD1.add(spHD);
        panelHD2.add(lbBlue);
        panelHD2.add(lbGreen);
        panelHD3.setLayout(new BorderLayout());
        panelHD3.add(lbHD, BorderLayout.WEST);
        
        boxesHD[0].add(panelHD1);
        boxesHD[1].add(panelHD2);
        boxesHD[2].add(panelHD3);
        
        JPanel panelNut = new JPanel();
        panelNut.setBackground(Color.white);
        
        JPanel panelNutLeft = new JPanel();
        panelNutLeft.setBorder(new EmptyBorder(0, 0, 0, 50));
        panelNutLeft.setBackground(Color.white);
        JPanel panelNutRight = new JPanel();
        panelNutRight.setBorder(new EmptyBorder(0, 40, 0, 0));
        panelNutRight.setBackground(Color.white);
        
        
        JButton btnBack = new JButton("Trở về");
        JButton btnEnd = new JButton("Kết thúc");
        
        panelNutLeft.add(btnBack);
        panelNutRight.add(btnEnd);
                
        panelNut.add(panelNutLeft);
        panelNut.add(panelNutRight);
        
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Main.ddBUS.insertDD("SV2");
//  
//                Main.dd.panelSS.removeAll();
//                Main.dd.panelHD.removeAll();
//                
//                Main.dd.renewPanelSSandHD();
//                
//                Main.frame.pack();
//                Main.frame.setLocationRelativeTo(null);
//                Main.frame.update(Main.frame.getGraphics());

                try{
                    Main.reader.stop();
                    Main.reader.disconnect();
                } catch (OctaneSdkException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
            }
        });
        
        //add vao panel chinh
        add(panelSS, gbc);
        gbc.gridx += 1;
        add(panelHD, gbc);
        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.gridwidth = 2;
        add(panelNut, gbc);
        
//        spSS.remove(tableSS);
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

    public static void main(String[] args)
    {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }
    
    public void renewPanelSSandHD()
    {
        //setting layout si so
        panelSS.setBorder(new CompoundBorder(new TitledBorder("Sỉ số"), new EmptyBorder(4, 4, 4, 4)));
        panelSS.setLayout(new BoxLayout(panelSS, BoxLayout.Y_AXIS));
        panelSS.setBackground(Color.white);
        
        Box boxesSS[] = new Box[2];
        boxesSS[0] = Box.createHorizontalBox();
        boxesSS[1] = Box.createHorizontalBox();
        
//        boxesSS[0].createGlue();
//        boxesSS[1].createGlue();
        
        panelSS.add(boxesSS[0]);
        panelSS.add(boxesSS[1]);
        
        JPanel panelSS1 = new JPanel();
        panelSS1.setBackground(Color.white);
        JPanel panelSS2 = new JPanel();
        panelSS2.setLayout(new BorderLayout());
        panelSS2.setBorder(new EmptyBorder(26, 0, 0, 0));
        panelSS2.setBackground(Color.white);
        
        String[] cols = {"Mã SV", "Họ tên"};
        String[][] rowsSS = new String[svBUS.getSVNotInDD().size()][2];
        int i = 0;
        for(SinhVien sv : svBUS.getSVNotInDD()){
            rowsSS[i][0] = sv.getMaSV();
            rowsSS[i][1] = sv.getHoTen();
            i++;
        }
        
        JTable tableSS = new JTable(rowsSS, cols);
        tableSS = new JTable(rowsSS, cols);
        tableSS.setBackground(Color.white);
        
        JScrollPane spSS = new JScrollPane(tableSS);
        spSS = new JScrollPane(tableSS);
        spSS.getViewport().setBackground(new Color(250, 250, 250));
        
        
        String ss = "Sỉ số: " + svBUS.getSVNotInDD().size();
        JLabel lbSS = new JLabel(ss);
        lbSS.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        panelSS1.add(spSS);
        panelSS2.add(lbSS, BorderLayout.WEST);
        
        boxesSS[0].add(panelSS1);
        boxesSS[1].add(panelSS2);
        
        //setting layout hien dien
        panelHD.setBorder(new CompoundBorder(new TitledBorder("Hiện diện"), new EmptyBorder(4, 4, 4, 4)));
        panelHD.setLayout(new BoxLayout(panelHD, BoxLayout.Y_AXIS));
        panelHD.setBackground(Color.white);
        
        Box boxesHD[] = new Box[3];
        boxesHD[0] = Box.createHorizontalBox();
        boxesHD[1] = Box.createHorizontalBox();
        boxesHD[2] = Box.createHorizontalBox();
        
        panelHD.add(boxesHD[0]);
        panelHD.add(boxesHD[1]);
        panelHD.add(boxesHD[2]);
        
        JPanel panelHD1 = new JPanel();
        panelHD1.setBackground(Color.white);
        JPanel panelHD2 = new JPanel();
        panelHD2.setBackground(Color.white);
        JPanel panelHD3 = new JPanel();
        panelHD3.setBackground(Color.white);
        
        String[] colsHD = {"Mã SV", "Họ tên", "Vi phạm"};        
        String[][] rowsHD = new String[ddBUS.GetAllDDonDate().size()][3];
        i = 0;
        for(DiemDanh dd : ddBUS.GetAllDDonDate()){
            SinhVien sv = svBUS.GetByID(dd.getMaSV());
            String color;
            if(dd.getGioRa() == null)
            {
                rowsHD[i][0] = setColor(dd.getMaSV(), "blue");
                rowsHD[i][1] = setColor(sv.getHoTen(), "blue");
                if(dd.isVaoTre())
                {
                    rowsHD[i][2] = setColor("Vào trễ", "blue"); 
                }
            }
            if(dd.getGioRa() != null)
            {
                rowsHD[i][0] = setColor(dd.getMaSV(), "green");
                rowsHD[i][1] = setColor(sv.getHoTen(), "green");
                if(dd.isVaoTre() && !dd.isVeSom())
                {
                    rowsHD[i][2] = setColor("Vào trễ", "green"); 
                }
                if(!dd.isVaoTre() && dd.isVeSom())
                {
                    rowsHD[i][2] = setColor("Về sớm", "green"); 
                }
                if(dd.isVaoTre() && dd.isVeSom())
                {
                    rowsHD[i][2] = setColor("Vào trễ + về sớm", "green"); 
                }
            }
            i++;
        }
        
        JTable tableHD = new JTable(rowsHD, colsHD){
            
        };
        tableHD.setBackground(Color.white);
        JScrollPane spHD = new JScrollPane(tableHD);
        spHD.getViewport().setBackground(new Color(250, 250, 250));

        ImageIcon iconBlue = new ImageIcon("./src/icons/icon_blue.png");
        ImageIcon iconGreen = new ImageIcon("./src/icons/icon_green.png");

        EmptyBorder eb = new EmptyBorder(0, 10, 0, 0);
        
        JLabel lbBlue = new JLabel("Đã điểm danh vào", iconBlue, JLabel.RIGHT);
        JLabel lbGreen = new JLabel("Đã điểm danh ra", iconGreen, JLabel.RIGHT);
        lbBlue.setBorder(eb);
        lbGreen.setBorder(eb);
        
        String hd = "Hiện diện: " + ddBUS.GetAllDDonDate().size();
        JLabel lbHD = new JLabel(hd);
        lbHD.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        panelHD1.add(spHD);
        panelHD2.add(lbBlue);
        panelHD2.add(lbGreen);
        panelHD3.setLayout(new BorderLayout());
        panelHD3.add(lbHD, BorderLayout.WEST);
        
        boxesHD[0].add(panelHD1);
        boxesHD[1].add(panelHD2);
        boxesHD[2].add(panelHD3);
    }
    
    public String setColor(String string, String color)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<span style=\"color: " + color + ";\">");
        sb.append(string);
        sb.append("</span>");
        sb.append("</html>");
        
        return sb.toString();
    }
}
