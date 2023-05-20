package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.dto.KhachHangDto;
import Api.AppDatDoAn.entity.KhachHang;
import Api.AppDatDoAn.services.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/khachhang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    private KhachHangDto converttoDto(KhachHang khachhang) {
        KhachHangDto khachHangDto = new KhachHangDto();
        khachHangDto.setMakhachhang(khachhang.getMakhachhang());
        khachHangDto.setTenkhachhang(khachhang.getTenkhachhang());
        khachHangDto.setDiachi(khachhang.getDiachi());
        khachHangDto.setSodienthoai(khachhang.getSodienthoai());
        return khachHangDto;
    }

    @GetMapping
    @ResponseBody
    public List<KhachHangDto> getAllKhachHang() {
        List<KhachHang> khachhangs = khachHangService.getAllKhachHang();
        List<KhachHangDto> khachHangDtos = new ArrayList<>();
        for (KhachHang khachhang : khachhangs) {
            khachHangDtos.add(converttoDto(khachhang));
        }
        return khachHangDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getKhachHangById(@PathVariable String id) {
        KhachHang khachhang = khachHangService.getKhachHangById(id);
        if (khachhang == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy khách hàng có mã là: " + id);
        }
        KhachHangDto khachHangDto = converttoDto(khachhang);
        return ResponseEntity.status(200).body(khachHangDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addKhachHang(@Valid @RequestBody KhachHang khachhang) {
        khachHangService.saveKhachHang(khachhang);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm khách hàng thành công!");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editKhachHang(@PathVariable String id, @RequestBody KhachHang khachhang) {
        Optional<KhachHang> khachhangOptional = Optional.ofNullable(khachHangService.getKhachHangById(id));

        if (!khachhangOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Không tìm thấy khách hàng có mã là: " + id);
        }
        khachHangService.updateKhachHang(id, khachhang);
        return ResponseEntity.ok("Đã cập nhật thông tin khách hàng.");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteKhachHang(@PathVariable String id) {
        KhachHang khachHang = khachHangService.getKhachHangById(id);
        if (khachHang == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy khách hàng có mã là: " + id);
        }
        khachHangService.removeKhachHang(id);
        return ResponseEntity.ok("Đã xóa khách hàng.");
    }
}
