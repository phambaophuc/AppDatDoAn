package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
@Entity
@Table(name = "dondathang")
public class Dondathang {
    @Id
    private UUID madondathang;

    @Column(name = "ngaydat")
    private Time ngaydat;

    @Column(name = "tinhtrang")
    private String tinhtrang;

    @Column(name = "giohen")
    private Date giohen;

    @ManyToOne
    @JoinColumn(name = "khachhang_id")
    private Khachhang khachhang;

}
