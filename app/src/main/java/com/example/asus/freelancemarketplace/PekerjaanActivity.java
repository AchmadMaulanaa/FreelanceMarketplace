package com.example.asus.freelancemarketplace;

import java.io.Serializable;
import com.google.firebase.database.IgnoreExtraProperties;

public class PekerjaanActivity implements Serializable {
    private String pekerjaan;
    private String nama;
    private String alamat;
    private String gaji;
    private String deskripsi;
    private String key;


    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
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

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString(){
        return " "+pekerjaan+"\n" +
                " "+nama +"\n" +
                " "+alamat +"\n" +
                " "+gaji +"\n" +
                " "+deskripsi;
    }

    public PekerjaanActivity(String pkj, String nm, String alm, String gj, String desk){
        pekerjaan = pkj;
        nama = nm;
        alamat = alm;
        gaji = gj;
        deskripsi = desk;

    }
}
