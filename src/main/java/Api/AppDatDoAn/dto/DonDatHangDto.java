package Api.AppDatDoAn.dto;

import Api.AppDatDoAn.entity.SanPham;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Data
public class DonDatHangDto {
    private String madondathang;

    private LocalDate ngaydat;

    private String tinhtrang;

    private Time giohen;

    private String tenkhachhang;
}
