package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.reponsitory.ICuaHangReponsitory;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuaHangService {
    @Autowired
    private ICuaHangReponsitory cuaHangReponsitory;

    public List<CuaHang> getAllCuaHang() {
        return cuaHangReponsitory.findAll();
    }

    public CuaHang getCuaHangById(String maCuaHang) {
        return cuaHangReponsitory.findByMaCuaHang(maCuaHang);
    }

    public CuaHang saveCuaHang(CuaHang cuahang) {
        String macuahang = RandomStringUtils.randomAlphanumeric(10);
        cuahang.setMacuahang(macuahang);
        cuahang.setLuotdanhgia(0L);
        return cuaHangReponsitory.save(cuahang);
    }

    public void updateCuaHang(String id, CuaHang cuahang) {
        CuaHang uCuaHang = cuaHangReponsitory.findByMaCuaHang(id);
        uCuaHang.setTencuahang(cuahang.getTencuahang());
        uCuaHang.setHinhanh(cuahang.getHinhanh());
        uCuaHang.setDiachi(cuahang.getDiachi());
        uCuaHang.setSodienthoai(cuahang.getSodienthoai());
        uCuaHang.setLuotdanhgia(cuahang.getLuotdanhgia());
        uCuaHang.setChatluong(cuahang.getChatluong());
        uCuaHang.setGiomocua(cuahang.getGiomocua());
        uCuaHang.setGiodongcua(cuahang.getGiodongcua());
        uCuaHang.setTinhtrang(cuahang.getTinhtrang());
        cuaHangReponsitory.save(uCuaHang);
    }

    public void removeCuaHang(String id) {
        cuaHangReponsitory.deleteById(id);
    }
}
