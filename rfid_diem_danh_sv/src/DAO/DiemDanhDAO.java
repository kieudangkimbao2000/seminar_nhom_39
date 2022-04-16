/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.DiemDanh;
import DTO.SinhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieud
 */
public class DiemDanhDAO {
    ConnectDB connDB;
    
    public List<DiemDanh> GetAll()
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        String query = "select * from diemdanh";
        List<DiemDanh> lsDD = new ArrayList<DiemDanh>();
        
        try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                DiemDanh dd;
                if(rs.getTimestamp(3) == null)
                {
                    dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), null,
                    rs.getBoolean(4), rs.getBoolean(5));
                } else
                {
                    dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), rs.getTimestamp(3).toLocalDateTime(),
                    rs.getBoolean(4), rs.getBoolean(5));
                }
                
                lsDD.add(dd);
            }
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
            System.out.println(ex);
        }
        
        return lsDD;
    }
    
    public List<DiemDanh> GetAllDDonDate()
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
        
        String query = "select * from diemdanh where GioVao like '"+strNow+"%'";
        List<DiemDanh> lsDD = new ArrayList<DiemDanh>();
        
        try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                DiemDanh dd;
                if(rs.getTimestamp(3) == null)
                {
                    dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), null,
                    rs.getBoolean(4), rs.getBoolean(5));
                } else
                {
                    dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), rs.getTimestamp(3).toLocalDateTime(),
                    rs.getBoolean(4), rs.getBoolean(5));
                }
                
                lsDD.add(dd);
            }
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
            System.out.println(ex);
        }
        
        return lsDD;
    }
    
    public List<DiemDanh> getListDDByID(String MaSV)
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
                
        String query = "select * from diemdanh dd where dd.MaSV='"+MaSV+"' and dd.GioVao like '"+strNow+"%';";
        List<DiemDanh> lsDD = new ArrayList<DiemDanh>();
         
         try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                DiemDanh dd;
                if(rs.getTimestamp(3) == null)
                {
                    dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), null,
                    rs.getBoolean(4), rs.getBoolean(5));
                } else
                {
                    dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), rs.getTimestamp(3).toLocalDateTime(),
                    rs.getBoolean(4), rs.getBoolean(5));
                }
                
                lsDD.add(dd);
            }
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
            System.out.println(ex);
        }
        
        return lsDD;
    }
    
    public DiemDanh getDDVaoNull(String MaSV)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "select * from diemdanh where MaSV=? and GioRa is null;";
        
        DiemDanh dd = null;
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, MaSV);
            ResultSet rs = stat.executeQuery();
            while(rs.next())
            {
                dd = new DiemDanh(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), null,
                    rs.getBoolean(4), rs.getBoolean(5));
            }
            
            conn.close();
        } catch(Exception ex)
        {
//            System.out.println("Không thể thêm diemdanh.");
        }
        
        return dd;
    }
    
    public void insertDD(DiemDanh dd)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "insert into diemdanh values(?, ?, ?, ?, ?);";
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, dd.getMaSV());
            stat.setTimestamp(2, Timestamp.valueOf(dd.getGioVao()));
            stat.setTimestamp(3, null);
            stat.setBoolean(4, dd.isVaoTre());
            stat.setBoolean(5, dd.isVeSom());
            
            stat.executeUpdate();
            
            conn.close();
        } catch(Exception ex)
        {
//            System.out.println("Không thể thêm diemdanh.");
            System.out.println(ex);
        }
    }
    
    public void updateDD(DiemDanh dd)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "update diemdanh set GioRa=?, VeSom=? where MaSV=? and GioVao=?;";
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setTimestamp(1, Timestamp.valueOf(dd.getGioRa()));
            stat.setBoolean(2, dd.isVeSom());
            stat.setString(3, dd.getMaSV());
            stat.setTimestamp(4, Timestamp.valueOf(dd.getGioVao()));
            
            stat.executeUpdate();
            
            conn.close();
        } catch(Exception ex)
        {
//            System.out.println("Không thể thêm diemdanh.");
            System.out.println(ex + " update");
        }
    }
    
//    public int checkSVinDD(String MaSV)
//    {
//        
//    }
    
    public static void main(String[] args)
    {
        
    }
}
