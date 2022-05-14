/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectDB.ConnectDB;
import DTO.Hoc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieud
 */
public class HocDAO {
    ConnectDB connDB = new ConnectDB("rfid_nhom_39", "root", "");
    
    public List<Hoc> listHocByLHNotInDD(String MaLH)
    {
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
        
        String query = "select * from hoc where hoc.MaLH='" + MaLH + "' and hoc.MaSV not in "
                + "(select dd.MaSV from diemdanh dd where dd.MaLH='" + MaLH + "' and dd.GioVao like '"+strNow+"%');";
        
        List<Hoc> lsH = new ArrayList<Hoc>();
        
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next())
            {
                Hoc h = new Hoc(rs.getString(1), rs.getString(2));
                lsH.add(h);
            }
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lsH;
    }
    
    public static void main(String[] args) {
        List<Hoc> ls = new HocDAO().listHocByLHNotInDD("LH2");
        for(Hoc h : ls)
        {
            System.out.println(h.getMaSV());
        }
    }
}
