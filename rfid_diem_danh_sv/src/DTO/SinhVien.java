/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author kieud
 */
public class SinhVien {
    private String MaSV;
    private String HoTen;
    private String GT;
    private LocalDate NgSinh;
    private String SDT;
    private String DChi;

    public SinhVien(String MaSV, String HoTen, String GT, LocalDate NgSinh, String SDT, String DChi) {
        this.MaSV = MaSV;
        this.HoTen = HoTen;
        this.GT = GT;
        this.NgSinh = NgSinh;
        this.SDT = SDT;
        this.DChi = DChi;
    }

    public SinhVien(String MaSV, String HoTen) {
        this.MaSV = MaSV;
        this.HoTen = HoTen;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public LocalDate getNgSinh() {
        return NgSinh;
    }

    public void setNgSinh(LocalDate NgSinh) {
        this.NgSinh = NgSinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDChi() {
        return DChi;
    }

    public void setDChi(String DChi) {
        this.DChi = DChi;
    }
    
}
