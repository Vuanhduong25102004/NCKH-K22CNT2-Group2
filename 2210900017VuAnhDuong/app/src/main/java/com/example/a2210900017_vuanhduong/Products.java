package com.example.a2210900017_vuanhduong;

import java.io.Serializable;

public class Products implements Serializable {
    private int masp;
    private String tensp;
    private int soluong;
    private double dongia;

    public Products() {
    }

    public Products(int masp, String tensp, int soluong, double dongia) {
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public double getThanhTien() {
        double thanhTien = soluong * dongia;
        if (soluong > 10) {
            thanhTien *= 0.9; // Giảm 10% nếu số lượng > 10
        }
        return thanhTien;
    }
}
