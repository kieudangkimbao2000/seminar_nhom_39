/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.SinhVienBUS;
import DTO.BaoCao;
import DTO.SinhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author kieud
 */
public class BaoCaoGUI extends javax.swing.JPanel {
//    public DiemDanhBUS ddBUS = new DiemDanhBUS();

    public SinhVienBUS svBUS = new SinhVienBUS();
    public JPanel panelBottom = new JPanel();
    public JTable tbl;
    public DefaultTableModel model = new DefaultTableModel();
    public LocalDate begin = LocalDate.now();
    public LocalDate end = begin.plusDays(1);
    public List<BaoCao> lsBC;
    public String[] cols = {"MaSV", "Họ tên", "Số lần trễ", "Số lần về sớm", "Số lần vắng"};
    public int typeBaoCao = 0;

    /**
     * Creates new form BaoCaoGUI
     */
    public BaoCaoGUI() {
        setBackground(Color.white);

        setLayout(new BorderLayout());

        //cai dat layout north
        JPanel panelN = new JPanel();
        panelN.setBackground(Color.black);
        panelN.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel lbBC = new JLabel("Báo cáo");
        lbBC.setHorizontalAlignment(JLabel.CENTER);
        lbBC.setFont(new Font("Verdana", Font.BOLD, 30));
        lbBC.setForeground(new Color(255, 222, 76));

        //add vao panel north
        panelN.add(lbBC);

        //cai date layout center
        JPanel panelC = new JPanel();
        panelC.setBackground(Color.white);
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));

        JPanel panelTop = new JPanel();
        panelTop.setBackground(Color.white);
        JButton btnNgay = new JButton("Trong ngày");
        btnNgay.setBackground(Color.white);
        btnNgay.setEnabled(false);
        JButton btnTuan = new JButton("Tuần");
        btnTuan.setBackground(Color.white);
        JButton btnThang = new JButton("Tháng");
        btnThang.setBackground(Color.white);
        JButton btnNam = new JButton("Năm");
        btnNam.setBackground(Color.white);

        JLabel lbSearch = new JLabel("Tìm kiếm:");

        JTextField tfSearch = new JTextField(7);

        panelTop.add(btnNgay);
        panelTop.add(btnTuan);
        panelTop.add(btnThang);
        panelTop.add(btnNam);
        panelTop.add(lbSearch);
        panelTop.add(tfSearch);

        tbl = new JTable(model);

        for (int i = 0; i < cols.length; i++) {
            model.addColumn(cols[i]);
        }

        JScrollPane sp = new JScrollPane(tbl);
        sp.getViewport().setBackground(new Color(250, 250, 250));

        panelBottom.setBackground(Color.white);
        panelBottom.add(sp);

        panelC.add(panelTop);
        panelC.add(panelBottom);

        //cai dat layout east
        JPanel panelE = new JPanel();
        panelE.setBackground(Color.white);
