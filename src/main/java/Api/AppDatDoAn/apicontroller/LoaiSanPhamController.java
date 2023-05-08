package Api.AppDatDoAn.apicontroller;

import Api.AppDatDoAn.dto.LoaiSanPhamDto;
import Api.AppDatDoAn.entity.Loaisanpham;
import Api.AppDatDoAn.services.LoaiSanPhamService;
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
@RequestMapping("/api/v1/loaisanpham")
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    private LoaiSanPhamDto converttoDto(Loaisanpham loaisanpham) {
        LoaiSanPhamDto sanPhamDto = new LoaiSanPhamDto();
        sanPhamDto.setMaloai(loaisanpham.getMaloai());
        sanPhamDto.setTenloai(loaisanpham.getTenloai());
        return sanPhamDto;
    }

    @GetMapping
    @ResponseBody
    public List<LoaiSanPhamDto> getAllLoaiSanPham() {
        List<Loaisanpham> loaisanphams = loaiSanPhamService.getAll();
        List<LoaiSanPhamDto> loaiSanPhamDtos = new ArrayList<>();
        for (Loaisanpham loaisanpham : loaisanphams) {
            loaiSanPhamDtos.add(converttoDto(loaisanpham));
        }
        return loaiSanPhamDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Loaisanpham loaisanpham = loaiSanPhamService.getById(id);
        if (loaisanpham == null) {
            return ResponseEntity.badRequest().body("Loại sản phẩm không tồn tại.");
        }
        LoaiSanPhamDto loaiSanPhamDto = converttoDto(loaisanpham);
        return ResponseEntity.status(200).body(loaiSanPhamDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addLoaiSanPham(@RequestBody Loaisanpham loaisanpham, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        } else {
            loaiSanPhamService.saveLoaiSanPham(loaisanpham);
            return ResponseEntity.ok(loaisanpham);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editLoaiSanPham(@PathVariable Long id, @RequestBody Loaisanpham loaisanpham) {
        Optional<Loaisanpham> loaisanphamOptional = Optional.ofNullable(loaiSanPhamService.getById(id));

        if (!loaisanphamOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Loại sản phẩm không tồn tại.");
        }

        Loaisanpham eLoaisanpham = loaisanphamOptional.get();
        eLoaisanpham.setTenloai(loaisanpham.getTenloai());

        loaiSanPhamService.saveLoaiSanPham(eLoaisanpham);
        return ResponseEntity.ok("Đã cập nhật.");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteLoaiSanPham(@PathVariable Long id) {
        if (loaiSanPhamService.getById(id) != null) {
            loaiSanPhamService.removeLoaiSanPham(id);
            return ResponseEntity.ok("Đã xóa.");
        } else {
            return ResponseEntity.badRequest().body("Loại sản phẩm không tồn tại");
        }
    }
}
