/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author kieud
 */
public class BaoCao {
    private String MaSV;
    private String HoTen;
    private int TotalVT;
    private int TotalVS;
    private int TotalV;

    public BaoCao(String MaSV, int TotalVT, int TotalVS, int TotalV) {
        this.MaSV = MaSV;
        this.TotalVT = TotalVT;
        this.TotalVS = TotalVS;
        this.TotalV = TotalV;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public int getTotalVT() {
        return TotalVT;
    }

    public void setTotalVT(int TotalVT) {
        this.TotalVT = TotalVT;
    }

    public int getTotalVS() {
        return TotalVS;
    }

    public void setTotalVS(int TotalVS) {
        this.TotalVS = TotalVS;
    }

    public int getTotalV() {
        return TotalV;
    }

    public void setTotalV(int TotalV) {
        this.TotalV = TotalV;
    }
    
}
