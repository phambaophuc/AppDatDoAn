package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.reponsitory.IDonDatHangReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class DonDatHangService {

    @Autowired
    private IDonDatHangReponsitory donDatHangReponsitory;

    public DonDatHang getDDHById(String id) {
        return donDatHangReponsitory.findByDDHById(id);
    }

    public List<Object[]> LayThongTinDatHangTheoKhachHang(String id) {
        return donDatHangReponsitory.LayThongTinDatHangTheoKhachHang(id);
    }

    public List<DonDatHang> getAllDDH() {
        return donDatHangReponsitory.findAll();
    }

    public DonDatHang saveDDH(DonDatHang donDatHang) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        String maHoaDon = "DDH" + formattedDateTime;
        donDatHang.setMadondathang(maHoaDon);

        return donDatHangReponsitory.save(donDatHang);
    }
}
