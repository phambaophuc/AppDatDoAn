package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "dondathang")
public class DonDatHang {
    @Id
    @GeneratedValue
    private UUID madondathang;

    @Column(name = "ngaydat")
    private Date ngaydat;

    @Column(name = "tinhtrang")
    private String tinhtrang;

    @Column(name = "giohen")
    private Time giohen;

    @ManyToOne
    @JoinColumn(name = "khachhang_id")
    private KhachHang khachhang;

    @OneToMany(mappedBy = "dondathang")
    private Set<ChiTietDonDatHang> chiTietDonDatHangs;
}
