
import ConnectDB.ConnectDB;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kieud
 */
public class test {
    public static void main(String[] args)
    {
//        ConnectDB connDB = new ConnectDB("rfid_nhom_39", "root", "");
//        try{
//            Connection conn = connDB.getConnection();
//            Statement stat = conn.createStatement();
//            LocalDateTime dateTime = LocalDateTime.now();
//            System.out.println(dateTime.getDayOfYear());
//            stat.execute("insert into diemdanh(MaSV, GIoVao, GioRa, VaoTre, VeSom) values('SV1', '2022-04-11', '2022-04-12', false, false);");
//        }catch (Exception ex)
//        {
//            System.out.println(ex);
//        }
        
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.getDayOfMonth());
        String str = String.valueOf(dateTime.getYear()) + '-' + String.valueOf(dateTime.getMonthValue()) + 
                '-' +String.valueOf(dateTime.getDayOfMonth()) + ' ';
        System.out.println(str);
    }
}
