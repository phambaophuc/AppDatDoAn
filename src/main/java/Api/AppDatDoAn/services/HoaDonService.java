package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.HoaDon;
import Api.AppDatDoAn.reponsitory.IHoaDonReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    private IHoaDonReponsitory hoaDonReponsitory;

    public HoaDon getHoaDonById(String id) {
        return hoaDonReponsitory.findByMaHoaDon(id);
    }

    public List<HoaDon> getAllHoaDon() {
        return hoaDonReponsitory.findAll();
    }

    public void saveHoaDon(HoaDon hoaDon) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);

        String maHoaDon = "HD" + formattedDateTime;
        hoaDon.setMahoadon(maHoaDon);

        hoaDonReponsitory.save(hoaDon);
        hoaDonReponsitory.tinhTongTien(hoaDon.getMahoadon());
    }
}
