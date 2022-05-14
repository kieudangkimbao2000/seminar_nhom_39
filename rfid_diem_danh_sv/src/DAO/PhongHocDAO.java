/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.PhongHoc;
import DTO.SinhVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieud
 */
public class PhongHocDAO {
    ConnectDB connDB;
    
    public List<PhongHoc> GetAll()
    {
        connDB = new ConnectDB("rfid_nhom_39", "root", "");
        Connection conn = connDB.getConnection();
        String query = "select * from phonghoc";
        List<PhongHoc> listPH = new ArrayList<PhongHoc>();
        Statement stat;
        try {
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                 PhongHoc ph = new PhongHoc(rs.getString(1));
                listPH.add(ph);
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PhongHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listPH;
    }
}
