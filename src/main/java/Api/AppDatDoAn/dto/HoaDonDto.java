package Api.AppDatDoAn.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HoaDonDto {
    private String mahoadon;

    private Date ngaylap;

    private Double tongtien;

    private String tenkhachhang;
}
