package apap.tk.SIRekrutmenH9.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(value={"roleName"},allowGetters = true)
public class PegawaiData {
    @NotNull
    @Size(max = 200)
    private String username;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 200)
    @Column(name = "no_telepon", nullable = false)
    private String noTelepon;

    @NotNull
    @Size(max = 200)
    private String tempatLahir;

    @NotNull
    @Column(name="tanggalLahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalLahir;

    @NotNull
    @Size(max = 200)
    private String alamat;

    @NotNull
    private Long roleId;

    private String roleName;

    // return username
    public String getUsername() {
        return username;
    }
    // set username
    public void setUsername(String username) {
        this.username = username;
    }
    // return nama
    public String getNama() {
        return nama;
    }
    // ser name
    public void setNama(String nama) {
        this.nama = nama;
    }
    // return noTelepon
    public String getNoTelepon() {
        return noTelepon;
    }
    // set noTelepon
    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    // return tempatLahir
    public String getTempatLahir() {
        return tempatLahir;
    }
    // set tempatLahir
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }
    // return tanggalLahir
    public Date getTanggalLahir() {
        return tanggalLahir;
    }
    // set username
    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
    // return alamat
    public String getAlamat() {
        return alamat;
    }
    // set alamat
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    // return id Role
    public Long getRoleId() {
        return roleId;
    }
    // set id Role
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    // return nama Role
    public String getRoleName() {
        return roleName;
    }
    // set nama Role
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}