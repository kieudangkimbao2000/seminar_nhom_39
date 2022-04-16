
import ConnectDB.ConnectDB;
import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kieud
 */
public class test {
    class MyCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof String) {
                String string = (String) value;
                if (string.indexOf('[') > -1) {
                    setText(getHTML(string));
                }
            }
            return tableCellRendererComponent;
        }

        private String getHTML(String string) {
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
//            int index = 0;
//            while (index < string.length()) {
//                int next = string.indexOf('[', index);
//                if (next > -1) {
//                    int end = string.indexOf(']', next);
//                    if (end > -1) {
//                        next++;
//                        sb.append(string.substring(index, next));
                        sb.append("<span style=\"color: red;\">");
                        sb.append(string);
                        sb.append("</span>");
//                        index = end;
//                    } else {
//                        break;
//                    }
//                } else {
//                    break;
//                }
//            }
//            sb.append(string.substring(index, string.length()));
            sb.append("</html>");
            return sb.toString();
        }
    }

    protected void initUI() {
        DefaultTableModel model = new DefaultTableModel();
        for (int i = 0; i < 2; i++) {
            model.addColumn("Col-" + (i + 1));
        }
        for (int i = 0; i < 200; i++) {
            Vector<Object> row = new Vector<Object>();
            for (int j = 0; j < 5; j++) {
                row.add("423545(50),[7568787(50)],53654656,2021947(50),[021947],2021947(50),[8021947(50)]");
            }
            model.addRow(row);
        }
        JTable table = new JTable(model);
        table.setDefaultRenderer(Object.class, new MyCellRenderer());
        JFrame frame = new JFrame(test.class.getSimpleName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollpane = new JScrollPane(table);
        frame.add(scrollpane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new test().initUI();
            }
        });
    }
}
