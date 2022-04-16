/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.SinhVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kieud
 */
public class SinhVienDAO {
    ConnectDB connDB;
    
    public List<SinhVien> GetAll()
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        String query = "select * from sinhvien";
        List<SinhVien> listSV = new ArrayList<SinhVien>();
        try
        {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                SinhVien sv = new SinhVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                listSV.add(sv);
            }
            conn.close();
        } catch(SQLException e)
        {
            System.out.println("Lay du lieu sinh vien that bai");
        }
        
        return listSV;
    }
    
    public SinhVien GetByID(String id)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        String query = "select * from sinhvien where MaSV='"+id+"'";
        SinhVien sv = null;
        
        try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                sv = new SinhVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
            System.out.println(ex);
        }
        
        return sv;
    }
    
    public List<SinhVien> getSVNotInDD()
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        LocalDate date = LocalDate.now();
        String month;
        String day;
        if(date.getMonthValue() < 10)
        {
            month = "0" + date.getMonthValue();
        }else
        {
            month = String.valueOf(date.getMonthValue());
        }
        if(date.getDayOfMonth() < 10)
        {
            day = "0" + date.getDayOfMonth();
        }else
        {
            day = String.valueOf(date.getDayOfMonth());
        }
        String strNow = date.getYear() + "-" + month + "-" + date.getDayOfMonth();
        
        String query = "select * from sinhvien sv where sv.MaSV not in (select dd.MaSV from diemdanh dd where dd.GioVao like '"+strNow+"%');";
        
        List<SinhVien> listSV = new ArrayList<SinhVien>();
        try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                SinhVien sv = new SinhVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                listSV.add(sv);
            }
            conn.close();
        } catch(SQLException e)
        {
            System.out.println("Lay du lieu sinh vien that bai");
        }
        
        return listSV;
    }
    
    public static void main(String[] args) {
        new SinhVienDAO().getSVNotInDD().forEach(sv -> {
            System.out.println(sv.getMaSV());
        });
    }
}
