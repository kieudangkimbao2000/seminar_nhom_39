/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.DiemDanhBUS;
import BUS.HocBUS;
import BUS.SinhVienBUS;
import DTO.DiemDanh;
import DTO.Hoc;
import DTO.SinhVien;
import com.impinj.octane.OctaneSdkException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author kieud
 */
public class DiemDanhGUI extends javax.swing.JPanel {
    public String MaLH;
    public HocBUS hBUS = new HocBUS();
    public SinhVienBUS svBUS = new SinhVienBUS();
    public DiemDanhBUS ddBUS = new DiemDanhBUS();
    public List<Hoc> lsH;
    public List<DiemDanh> lsDD;
    public JPanel panelN = new JPanel();
    public JTable tblSS;
    public JTable tblHD;
    public DefaultTableModel modelSS = new DefaultTableModel();
    public DefaultTableModel modelHD = new DefaultTableModel();
    public JLabel lbSS;
    public JLabel lbHD;
    
    /**
     * Creates new form DiemDanhGUI
     */
    public DiemDanhGUI() {
//        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        //cai dat layout north
        panelN.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        //cai dat layout center
        JPanel panelC = new JPanel();
        panelC.setBackground(Color.white);
        
            //tao layout si so
        JPanel panelSS = new JPanel();
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
       
        tblSS = new JTable(modelSS);
        modelSS.addColumn("Mã SV");
        modelSS.addColumn("Họ tên");
        
        tblSS.setBackground(Color.white);
        
        JScrollPane spSS = new JScrollPane(tblSS);
        spSS = new JScrollPane(tblSS);
        spSS.getViewport().setBackground(new Color(250, 250, 250));
        
        String ss = "Sỉ số: ";
        lbSS = new JLabel(ss);
        lbSS.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        panelSS1.add(spSS);
        panelSS2.add(lbSS, BorderLayout.WEST);
        
        boxesSS[0].add(panelSS1);
        boxesSS[1].add(panelSS2);
        
            //tao layout hien dien
        JPanel panelHD = new JPanel();
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
        
        tblHD = new JTable(modelHD);
        modelHD.addColumn("Mã SV");
        modelHD.addColumn("Họ tên");
        modelHD.addColumn("Giờ vào");
        modelHD.addColumn("Giờ ra");
        modelHD.addColumn("Vi phạm");
        TableColumnModel columnModel = tblHD.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(100);
        
        tblHD.setBackground(Color.white);
        JScrollPane spHD = new JScrollPane(tblHD);
        spHD.getViewport().setBackground(new Color(250, 250, 250));

        ImageIcon iconBlue = new ImageIcon("./src/icons/icon_blue.png");
        ImageIcon iconGreen = new ImageIcon("./src/icons/icon_green.png");

        EmptyBorder eb = new EmptyBorder(0, 10, 0, 0);
        
        JLabel lbBlue = new JLabel("Đã điểm danh vào", iconBlue, JLabel.RIGHT);
        JLabel lbGreen = new JLabel("Đã điểm danh ra", iconGreen, JLabel.RIGHT);
        lbBlue.setBorder(eb);
        lbGreen.setBorder(eb);
        
        String hd = "Hiện diện: ";
        lbHD = new JLabel(hd);
        lbHD.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        panelHD1.add(spHD);
        panelHD2.add(lbBlue);
        panelHD2.add(lbGreen);
        panelHD3.setLayout(new BorderLayout());
        panelHD3.add(lbHD, BorderLayout.WEST);
        
        boxesHD[0].add(panelHD1);
        boxesHD[1].add(panelHD2);
        boxesHD[2].add(panelHD3);
        
        //add panel center
        panelC.add(panelSS);
        panelC.add(panelHD);
        
        //cai date layout south
        JPanel panelS = new JPanel();
        panelS.setBackground(Color.white);
        
        JPanel panelBack = new JPanel();
        panelBack.setBorder(new EmptyBorder(0, 0, 0, 50));
        panelBack.setBackground(Color.white);
        JPanel panelStop = new JPanel();
        panelStop.setBorder(new EmptyBorder(0, 0, 0, 50));
        panelStop.setBackground(Color.white);
        JPanel panelStart = new JPanel();
        panelStart.setBorder(new EmptyBorder(0, 0, 0, 50));
        panelStart.setBackground(Color.white);
        JPanel panelCheckOut = new JPanel();
        panelCheckOut.setBorder(new EmptyBorder(0, 0, 0, 50));
        panelCheckOut.setBackground(Color.white);
        JPanel panelEnd = new JPanel();
        panelEnd.setBorder(new EmptyBorder(0, 0, 0, 50));
        panelEnd.setBackground(Color.white);
        
        Dimension d = new Dimension(100, 100);
        JButton btnBack = new JButton("Trở về");
        btnBack.setBackground(Color.white);
        JButton btnStop = new JButton("Tạm ngưng");
        btnStop.setBackground(Color.white);
        JButton btnStart = new JButton("Tiếp tục");
        btnStart.setEnabled(false);
        btnStart.setBackground(Color.white);
        JButton btnCheckOut = new JButton("Check out");
        btnCheckOut.setForeground(new Color(240, 0, 0));
        btnCheckOut.setBackground(Color.white);
        JButton btnEnd = new JButton("Kết thúc");
        btnEnd.setBackground(Color.white);
        
        panelBack.add(btnBack);
        panelStop.add(btnStop);
        panelStart.add(btnStart);
        panelCheckOut.add(btnCheckOut);
        panelEnd.add(btnEnd);
        
        //add panel south
        panelS.add(panelBack);
        panelS.add(panelStop);
        panelS.add(panelStart);
        panelS.add(panelCheckOut);
        panelS.add(panelEnd);
        
        btnBack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Main.reader.stop();
                    Main.reader.disconnect();
                } catch (OctaneSdkException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
                Main.menu.getItem(0).setEnabled(true);

                Main.frame.remove(Main.dd);
                Main.frame.setSize(500, 400);
                Main.frame.setLocationRelativeTo(null);
                Main.frame.add(Main.ng);
                Main.frame.update(Main.frame.getGraphics());
            }
            
        });
        
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart.setEnabled(true);
                try{
                    Main.reader.stop();
                } catch (OctaneSdkException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
                btnStop.setEnabled(false);
            }
        });
        
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStop.setEnabled(true);
                try{
                    Main.reader.start();
                } catch (OctaneSdkException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
                btnStart.setEnabled(false);
            }
        });
        
        btnCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.ddBUS.checkOut){
                    btnCheckOut.setForeground(new Color(240, 0, 0));
                    Main.ddBUS.checkOut = false;
                } else {
                    btnCheckOut.setForeground(new Color(0, 240, 0));
                    Main.ddBUS.checkOut = true;
                }
            }
        });
        
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(Main.ddBUS.insertDD("SV2"))
//                {
//                    Main.dd.panelSS.removeAll();
//                    Main.dd.panelHD.removeAll();
//
//                    Main.dd.renewPanelSSandHD();
//
//                    Main.frame.pack();
//                    Main.frame.setLocationRelativeTo(null);
//                    Main.dd.panelSS.updateUI();
//                    Main.dd.panelHD.updateUI();
//                }

                try{  
                    Main.reader.stop();
                    Main.reader.disconnect();
                } catch (OctaneSdkException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(System.out);
                }

                Main.ddBUS.updateDDVang();
                
                for(Hoc h : lsH)
                {
                    Main.ddBUS.insertDDVang(h.getMaSV());
                }

                Main.frame.remove(Main.dd);
                Main.bc.renewPanelBottom(Main.bc.cols, Main.bc.begin, Main.bc.end);
                Main.frame.add(Main.bc);
                Main.frame.pack();
                Main.frame.setLocationRelativeTo(null);
                Main.frame.update(Main.frame.getGraphics());
            }
        });
        
        //add vao panel chinh
        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);
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
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                frame.add(new DiemDanhGUI());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    public void renewTable()
    {
        
        lsH = hBUS.listHocByLHNotInDD(MaLH);
        lsDD = Main.ddBUS.GetAllDDonDate();
        
        modelSS = new DefaultTableModel();
        tblSS.setModel(modelSS);
        modelSS.addColumn("Mã SV");
        modelSS.addColumn("Họ tên");
        for(Hoc h : lsH)
        {
            System.out.println(h.getMaSV());
            SinhVien sv = svBUS.GetByID(h.getMaSV());
            String[] rows = {sv.getMaSV(), sv.getHoTen()};
            modelSS.addRow(rows);
        }
        
        modelHD = new DefaultTableModel();
        tblHD.setModel(modelHD);
        modelHD.addColumn("Mã SV");
        modelHD.addColumn("Họ tên");
        modelHD.addColumn("Giờ vào");
        modelHD.addColumn("Giờ ra");
        modelHD.addColumn("Vi phạm");
        TableColumnModel columnModel = tblHD.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(100);
        for(DiemDanh dd : lsDD)
        {
            SinhVien sv = svBUS.GetByID(dd.getMaSV());
            if(dd.getGioRa() == null)
            {
                String[] rows = {setColor(dd.getMaSV(), "blue"), setColor(sv.getHoTen(), "blue"),
                    setColor(dd.getGioVao().toLocalTime().toString(), "blue"), "", ""};
                if(dd.isVaoTre())
                {
                    rows[4] = setColor("Vào trễ", "blue"); 
                }
                modelHD.addRow(rows);
            }
            if(dd.getGioRa() != null)
            {
                String[] rows = {setColor(dd.getMaSV(), "green"), setColor(sv.getHoTen(), "green"),
                    setColor(dd.getGioVao().toLocalTime().toString(), "green"),
                    setColor(dd.getGioRa().toLocalTime().toString(), "green"), ""};
                if(dd.isVaoTre() && !dd.isVeSom())
                {
                    rows[4] = setColor("Vào trễ", "green"); 
                }
                if(!dd.isVaoTre() && dd.isVeSom())
                {
                    rows[4] = setColor("Về sớm", "green"); 
                }
                if(dd.isVaoTre() && dd.isVeSom())
                {
                    rows[4] = setColor("Vào trễ + về sớm", "green"); 
                }
                modelHD.addRow(rows);
            }
        }
        String hd = "Hiện diện: " + lsDD.size();
        lbHD.setText(hd);
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
    
    public void setPanelN()
    {   
        panelN.removeAll();
        panelN.setBackground(Color.BLACK);
        
        GridBagLayout gbLayoutN = new GridBagLayout();
        panelN.setLayout(gbLayoutN);
        
        JLabel lbTieuDe1 = new JLabel("Điểm danh");
        lbTieuDe1.setHorizontalAlignment(JLabel.CENTER);
        lbTieuDe1.setFont(new Font("Verdana", Font.BOLD, 30));
        lbTieuDe1.setForeground(new Color(255,222,76));
        
        String strGV = Main.ddBUS.gioVao.toString();
        String strGR = Main.ddBUS.gioRa.toString();
        
        JLabel lbTieuDe2 = new JLabel("Giờ vào: " + strGV + "   Giờ ra: " + strGR);
        lbTieuDe2.setHorizontalAlignment(JLabel.CENTER);
        lbTieuDe2.setFont(new Font("Verdana", Font.BOLD, 20));
        lbTieuDe2.setForeground(new Color(255,222,76));
        
        GridBagConstraints gbcN = new GridBagConstraints();
        gbcN.fill = GridBagConstraints.HORIZONTAL;
        gbcN.gridx = 0;
        gbcN.gridy = 0;
        
        //add vao panel north
        panelN.add(lbTieuDe1, gbcN);
        gbcN.gridy += 1;
        panelN.add(lbTieuDe2, gbcN);
    }
}
