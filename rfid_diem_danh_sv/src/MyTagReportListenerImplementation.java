
import ConnectDB.ConnectDB;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kieud
 */
public class MyTagReportListenerImplementation implements TagReportListener{

    @Override
    public void onTagReported(ImpinjReader reader, TagReport report) {
        ConnectDB connDB = new ConnectDB("rfid_nhom_39", "root", "");
        List<Tag> tags = report.getTags();
        try
        {
            Connection conn = connDB.getConnection();
            Statement stat = conn.createStatement();
            for(Tag t : tags)
            {
                String MaSV = t.getEpc().toString();
//                MaSV = MaSV.replaceAll(" ", "");
                System.out.println(MaSV);
//                stat.executeUpdate("insert into diemdanh(MaSV, GIoVao, GioRa, VaoTre, RaSom) values("+ MaSV +", '2022-04-11', '2022-04-12', false, false);");
            }
        } catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
}
