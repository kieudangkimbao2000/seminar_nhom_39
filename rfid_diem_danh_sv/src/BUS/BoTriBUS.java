/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BoTriDAO;
import DTO.BoTri;
import java.util.List;

/**
 *
 * @author kieud
 */
public class BoTriBUS {
    public BoTriDAO btDAO = new BoTriDAO();
    
    public List<BoTri> getListByPHandNgay(String MaPH)
    {
        return btDAO.getListByPHandNgay(MaPH);
    }
}
