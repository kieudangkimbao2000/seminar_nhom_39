/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DiemDanhDAO;
import DTO.DiemDanh;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author kieud
 */
public class DiemDanhBUS {
    DiemDanhDAO ddDAO = new DiemDanhDAO();
    public LocalTime gioVao;
    public LocalTime gioRa;
    
    public List<DiemDanh> GetAll()
    {
        return ddDAO.GetAll();
    }
    
     public List<DiemDanh> GetAllDDonDate()
    {
        return ddDAO.GetAllDDonDate();
    }
    
    public void insertDD(String MaSV)
    {
        DiemDanh dd = ddDAO.getDDVaoNull(MaSV);
        if(dd == null)
        {
            LocalDateTime now = LocalDateTime.now();
            if(gioVao.compareTo(now.toLocalTime()) < 0){
                dd = new DiemDanh(MaSV, now, null, true, false);
                ddDAO.insertDD(dd);
            } else {
                dd = new DiemDanh(MaSV, now, null, false, false);
                ddDAO.insertDD(dd);
            }
        } else{
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Gio ra BUS: "+gioRa);
            System.out.println("Gio ra hien tai: "+now.toLocalTime());
            System.out.println(gioRa.compareTo(now.toLocalTime()));
            if(gioRa.compareTo(now.toLocalTime()) > 0){
                dd.setGioRa(now);
                dd.setVeSom(true);
                ddDAO.updateDD(dd);
            } else {
                dd.setGioRa(now);
                ddDAO.updateDD(dd);
            }
        }
    }
    
    public static void main(String[] args) {
        DiemDanhBUS ddBUS = new DiemDanhBUS();
        ddBUS.gioVao = LocalTime.of(10, 30);
        ddBUS.gioRa = LocalTime.of(10, 50);
        ddBUS.insertDD("SV2");
    }
}
