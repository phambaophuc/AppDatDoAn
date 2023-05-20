package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @Column(name = "mahoadon", length = 10, unique = true)
    private String mahoadon;

    @Column(name = "ngaylap")
    private Date ngaylap;

    @Column(name = "tongtien")
    private Double tongtien;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dondathang_id", referencedColumnName = "madondathang")
    @NotNull(message = "Vui lòng nhập mã đơn đặt hàng")
    private DonDatHang donDatHang;

    @PrePersist
    public void preNgayLap() {
        this.ngaylap = new Date();
    }

}
