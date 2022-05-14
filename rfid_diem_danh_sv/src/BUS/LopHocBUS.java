/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LopHocDAO;
import DTO.LopHoc;
import java.util.List;

/**
 *
 * @author kieud
 */
public class LopHocBUS {
    private LopHocDAO lhDAO = new LopHocDAO();
    
    public LopHoc getLHByRoomandID(String MaPH, String MaLH)
    {
        return lhDAO.getLHByRoomandID(MaPH, MaLH);
    }
    
    public LopHoc getLHByID(String MaLH)
    {
        return lhDAO.getLHByID(MaLH);
    }
}
