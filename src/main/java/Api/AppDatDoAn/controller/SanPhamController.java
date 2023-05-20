package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.dto.SanPhamDto;
import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.services.CuaHangService;
import Api.AppDatDoAn.services.LoaiSanPhamService;
import Api.AppDatDoAn.services.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @Autowired
    private CuaHangService cuaHangService;

    private SanPhamDto converttoDto(SanPham sanpham) {
        SanPhamDto sanPhamDto = new SanPhamDto();
        sanPhamDto.setMasanpham(sanpham.getMasanpham());
        sanPhamDto.setTensanpham(sanpham.getTensanpham());
        sanPhamDto.setMota(sanpham.getMota());
        sanPhamDto.setGia(sanpham.getGia());
        sanPhamDto.setTinhtrang(sanpham.getTinhtrang());
        sanPhamDto.setGhichu(sanpham.getGhichu());
        sanPhamDto.setTenloai(loaiSanPhamService.getById(sanpham.getLoaisanpham().getMaloai()).getTenloai());
        sanPhamDto.setTencuahang(cuaHangService.getCuaHangById(sanpham.getCuahang().getMacuahang()).getTencuahang());
        return sanPhamDto;
    }

    @GetMapping
    @ResponseBody
    public List<SanPhamDto> getAllSanPham() {
        List<SanPham> sanphams = sanPhamService.getAllSanPham();
        List<SanPhamDto> sanPhamDtos = new ArrayList<>();
        for (SanPham sanpham : sanphams) {
            sanPhamDtos.add(converttoDto(sanpham));
        }
        return sanPhamDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable String id) {
        SanPham sanpham = sanPhamService.getSanPhamById(id);
        if (sanpham == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm có mã là: " + id);
        }
        SanPhamDto sanPhamDto = converttoDto(sanpham);
        return ResponseEntity.status(200).body(sanPhamDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addSanPham(@Valid @RequestBody SanPham sanpham) {
        sanPhamService.saveSanPham(sanpham);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm sản phẩm thành công!");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editSanPham(@PathVariable String id, @RequestBody SanPham sanpham) {
        Optional<SanPham> sanphamOptional = Optional.ofNullable(sanPhamService.getSanPhamById(id));

        if (!sanphamOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm có mã là: " + id);
        }

        SanPham eSanpham = sanphamOptional.get();
        eSanpham.setTensanpham(sanpham.getTensanpham());
        eSanpham.setMota(sanpham.getMota());
        eSanpham.setGia(sanpham.getGia());
        eSanpham.setTinhtrang(sanpham.getTinhtrang());
        eSanpham.setGhichu(sanpham.getGhichu());
        eSanpham.setLoaisanpham(sanpham.getLoaisanpham());
        eSanpham.setCuahang(sanpham.getCuahang());

        sanPhamService.updateSanPham(eSanpham);
        return ResponseEntity.ok("Đã cập nhật thông tin sản phẩm.");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteSanPham(@PathVariable String id) {
        if (sanPhamService.getSanPhamById(id) != null) {
            sanPhamService.removeSanPham(id);
            return ResponseEntity.ok("Đã xóa sản phẩm.");
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm có mã là: " + id);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> timKiemSanPham(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "theloai", required = false) Long theloai) {
        if (name != null && theloai == null) {
            List<SanPham> sanphams = sanPhamService.timKiemTheoTen(name);
            List<SanPhamDto> sanPhamDtos = new ArrayList<>();
            for (SanPham sanpham : sanphams) {
                sanPhamDtos.add(converttoDto(sanpham));
            }
            return ResponseEntity.ok(sanPhamDtos);
        } else if(theloai != null && name == null) {
            List<SanPham> sanphams = sanPhamService.timKiemTheoTheLoai(theloai);
            List<SanPhamDto> sanPhamDtos = new ArrayList<>();
            for (SanPham sanpham : sanphams) {
                sanPhamDtos.add(converttoDto(sanpham));
            }
            return ResponseEntity.ok(sanPhamDtos);
        } else {
            List<SanPham> sanphams = sanPhamService.timKiemTheoTenVaTheLoai(name, theloai);
            List<SanPhamDto> sanPhamDtos = new ArrayList<>();
            for (SanPham sanpham : sanphams) {
                sanPhamDtos.add(converttoDto(sanpham));
            }
            return ResponseEntity.ok(sanPhamDtos);
        }
    }

}
