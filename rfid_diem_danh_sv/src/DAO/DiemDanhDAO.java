/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.BaoCao;
import DTO.DiemDanh;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieud
 */
public class DiemDanhDAO {
    ConnectDB connDB = new ConnectDB("rfid_nhom_39", "root", "");
    
    public List<DiemDanh> GetAll()
    {
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
                    dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), null,
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                } else
                {
                    dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                }
                
                lsDD.add(dd);
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
            System.out.println(ex);
        }
        
        return lsDD;
    }
    
    public List<DiemDanh> GetAllDDonDate(String MaLH)
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
        String strNow = date.getYear() + "-" + month + "-" + day;
        
        String query = "select * from diemdanh where MaLH='" + MaLH + "' and GioVao like '"+strNow+"%'";
        List<DiemDanh> lsDD = new ArrayList<DiemDanh>();
        
        try
        {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                DiemDanh dd;
                if(rs.getTimestamp(4) == null)
                {
                    dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), null,
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                } else
                {
                    dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                }
                
                lsDD.add(dd);
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
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
                    dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), null,
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                } else
                {
                    dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                }
                
                lsDD.add(dd);
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không lấy được dữ liệu diemdanh.");
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
                dd = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), null,
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không thể thêm diemdanh.");
        }
        
        return dd;
    }
    
    public void insertDD(DiemDanh dd)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "insert into diemdanh values(?, ?, ?, ?, ?, ?, ?);";
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, dd.getMaSV());
            stat.setString(2, dd.getMaLH());
            stat.setTimestamp(3, Timestamp.valueOf(dd.getGioVao()));
            stat.setTimestamp(4, null);
            stat.setBoolean(5, dd.isVaoTre());
            stat.setBoolean(6, dd.isVeSom());
            stat.setBoolean(7, dd.isVang());
            
            stat.executeUpdate();
            
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không thể thêm diemdanh.");
        }
    }
    
    public void updateDD(DiemDanh dd)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "update diemdanh set GioRa=?, VeSom=? where MaSV=? and MaLH=? and GioVao=?;";
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setTimestamp(1, Timestamp.valueOf(dd.getGioRa()));
            stat.setBoolean(2, dd.isVeSom());
            stat.setString(3, dd.getMaSV());
            stat.setString(4, dd.getMaLH());
            stat.setTimestamp(5, Timestamp.valueOf(dd.getGioVao()));
            
            stat.executeUpdate();
            
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không thể thêm diemdanh.");
        }
    }
    
    public void updateDDVang(DiemDanh dd)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "update diemdanh set VaoTre=?, Vang=? where MaSV=? and MaLH=? and GioVao=?;";
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setBoolean(1, dd.isVaoTre());
            stat.setBoolean(2, dd.isVang());
            stat.setString(3, dd.getMaSV());
            stat.setString(4, dd.getMaLH());
            stat.setTimestamp(5, Timestamp.valueOf(dd.getGioVao()));
            
            stat.executeUpdate();
            
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không thể thêm diemdanh.");
        }
    }
    
    public List<BaoCao> BaoCaoDD(String MaLH, LocalDate begin, LocalDate end)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "select MaSV, sum(VaoTre), sum(VeSom), sum(Vang) from diemdanh where MaLH=? and GioVao between ? and ? group by MaSV;";
        List<BaoCao> lsBC = new ArrayList<BaoCao>();
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, MaLH);
            stat.setDate(2, Date.valueOf(begin));
            stat.setDate(3, Date.valueOf(end));
            
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                BaoCao bc = new BaoCao(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                lsBC.add(bc);
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không thể lấy dữ liệu diemdanh.");
        }
        
        return lsBC;
    }
    
    public List<DiemDanh> ChiTietBaoCaoDD(String MaSV, String MaLH, LocalDate begin, LocalDate end)
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        
        String query = "select * from diemdanh where MaSV=? and MaLH=? and GioVao between ? and ?;";
        List<DiemDanh> lsDD = new ArrayList<DiemDanh>();
        
        try
        {
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, MaSV);
            stat.setString(2, MaLH);
            stat.setDate(3, Date.valueOf(begin));
            stat.setDate(4, Date.valueOf(end));
            
            ResultSet rs = stat.executeQuery();
            
            while(rs.next())
            {
                DiemDanh bc = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), null,
                    rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                
                if(rs.getTimestamp(4) != null)
                {
                    bc = new DiemDanh(rs.getString(1), rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getTimestamp(4).toLocalDateTime(),
                        rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));
                }
                
                lsDD.add(bc);
            }
            stat.close();
            conn.close();
        } catch(Exception ex)
        {
            System.out.println("Không thể lấy dữ liệu diemdanh.");
        }
        
        return lsDD;
    }
    
//    public int checkSVinDD(String MaSV)
//    {
//        
//    }

}
