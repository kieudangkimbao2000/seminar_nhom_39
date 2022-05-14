/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BoTriBUS;
import BUS.LopHocBUS;
import DTO.LopHoc;
//import com.example.sdksamples.SampleProperties;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author kieud
 */
public class NhapGioGUI extends javax.swing.JPanel {
    public JComboBox cbLH;
    public JTextField tfGioVao;
    public JTextField tfGioRa;
    
    public String MaPH;
    public int tgVT;
    public int tgVS;
    
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
        panelN.setBorder(new EmptyBorder(20, 0, 20, 0));
        panelN.setBackground(Color.BLACK);
        
        GridBagLayout gbLayoutN = new GridBagLayout();
        panelN.setLayout(gbLayoutN);
        
        JLabel lbTieuDe1 = new JLabel("RFID");
        lbTieuDe1.setHorizontalAlignment(JLabel.CENTER);
        lbTieuDe1.setFont(new Font("Verdana", Font.BOLD, 30));
        lbTieuDe1.setForeground(new Color(255,222,76));
        
        JLabel lbTieuDe2 = new JLabel("Điểm danh sinh viên");
        lbTieuDe2.setHorizontalAlignment(JLabel.CENTER);
        lbTieuDe2.setFont(new Font("Verdana", Font.BOLD, 20));
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
        JLabel lbLH = new JLabel("Lớp học");
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
        dpNgay.getComponent(1).setBackground(Color.white);
        dpNgay.setDate(LocalDate.now());
        dpNgay.setEnabled(false);
        
        BoTriBUS btBUS = new BoTriBUS();
        
        Properties prop = readFileCauHinh();
        MaPH = prop.getProperty("MaPH").toString();
        tgVT = Integer.valueOf(prop.getProperty("VT").toString());
        tgVS = Integer.valueOf(prop.getProperty("VS").toString());
        String[] arrayLH = btBUS.getListByPHandNgay(MaPH)
                .stream()
                .map(bt -> bt.getMaLH())
                .toArray(String[]::new);
        cbLH = new  JComboBox(arrayLH);
        cbLH.setBackground(Color.white);
        
        tfGioVao = new JTextField();
        tfGioVao.setEditable(false);
        tfGioRa = new JTextField();
        tfGioRa.setEditable(false);
        
        if(cbLH.getItemCount() > 0)
        {
            LopHoc lh = new LopHocBUS().getLHByRoomandID(MaPH, cbLH.getSelectedItem().toString());
            tfGioVao.setText(lh.getGioVao().toString());
            tfGioRa.setText(lh.getGioRa().toString());
        }
        
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
        panelC.add(lbLH, gbcC);
        gbcC.gridx += 1;
        panelC.add(cbLH, gbcC);
        gbcC.gridx = 0;
        gbcC.gridy += 1;
        panelC.add(lbGioVao, gbcC);
        gbcC.gridx += 1;
        panelC.add(tfGioVao, gbcC);
        gbcC.gridx += 1;
        panelC.add(lbWarGioVao, gbcC);
        gbcC.gridx = 0;
        gbcC.gridy += 1;
        panelC.add(lbGioRa, gbcC);
        gbcC.gridx += 1;
        panelC.add(tfGioRa, gbcC);
        gbcC.gridx += 1;
        panelC.add(lbWarGioRa, gbcC);
        
        //tao layout cho south
        JPanel panelS = new  JPanel();
        panelS.setBackground(Color.white);
        
        FlowLayout fLayoutS = new FlowLayout();
        panelS.setLayout(fLayoutS);
        
        JButton bTiepTuc = new JButton("Tiếp tục");
        bTiepTuc.setBackground(Color.white);
        
        panelS.add(bTiepTuc);
        
