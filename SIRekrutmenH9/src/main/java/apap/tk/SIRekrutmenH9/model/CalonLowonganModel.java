package apap.tk.SIRekrutmenH9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "CalonLowongan")
public class CalonLowonganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "divisi", nullable = false)
    private String divisi;

    @NotNull
    @Size(max = 20)
    @Column(name = "posisi", nullable = false)
    private String posisi;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @NotNull
    @Size(max = 20)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Column(name = "jenis_lowongan", nullable = false)
    private Integer jenisLowongan;

    public Integer getJenisLowongan() {
        return jenisLowongan;
    }

    public void setJenisLowongan(Integer jenisLowongan) {
        this.jenisLowongan = jenisLowongan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}