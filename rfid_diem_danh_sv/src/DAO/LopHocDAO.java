/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.LopHoc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieud
 */
public class LopHocDAO {
    ConnectDB connDB = new ConnectDB("rfid_nhom_39", "root", "");
    
    public LopHoc getLHByRoomandID(String MaPH, String MaLH)
    {
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
        
        String query = "SELECT bt.MaLH, MIN(th.TGBD) TGBD, MAX(th.TGKT) TGKT FROM botri bt LEFT JOIN tiethoc th "
                + "ON bt.MaTH=th.MaTH WHERE bt.MaLH='" + MaLH + "' AND bt.MaPH='" + MaPH + "' AND bt.Ngay='" + strNow + "' AND "
                + "th.Buoi='" + buoi + "' "
                + "GROUP BY bt.MaLH;";
        LopHoc lh = null;
        
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                lh = new LopHoc(rs.getString(1), null, rs.getTime(2).toLocalTime(), rs.getTime(3).toLocalTime());
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lh;
    }
    
    public LopHoc getLHByID(String MaLH)
    {
        Connection conn = connDB.getConnection();
        String query = "SELECT lh.MaLH, CONCAT(lh.MaLH, '-', mh.TenMH) TenLH FROM lophoc lh LEFT JOIN monhoc mh "
                + "ON lh.MaMH=mh.MaMH WHERE lh.MaLH='" + MaLH + "';";
        LopHoc lh = null;
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                lh = new LopHoc(rs.getString(1), rs.getString(2), null, null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lh;
    }
    
    public static void main(String[] args) {
        
    }
}
