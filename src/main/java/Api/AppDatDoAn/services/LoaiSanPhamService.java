package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.Loaisanpham;
import Api.AppDatDoAn.reponsitory.ILoaiSanPhamReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSanPhamService {
    @Autowired
    private ILoaiSanPhamReponsitory loaiSanPhamReponsitory;

    public List<Loaisanpham> getAll() {
        return loaiSanPhamReponsitory.findAll();
    }

    public Loaisanpham getById(Long id) {
        Optional<Loaisanpham> optional = loaiSanPhamReponsitory.findById(id);
        return optional.orElse(null);
    }

    public Loaisanpham saveLoaiSanPham(Loaisanpham loaisanpham) {
        return loaiSanPhamReponsitory.save(loaisanpham);
    }

    public void removeLoaiSanPham(Long id) {
        loaiSanPhamReponsitory.deleteById(id);
    }
}
