package Api.AppDatDoAn.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class DonDatHangDto {
    private String madondathang;

    private LocalDate ngaydat;

    private String tinhtrang;

    private Time giohen;

    private String tenkhachhang;
}
