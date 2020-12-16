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
@Table(name = "lowongan")
public class LowonganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;


    @NotNull
    @Size(max = 20)
    @Column(name = "kode_lowongan", nullable = false, unique = true)
    private String kodeLowongan;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_user", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jenis_lowongan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisLowonganModel jenisLowongan;

    @OneToMany(mappedBy = "lowonganModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LamaranModel> listLamaran;

    public void setJenisLowongan(JenisLowonganModel jenisLowongan) {
        this.jenisLowongan = jenisLowongan;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public void setKodeLowongan(String kodeLowongan) {
        this.kodeLowongan = kodeLowongan;
    }

    public void setId_lowongan(Long id_lowongan) {
        this.id = id_lowongan;
    }

    public JenisLowonganModel getJenisLowongan() {
        return jenisLowongan;
    }

    public UserModel getUser() {
        return user;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public String getPosisi() {
        return posisi;
    }

    public String getDivisi() {
        return divisi;
    }

    public String getKodeLowongan() {
        return kodeLowongan;
    }

    public Long getId_lowongan() {
        return id;
    }

    public List<LamaranModel> getListLamaran() {
        return listLamaran;
    }
}