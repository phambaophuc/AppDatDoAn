package Api.AppDatDoAn.dto;

import Api.AppDatDoAn.entity.ChiTietDonDatHang;
import Api.AppDatDoAn.entity.KhachHang;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import java.util.UUID;

@Data
public class DonDatHangDto {
    private UUID madondathang;

    private Date ngaydat;

    private String tinhtrang;

    private Time giohen;

    private String tenkhachhang;
}
