package apap.tk.SIRekrutmenH9.rest;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PelatihanDetail {
    @NotNull
    @Size(max = 50)
    private String nama_pelatihan;

    @NotNull
    @Size(max = 200)
    private String deskripsi;

    @NotNull
    private Integer kapasitas;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    private Date waktu_mulai;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    private Date waktu_selesai;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_mulai;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggal_selesai;


    public Date getTanggal_mulai() {
        return this.tanggal_mulai;
    }

    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public Date getTanggal_selesai() {
        return this.tanggal_selesai;
    }

    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public Date getWaktu_mulai() {
        return this.waktu_mulai;
    }

    public void setWaktu_mulai(Date waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public Date getWaktu_selesai() {
        return this.waktu_selesai;
    }

    public void setWaktu_selesai(Date waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public String getNama_pelatihan() {
        return this.nama_pelatihan;
    }

    public void setNama_pelatihan(String nama_pelatihan) {
        this.nama_pelatihan = nama_pelatihan;
    }

    public String getDeskripsi() {
        return this.deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getKapasitas() {
        return this.kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }
}