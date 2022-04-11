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
    private LocalDateTime GioVao;
    private LocalDateTime GioRa;
    private boolean VaoTre;
    private boolean VeSom;

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
