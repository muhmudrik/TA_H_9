package apap.tk.SIRekrutmenH9.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pelamar")
public class PelamarModel implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid_user", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    UserModel uuidUser;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelamar")
    Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nama")
    String nama;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_telepon")
    String noTelepon;

    @NotNull
    @Size(max = 20)
    @Column(name = "tempat_lahir")
    String tempatLahir;

    @NotNull
    @Column(name = "tanggal_lahir")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date tanggalLahir;

    @NotNull
    @Size(max = 100)
    @Column(name = "alamat")
    String alamat;

    public UserModel getUuidUser() {
        return this.uuidUser;
    }

    public void setUuidUser(UserModel uuidUser) {
        this.uuidUser = uuidUser;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return this.noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
