package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.reponsitory.ICuaHangReponsitory;
import org.apache.commons.lang3.StringUtils;
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
        updateMaChByTenCh(cuahang);
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
        updateMaChByTenCh(uCuaHang);
        cuaHangReponsitory.save(uCuaHang);
    }

    public void updateMaChByTenCh(CuaHang cuahang) {
        String tencuahang = cuahang.getTencuahang();
        String maCuaHang = StringUtils.stripAccents(tencuahang)
                .toLowerCase()
                .replace("đ", "d")
                .replaceAll("[^a-zA-Z0-9-]+", "-");

        cuahang.setMacuahang(maCuaHang);
    }

    public void removeCuaHang(String id) {
        cuaHangReponsitory.deleteById(id);
    }
}