//        panelE.setLayout();
        panelE.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));

        JPanel panelEcon = new JPanel();
        panelEcon.setLayout(new BoxLayout(panelEcon, BoxLayout.Y_AXIS));
        panelEcon.setBackground(Color.white);

        JButton btnExp = new JButton("Xuất Excel");
        btnExp.setBackground(Color.white);
        btnExp.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnFirst = new JButton("Trang đầu");
        btnFirst.setBackground(Color.white);
        btnFirst.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add vao panel east con
        panelEcon.add(btnExp);
        panelEcon.add(Box.createVerticalStrut(10));
        panelEcon.add(btnFirst);

        //add vao panel east
        panelE.add(panelEcon);

        //cai dat event cho btn
        btnNgay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeBaoCao = 0;
                btnNgay.setEnabled(false);
                btnTuan.setEnabled(true);
                btnThang.setEnabled(true);
                btnNam.setEnabled(true);

                begin = LocalDate.now();
                end = begin.plusDays(1);

                renewPanelBottom(cols, begin, end);
            }

        });
        btnTuan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeBaoCao = 1;
                btnNgay.setEnabled(true);
                btnTuan.setEnabled(false);
                btnThang.setEnabled(true);
                btnNam.setEnabled(true);

                LocalDate now = LocalDate.now();
                begin = now.withDayOfMonth(now.getDayOfMonth() - (now.getDayOfWeek().getValue() - 1));
                try {
                    end = now.withDayOfMonth(now.getDayOfMonth() + (7 - (now.getDayOfWeek().getValue() - 1)));
                } catch (Exception ex) {
                    if (now.getMonthValue() == 12) {
                        end = LocalDate.of(now.getYear() + 1, 1, 1);
                    } else {
                        end = LocalDate.of(now.getYear(), now.getMonthValue() + 1, 1);
                    }
                }

                renewPanelBottom(cols, begin, end);
            }
        });
        btnThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeBaoCao = 2;
                btnNgay.setEnabled(true);
                btnTuan.setEnabled(true);
                btnThang.setEnabled(false);
                btnNam.setEnabled(true);

                LocalDate now = LocalDate.now();
                begin = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
                if (now.getMonthValue() == 12) {
                    end = LocalDate.of(now.getYear() + 1, 1, 1);
                } else {
                    end = LocalDate.of(now.getYear(), now.getMonthValue() + 1, 1);
                }

                renewPanelBottom(cols, begin, end);
            }
        });
        btnNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeBaoCao = 3;
                btnNgay.setEnabled(true);
                btnTuan.setEnabled(true);
                btnThang.setEnabled(true);
                btnNam.setEnabled(false);

                LocalDate now = LocalDate.now();
                begin = LocalDate.of(now.getYear(), 1, 1);
                end = LocalDate.of(now.getYear() + 1, 1, 1);

                renewPanelBottom(cols, begin, end);
            }
        });

        tfSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lsBC = Main.ddBUS.BaoCaoDD(begin, end);
                model = new DefaultTableModel();
                tbl.setModel(model);

                for (int i = 0; i < cols.length; i++) {
                    model.addColumn(cols[i]);
                }
                for (BaoCao bc : lsBC) {
                    SinhVien sv = svBUS.GetByID(bc.getMaSV());
                    String[] format = {bc.getMaSV(), sv.getHoTen()};
                    String search = String.format("%s %s", format);
                    if (search.matches("(.*)" + tfSearch.getText().toString() + "(.*)")) {
                        String[] rows = {bc.getMaSV(), sv.getHoTen(), String.valueOf(bc.getTotalVT()), String.valueOf(bc.getTotalVS()),
                            String.valueOf(bc.getTotalV())};
                        model.addRow(rows);
                    }
                }
            }
        });

        btnExp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Xuất báo cáo");

                String userHomeDir = System.getProperty("user.home");

                JFileChooser fileChooser = new JFileChooser(userHomeDir);
                fileChooser.setSelectedFile(new File("bao_cao.xls"));

                int userSelection = fileChooser.showSaveDialog(frame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    exportExcel_2(fileChooser.getSelectedFile());
                }

