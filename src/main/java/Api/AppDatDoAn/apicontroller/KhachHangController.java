package Api.AppDatDoAn.apicontroller;

import Api.AppDatDoAn.dto.KhachHangDto;
import Api.AppDatDoAn.entity.Khachhang;
import Api.AppDatDoAn.services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    private KhachHangDto converttoDto(Khachhang khachhang) {
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
        List<Khachhang> khachhangs = khachHangService.getAllKhachHang();
        List<KhachHangDto> khachHangDtos = new ArrayList<>();
        for (Khachhang khachhang : khachhangs) {
            khachHangDtos.add(converttoDto(khachhang));
        }
        return khachHangDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getKhachHangById(@PathVariable UUID id) {
        Khachhang khachhang = khachHangService.getKhachHangById(id);
        if (khachhang == null) {
            return ResponseEntity.badRequest().body("khách hàng không tồn tại.");
        }
        KhachHangDto khachHangDto = converttoDto(khachhang);
        return ResponseEntity.status(200).body(khachHangDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addKhachHang(@RequestBody Khachhang khachhang, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        } else {
            khachHangService.saveKhachHang(khachhang);
            return ResponseEntity.ok(khachhang);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editKhachHang(@PathVariable UUID id, @RequestBody Khachhang khachhang) {
        Optional<Khachhang> khachhangOptional = Optional.ofNullable(khachHangService.getKhachHangById(id));

        if (!khachhangOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Khách hàng không tồn tại.");
        }
        khachHangService.updateKhachHang(id, khachhang);
        return ResponseEntity.ok("Đã cập nhật.");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteKhachHang(@PathVariable UUID id) {
        khachHangService.removeKhachHang(id);
        return ResponseEntity.ok("Đã xóa.");
    }
}
