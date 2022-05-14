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
public class BoTri {
    private String MaLH;
    private String MaPH;
    private String MaTH;
    private LocalDate Ngay;

    public BoTri(String MaLH, String MaPH, String MaTH, LocalDate Ngay) {
        this.MaLH = MaLH;
        this.MaPH = MaPH;
        this.MaTH = MaTH;
        this.Ngay = Ngay;
    }

    public String getMaLH() {
        return MaLH;
    }

    public void setMaLH(String MaLH) {
        this.MaLH = MaLH;
    }

    public String getMaPH() {
        return MaPH;
    }

    public void setMaPH(String MaPH) {
        this.MaPH = MaPH;
    }

    public String getMaTH() {
        return MaTH;
    }

    public void setMaTH(String MaTH) {
        this.MaTH = MaTH;
    }

    public LocalDate getNgay() {
        return Ngay;
    }

    public void setNgay(LocalDate Ngay) {
        this.Ngay = Ngay;
    }
    
}
