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
}
