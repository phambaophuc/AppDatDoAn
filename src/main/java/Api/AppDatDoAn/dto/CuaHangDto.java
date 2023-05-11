package Api.AppDatDoAn.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class CuaHangDto {
    private String macuahang;

    private String tencuahang;

    private String hinhanh;

    private String diachi;

    private String sodienthoai;

    private Long luotdanhgia;

    private int chatluong;

    private Date giomocua;

    private Date giodongcua;

    private String tinhtrang;
}
