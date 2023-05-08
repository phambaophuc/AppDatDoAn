package Api.AppDatDoAn.apicontroller;

import Api.AppDatDoAn.dto.SanPhamDto;
import Api.AppDatDoAn.entity.Sanpham;
import Api.AppDatDoAn.services.CuaHangService;
import Api.AppDatDoAn.services.LoaiSanPhamService;
import Api.AppDatDoAn.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private SanPhamDto converttoDto(Sanpham sanpham) {
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
        List<Sanpham> sanphams = sanPhamService.getAllSanPham();
        List<SanPhamDto> sanPhamDtos = new ArrayList<>();
        for (Sanpham sanpham : sanphams) {
            sanPhamDtos.add(converttoDto(sanpham));
        }
        return sanPhamDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Sanpham sanpham = sanPhamService.getSanPhamById(id);
        if (sanpham == null) {
            return ResponseEntity.badRequest().body("Sản phẩm không tồn tại.");
        }
        SanPhamDto sanPhamDto = converttoDto(sanpham);
        return ResponseEntity.status(200).body(sanPhamDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addSanPham(@RequestBody Sanpham sanpham, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        } else {
            sanPhamService.saveSanPham(sanpham);
            return ResponseEntity.ok(sanpham);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editSanPham(@PathVariable Long id, @RequestBody Sanpham sanpham) {
        Optional<Sanpham> sanphamOptional = Optional.ofNullable(sanPhamService.getSanPhamById(id));

        if (!sanphamOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Sản phẩm không tồn tại.");
        }

        Sanpham eSanpham = sanphamOptional.get();
        eSanpham.setTensanpham(sanpham.getTensanpham());
        eSanpham.setMota(sanpham.getMota());
        eSanpham.setGia(sanpham.getGia());
        eSanpham.setTinhtrang(sanpham.getTinhtrang());
        eSanpham.setGhichu(sanpham.getGhichu());
        eSanpham.setLoaisanpham(sanpham.getLoaisanpham());
        eSanpham.setCuahang(sanpham.getCuahang());

        sanPhamService.saveSanPham(eSanpham);
        return ResponseEntity.ok("Đã cập nhật.");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteSanPham(@PathVariable Long id) {
        if (sanPhamService.getSanPhamById(id) != null) {
            sanPhamService.removeSanPham(id);
            return ResponseEntity.ok("Đã xóa.");
        } else {
            return ResponseEntity.badRequest().body("Sản phẩm không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> timKiemSanPhamTheoTen(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "theloai", required = false) Long theloai) {
        if (name != null && theloai == null) {
            List<Sanpham> sanphams = sanPhamService.timKiemTheoTen(name);
            List<SanPhamDto> sanPhamDtos = new ArrayList<>();
            for (Sanpham sanpham : sanphams) {
                sanPhamDtos.add(converttoDto(sanpham));
            }
            return ResponseEntity.ok(sanPhamDtos);
        } else if(theloai != null && name == null) {
            List<Sanpham> sanphams = sanPhamService.timKiemTheoTheLoai(theloai);
            List<SanPhamDto> sanPhamDtos = new ArrayList<>();
            for (Sanpham sanpham : sanphams) {
                sanPhamDtos.add(converttoDto(sanpham));
            }
            return ResponseEntity.ok(sanPhamDtos);
        } else {
            List<Sanpham> sanphams = sanPhamService.timKiemTheoTenVaTheLoai(name, theloai);
            List<SanPhamDto> sanPhamDtos = new ArrayList<>();
            for (Sanpham sanpham : sanphams) {
                sanPhamDtos.add(converttoDto(sanpham));
            }
            return ResponseEntity.ok(sanPhamDtos);
        }
    }

}
