/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DiemDanhDAO;
import DTO.BaoCao;
import DTO.DiemDanh;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

/**
 *
 * @author kieud
 */
public class DiemDanhBUS {

    DiemDanhDAO ddDAO = new DiemDanhDAO();
    public String MaLH;
    public int tgVT;
    public int tgVS;
    public boolean checkOut = false;
    public LocalTime gioVao;
    public LocalTime gioRa;
    public List<DiemDanh> listDD;

    public List<DiemDanh> GetAll() {
        return ddDAO.GetAll();
    }

    public List<DiemDanh> GetAllDDonDate() {
        return ddDAO.GetAllDDonDate(MaLH);
    }

    public List<DiemDanh> getListDDByID(String MaSV) {
        return ddDAO.getListDDByID(MaSV);
    }

    public boolean insertDD(String MaSV) {
        DiemDanh dd;
        if (listDD == null) {
            if (!checkOut) {
                LocalDateTime now = LocalDateTime.now();
                if (gioVao.compareTo(now.toLocalTime().minusMinutes(tgVT)) < 0) {
                    dd = new DiemDanh(MaSV, MaLH, now, null, true, false, false);
                    ddDAO.insertDD(dd);
                    listDD.add(dd);
                    return true;
                } else {
                    dd = new DiemDanh(MaSV, MaLH, now, null, false, false, false);
                    ddDAO.insertDD(dd);
                    listDD.add(dd);
                    return true;
                }
            }
        } else {
            for (DiemDanh ddCheck : listDD) {
                if (ddCheck.getMaSV().equals(MaSV) && ddCheck.getGioRa() == null && !ddCheck.isVang()) {
                    LocalDateTime now = LocalDateTime.now();
                    if (checkOut) {
                        ddCheck.setGioRa(now);
                        if (gioRa.compareTo(now.toLocalTime().plusMinutes(tgVS)) > 0) {
                            ddCheck.setVeSom(true);
                        }
                        ddDAO.updateDD(ddCheck);

                        return true;
                    } else {
                        return false;
                    }
                }
                if (ddCheck.getMaSV().equals(MaSV) && (ddCheck.getGioRa() != null || ddCheck.isVang())) {
                    return false;
                }
            }
        }
        if (!checkOut) {
            LocalDateTime now = LocalDateTime.now();
            if (gioVao.compareTo(now.toLocalTime().minusMinutes(tgVT)) < 0) {
                dd = new DiemDanh(MaSV, MaLH, now, null, true, false, false);
                ddDAO.insertDD(dd);
                listDD.add(dd);
                return true;
            } else {
                dd = new DiemDanh(MaSV, MaLH, now, null, false, false, false);
                ddDAO.insertDD(dd);
                listDD.add(dd);
                return true;
            }
        }
        return false;
    }
    
    public void updateDDVang()
    {
        for(DiemDanh dd : listDD)
        {
            if(dd.getGioRa() == null)
            {
                dd.setVaoTre(false);
                dd.setVeSom(false);
                dd.setVang(true);
                ddDAO.updateDDVang(dd);
            }
        }
    }

    public void insertDDVang(String MaSV) {
        DiemDanh dd = new DiemDanh(MaSV, MaLH, LocalDateTime.now(), null, false, false, true);
        ddDAO.insertDD(dd);
    }

    public List<BaoCao> BaoCaoDD(LocalDate begin, LocalDate end) {
        return ddDAO.BaoCaoDD(MaLH, begin, end);
    }

    public List<DiemDanh> ChiTietBaoCaoDD(String MaSV, LocalDate begin, LocalDate end) {
        return ddDAO.ChiTietBaoCaoDD(MaSV, MaLH, begin, end);
    }
}