//                exportExcel();
            }
        });

        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.menu.getItem(0).setEnabled(true);

                Main.frame.remove(Main.bc);
                Main.frame.setSize(500, 400);
                Main.frame.setLocationRelativeTo(null);
                Main.frame.add(Main.ng);
                Main.frame.update(Main.frame.getGraphics());
            }
        });

        //add event cho table
        tbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                selectRowEvent(me);
            }
        });

        //add vao panel chinh
        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelE, BorderLayout.EAST);
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
    public void renewPanelBottom(String[] cols, LocalDate begin, LocalDate end) {
        lsBC = Main.ddBUS.BaoCaoDD(begin, end);
        model = new DefaultTableModel();
        tbl.setModel(model);

        for (int i = 0; i < cols.length; i++) {
            model.addColumn(cols[i]);
        }

        for (BaoCao bc : lsBC) {
            SinhVien sv = svBUS.GetByID(bc.getMaSV());
            String[] rows = {bc.getMaSV(), sv.getHoTen(), String.valueOf(bc.getTotalVT()), String.valueOf(bc.getTotalVS()),
                String.valueOf(bc.getTotalV())};
            model.addRow(rows);
        }
    }

    public void selectRowEvent(MouseEvent e) {
        String MaSV = tbl.getValueAt(tbl.getSelectedRow(), 0).toString();

        ChiTietBaoCaoGUI ctbcGUI = new ChiTietBaoCaoGUI(MaSV, begin, end);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new BaoCaoGUI());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void exportExcel() {
        List<String[]> datasExport = new ArrayList<String[]>();
        String[] header = {"MaSV", "Họ tên", "Số lần trễ", "Số lần về sớm", "Số lần vắng"};
        datasExport.add(header);

        for (BaoCao bc : lsBC) {
            SinhVien sv = svBUS.GetByID(bc.getMaSV());
            String[] rows = new String[5];
            rows[0] = bc.getMaSV();
            rows[1] = sv.getHoTen();
            rows[2] = String.valueOf(bc.getTotalVT());
            rows[3] = String.valueOf(bc.getTotalVS());
            rows[4] = String.valueOf(bc.getTotalV());
            datasExport.add(rows);
        }

        JFrame frame = new JFrame("Save file");
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            System.out.println("Save as file: " + fileToSave.getAbsolutePath());

            File csvFile = fileChooser.getSelectedFile();;
            try ( PrintWriter pw = new PrintWriter(csvFile)) {
                datasExport.stream()
                        .map(this::convertToCSV)
                        .forEach(pw::println);
            } catch (IOException ex) {
                System.out.println("Không thể xuất file");
            }
        }
    }

    public void exportExcel_2(File fileExcel) {
        String[] header = {"MaSV", "Họ tên", "Số lần trễ", "Số lần về sớm", "Số lần vắng"};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Báo cáo");

        //title của excelt
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeight((short) 1500);

        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderBottom(BorderStyle.THIN);

        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("Times New Roman");
        HSSFPalette palette = workbook.getCustomPalette();
        titleFont.setColor(palette.findSimilarColor(255, 222, 76).getIndex());
        titleFont.setFontHeightInPoints((short) 20);
        titleStyle.setFont(titleFont);

        HSSFCell titleCell = titleRow.createCell(0);
        switch (typeBaoCao) {
            case 0:
                titleCell.setCellValue("Báo cáo ngày lớp " + Main.ddBUS.MaLH);
                break;
            case 1:
                titleCell.setCellValue("Báo cáo tuần lớp " + Main.ddBUS.MaLH);
                break;
            case 2:
                titleCell.setCellValue("Báo cáo tháng lớp " + Main.ddBUS.MaLH);
                break;
            default:
                titleCell.setCellValue("Báo cáo năm lớp " + Main.ddBUS.MaLH);
                break;
        }
        titleCell.setCellStyle(titleStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        HSSFRow headerRow = sheet.createRow(1);

        //header của excel
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
//        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontName("Times New Roman");
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        HSSFCell headerCell;
        for (int i = 0; i < header.length; i++) {
            headerCell = headerRow.createCell(i);
            sheet.setColumnWidth(i, 10000);
            headerCell.setCellValue(header[i]);
            headerCell.setCellStyle(headerStyle);
        }

        //noi dung excel
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
//        style.setWrapText(true);

        HSSFFont font = workbook.createFont();
        font.setFontName("Times New Roman");
        style.setFont(font);

        int row_position = 2;
        HSSFRow row;
        for (BaoCao bc : lsBC) {
            SinhVien sv = svBUS.GetByID(bc.getMaSV());

            row = sheet.createRow(row_position);

            HSSFCell cellMaSV = row.createCell(0);
            cellMaSV.setCellValue(bc.getMaSV());
            cellMaSV.setCellStyle(style);

            HSSFCell cellHoTen = row.createCell(1);
            cellHoTen.setCellValue(sv.getHoTen());
            cellHoTen.setCellStyle(style);

            HSSFCell cellTotalVT = row.createCell(2);
            cellTotalVT.setCellValue(bc.getTotalVT());
            cellTotalVT.setCellStyle(style);

            HSSFCell cellTotalVS = row.createCell(3);
            cellTotalVS.setCellValue(bc.getTotalVS());
            cellTotalVS.setCellStyle(style);

            HSSFCell cellTotalV = row.createCell(4);
            cellTotalV.setCellValue(bc.getTotalV());
            cellTotalV.setCellStyle(style);

            row_position++;
        }

        //xuat file
        fileExcel.getParentFile().mkdirs();

        try {
            FileOutputStream outputStream = new FileOutputStream(fileExcel);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaoCaoGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaoCaoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
