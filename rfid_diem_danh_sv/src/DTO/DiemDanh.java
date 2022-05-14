/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author kieud
 */
public class DiemDanh {
    private String MaSV;
    private String MaLH;
    private LocalDateTime GioVao;
    private LocalDateTime GioRa;
    private boolean VaoTre;
    private boolean VeSom;
    private boolean Vang;

    public DiemDanh(String MaSV, String MaLH, LocalDateTime GioVao, LocalDateTime GioRa, boolean VaoTre, boolean VeSom, boolean Vang) {
        this.MaSV = MaSV;
        this.MaLH = MaLH;
        this.GioVao = GioVao;
        this.GioRa = GioRa;
        this.VaoTre = VaoTre;
        this.VeSom = VeSom;
        this.Vang = Vang;
    }

    public String getMaLH() {
        return MaLH;
    }

    public void setMaLH(String MaLH) {
        this.MaLH = MaLH;
    }
    
    public boolean isVang() {
        return Vang;
    }

    public void setVang(boolean Vang) {
        this.Vang = Vang;
    }
    
    public DiemDanh(String MaSV)
    {
        this.MaSV = MaSV;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public LocalDateTime getGioVao() {
        return GioVao;
    }

    public void setGioVao(LocalDateTime GioVao) {
        this.GioVao = GioVao;
    }

    public LocalDateTime getGioRa() {
        return GioRa;
    }

    public void setGioRa(LocalDateTime GioRa) {
        this.GioRa = GioRa;
    }

    public boolean isVaoTre() {
        return VaoTre;
    }

    public void setVaoTre(boolean VaoTre) {
        this.VaoTre = VaoTre;
    }

    public boolean isVeSom() {
        return VeSom;
    }

    public void setVeSom(boolean VeSom) {
        this.VeSom = VeSom;
    }
    
    
}
