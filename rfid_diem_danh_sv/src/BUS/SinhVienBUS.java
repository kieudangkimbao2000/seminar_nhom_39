/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SinhVienDAO;
import DTO.SinhVien;
import java.util.List;

/**
 *
 * @author kieud
 */
public class SinhVienBUS {
    static SinhVienDAO svDAO = new SinhVienDAO();
    
    public List<SinhVien> GetAll()
    {
        return svDAO.GetAll();
    }
    
    public SinhVien GetByID(String id)
    {
        return svDAO.GetByID(id);
    }
    
    public List<SinhVien> getSVNotInDD()
    {
        return svDAO.getSVNotInDD();
    }
    
    public boolean importExcel(String path)
    {
        path = path.replace("\\", "/");
        return svDAO.importExcel(path);
    }
    
    public static void main(String[] args)
    {
//        svDAO = new SinhVienDAO();
//        List<SinhVien> listSV =  svDAO.GetAll();
//        listSV.forEach(sv -> {
//            System.out.println(sv.getMaSV());
//        });
        
        SinhVien sv = new SinhVienBUS().GetByID("SV1");
        System.out.println(sv.getHoTen());
    }
    
}
