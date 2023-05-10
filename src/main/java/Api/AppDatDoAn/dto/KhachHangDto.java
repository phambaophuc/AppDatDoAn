package Api.AppDatDoAn.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class KhachHangDto {
    private UUID makhachhang;

    private String tenkhachhang;

    private String diachi;

    private Long sodienthoai;
}
