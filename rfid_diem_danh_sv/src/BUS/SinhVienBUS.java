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
    static SinhVienDAO svDAO;
    
    public List<SinhVien> GetAll()
    {
        svDAO = new SinhVienDAO();
        return svDAO.GetAll();
    }
    
    public static void main(String[] args)
    {
        svDAO = new SinhVienDAO();
        List<SinhVien> listSV =  svDAO.GetAll();
        listSV.forEach(sv -> {
            System.out.println(sv.getNgSinh());
        });
    }
}
