/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HocDAO;
import DTO.Hoc;
import java.util.List;

/**
 *
 * @author kieud
 */
public class HocBUS {
    private HocDAO hDAO = new HocDAO();
    
    public List<Hoc> listHocByLHNotInDD(String MaLH)
    {
        return hDAO.listHocByLHNotInDD(MaLH);
    }
}
