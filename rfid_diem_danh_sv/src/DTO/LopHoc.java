/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalTime;

/**
 *
 * @author kieud
 */
public class LopHoc {
    private String MaLH;
    private String TenLH;
    private LocalTime gioVao;
    private LocalTime gioRa;

    public LopHoc(String MaLH, String TenLH, LocalTime gioVao, LocalTime gioRa) {
        this.MaLH = MaLH;
        this.TenLH = TenLH;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
    }

    public String getMaLH() {
        return MaLH;
    }

    public void setMaLH(String MaLH) {
        this.MaLH = MaLH;
    }

    public String getTenLH() {
        return TenLH;
    }

    public void setTenLH(String TenLH) {
        this.TenLH = TenLH;
    }

    public LocalTime getGioVao() {
        return gioVao;
    }

    public void setGioVao(LocalTime gioVao) {
        this.gioVao = gioVao;
    }

    public LocalTime getGioRa() {
        return gioRa;
    }

    public void setGioRa(LocalTime gioRa) {
        this.gioRa = gioRa;
    }
    
}
