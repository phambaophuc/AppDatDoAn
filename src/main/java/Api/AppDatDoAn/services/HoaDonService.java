package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.HoaDon;
import Api.AppDatDoAn.reponsitory.IHoaDonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;

    public HoaDon getHoaDonById(String id) {
        return hoaDonRepository.findByMaHoaDon(id);
    }

    public List<HoaDon> getAllHoaDonByMaCH(String macuahang) {
        return hoaDonRepository.findAllHoaDonByMaCH(macuahang);
    }

    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    public void saveHoaDon(HoaDon hoaDon) {
        String maHoaDon = RandomStringUtils.randomAlphanumeric(10);
        hoaDon.setMahoadon(maHoaDon);

        hoaDonRepository.save(hoaDon);
        hoaDonRepository.tinhTongTien(hoaDon.getMahoadon());
    }

}