        //xu ly event
        cbLH.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cbLH.getItemCount() > 0)
                {
                    LopHoc lh = new LopHocBUS().getLHByRoomandID(MaPH, cbLH.getSelectedItem().toString());
                    tfGioVao.setText(lh.getGioVao().toString());
                    tfGioRa.setText(lh.getGioRa().toString());
                }
            }
        });
        
        bTiepTuc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbLH.getItemCount() > 0){
                    Main.menu.getItem(0).setEnabled(false);
                    LopHoc lh = new LopHocBUS().getLHByRoomandID(MaPH, cbLH.getSelectedItem().toString());
                    Main.ddBUS.gioVao = lh.getGioVao();
                    Main.ddBUS.gioRa = lh.getGioRa();
                    Main.ddBUS.MaLH = cbLH.getSelectedItem().toString();
                    Main.ddBUS.tgVT = tgVT;
                    Main.ddBUS.tgVS = tgVS;
                    Main.dd.MaLH = cbLH.getSelectedItem().toString();
                    Main.ddBUS.listDD = Main.ddBUS.GetAllDDonDate();
                    
                    settingReader();
                    
                    Main.frame.remove(Main.ng);
                    Main.dd.setPanelN();
                    
                    Main.dd.renewTable();
                    Main.dd.lbSS.setText("Sỉ số: " + Main.dd.lsH.size());
                    
                    Main.frame.add(Main.dd);
                    Main.frame.pack();
                    Main.frame.setLocationRelativeTo(null);
                    Main.frame.update(Main.frame.getGraphics());
                } else {
                        String thongBao = "Chưa có lớp được chọn!";
                        ThongBaoGUI tbGUI = new ThongBaoGUI(thongBao, ThongBaoGUI.WARNING);
                }
            }
        
        });
        
        //them cac thanh phan vao panel chinh
        add(panelN, BorderLayout.NORTH);
        add(panelS, BorderLayout.SOUTH);
        add(panelC, BorderLayout.CENTER);
    }
    
    private void getComponents(Container c){
        Component[] m = c.getComponents();

        for(int i = 0; i < m.length; i++){

            if(m[i].getClass().getName() == "javax.swing.JPanel")
                m[i].setBackground(Color.white);

            if(c.getClass().isInstance(m[i]))
                getComponents((Container)m[i]);
        }
    }
    
    public Properties readFileCauHinh()
    {
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

    public static void settingReader(){
        try {
//            String hostname = System.getProperty(SampleProperties.hostname);
            String hostname = "169.254.96.13";

//            if (hostname == null) {
//                throw new Exception("Must specify the '"
//                        + SampleProperties.hostname + "' property");
//            }
            System.out.println("Connecting");
            Main.reader.connect(hostname);

            Settings settings = Main.reader.queryDefaultSettings();

            ReportConfig report = settings.getReport();
            report.setIncludeAntennaPortNumber(true);
            report.setMode(ReportMode.Individual);

            // The reader can be set into various modes in which reader
            // dynamics are optimized for specific regions and environments.
            // The following mode, AutoSetDenseReader, monitors RF noise and interference and then automatically
            // and continuously optimizes the reader's configuration
            settings.setReaderMode(ReaderMode.AutoSetDenseReader);

            // set some special settings for antenna 1
            AntennaConfigGroup antennas = settings.getAntennas();
            antennas.disableAll();
            antennas.enableById(new short[]{1});
            antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
            antennas.getAntenna((short) 1).setIsMaxTxPower(false);
            antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
            antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

//            reader.setTagReportListener(new TagReportListenerImplementation());
            Main.reader.setTagReportListener(new MyTagReportListenerImplementation());
            
            System.out.println("Applying Settings");
            Main.reader.applySettings(settings);

            System.out.println("Starting");
            Main.reader.start();

//            System.out.println("Press Enter to exit.");
//            Scanner s = new Scanner(System.in);
//            s.nextLine();
//
//            reader.stop();
//            reader.disconnect();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
    
    static class MyTagReportListenerImplementation implements TagReportListener{
        
        @Override
        public void onTagReported(ImpinjReader reader, TagReport report) {
            List<Tag> tags = report.getTags();
            for(Tag t : tags)
            {
                String MaSV = t.getEpc().toString();
                if(Main.ddBUS.insertDD(MaSV))
                {
                    Main.dd.renewTable();
                }
            }
        }
        
    }
}
