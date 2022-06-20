package com.example.rentalll.Model;

public class Barang {
    private String id;
    private String nama;
    private String alamat;
    private String nomorHp;
    private String lama;

    public Barang(){

    }

    public Barang(String nama, String alamat, String nomorHp, String lama) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorHp = nomorHp;
        this.lama = lama;
        this.id =id;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getLama() {
        return lama;
    }

    public void setLama(String lama) {
        this.lama = lama;
    }
}


