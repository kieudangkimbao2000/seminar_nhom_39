/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhongHocDAO;
import DTO.PhongHoc;
import java.util.List;

/**
 *
 * @author kieud
 */
public class PhongHocBUS {
    private PhongHocDAO phDAO = new PhongHocDAO();
    
    public List<PhongHoc> GetAll()
    {
        return phDAO.GetAll();
    }
}
