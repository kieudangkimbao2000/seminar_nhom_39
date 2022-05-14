/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.BoTri;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieud
 */
public class BoTriDAO {
    ConnectDB connDB = new ConnectDB("rfid_nhom_39", "root", "");
    
    public List<BoTri> getListByPHandNgay(String MaPH)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        LocalDate now = LocalDate.now();
        LocalTime timeNow =  LocalTime.now();
        LocalTime trua = LocalTime.of(12, 0);
        String month;
        String day;
        if(now.getMonthValue() < 10)
        {
            month = "0" + now.getMonthValue();
        }else
        {
            month = String.valueOf(now.getMonthValue());
        }
        if(now.getDayOfMonth() < 10)
        {
            day = "0" + now.getDayOfMonth();
        }else
        {
            day = String.valueOf(now.getDayOfMonth());
        }
        String strNow = now.getYear() + "-" + month + "-" + now.getDayOfMonth();
        String buoi = "Sang";
        if(timeNow.compareTo(trua) > 0)
        {
            buoi = "Chieu";
        }
        
        
        String query = "SELECT bt.MaLH, bt.MaPH, bt.MaLH, bt.Ngay FROM botri bt LEFT JOIN tiethoc th ON bt.MaTH=th.MaTH "
                + "WHERE bt.MaPH='" + MaPH + "' and bt.Ngay='" + strNow + "' and th.Buoi='" + buoi + "'GROUP BY bt.MaLH;";
        List<BoTri> lsBT = new ArrayList<BoTri>();
        
        try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                BoTri bt = new BoTri(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
                lsBT.add(bt);
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
            System.out.println(ex);
        }
        
        return lsBT;
    }
    
    public static void main(String[] args) {
        List<BoTri> lsBT = new BoTriDAO().getListByPHandNgay("C.B.004");
        for(BoTri bt : lsBT)
        {
            System.out.println(bt.getMaLH());
        }
    }
}
