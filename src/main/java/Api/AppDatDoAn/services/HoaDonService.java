package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.HoaDon;
import Api.AppDatDoAn.reponsitory.IHoaDonReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public HoaDon saveHoaDon(HoaDon hoaDon) {
        return hoaDonReponsitory.save(hoaDon);
    }
}
