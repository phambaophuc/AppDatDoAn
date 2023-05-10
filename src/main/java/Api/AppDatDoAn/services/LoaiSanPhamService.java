package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.LoaiSanPham;
import Api.AppDatDoAn.reponsitory.ILoaiSanPhamReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSanPhamService {
    @Autowired
    private ILoaiSanPhamReponsitory loaiSanPhamReponsitory;

    public List<LoaiSanPham> getAll() {
        return loaiSanPhamReponsitory.findAll();
    }

    public LoaiSanPham getById(Long id) {
        Optional<LoaiSanPham> optional = loaiSanPhamReponsitory.findById(id);
        return optional.orElse(null);
    }

    public LoaiSanPham saveLoaiSanPham(LoaiSanPham loaisanpham) {
        return loaiSanPhamReponsitory.save(loaisanpham);
    }

    public void removeLoaiSanPham(Long id) {
        loaiSanPhamReponsitory.deleteById(id);
    }
}
