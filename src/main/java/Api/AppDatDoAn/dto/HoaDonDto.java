package Api.AppDatDoAn.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HoaDonDto {
    private String mahoadon;
    private String madondathang;
    private String tenkhachhang;
    private LocalDate ngaylap;
    private Double tongtien;
}
