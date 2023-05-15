package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.dto.DonDatHangDto;
import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.services.DonDatHangService;
import Api.AppDatDoAn.services.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/dondathang")
public class DonDatHangController {
    @Autowired
    private DonDatHangService donDatHangService;

    @Autowired
    private KhachHangService khachHangService;

    private DonDatHangDto converttoDto(DonDatHang donDatHang) {
        DonDatHangDto donDatHangDto = new DonDatHangDto();
        donDatHangDto.setMadondathang(donDatHang.getMadondathang());
        donDatHangDto.setNgaydat(donDatHang.getNgaydat());
        donDatHangDto.setGiohen(donDatHang.getGiohen());
        donDatHangDto.setTinhtrang(donDatHang.getTinhtrang());
        donDatHangDto.setTenkhachhang(khachHangService.getKhachHangById(donDatHang.getKhachhang().getMakhachhang()).getTenkhachhang());
        return donDatHangDto;
    }

    @GetMapping
    @ResponseBody
    public List<DonDatHangDto> getAllDDH() {
        List<DonDatHang> donDatHangs = donDatHangService.getAllDDH();
        List<DonDatHangDto> donDatHangDtos = new ArrayList<>();
        for (DonDatHang donDatHang : donDatHangs) {
            donDatHangDtos.add(converttoDto(donDatHang));
        }
        return donDatHangDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getDDHById(@PathVariable String id) {
        DonDatHang donDatHang = donDatHangService.getDDHById(id);
        if (donDatHang == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy đơn đặt hàng có mã là: " + id);
        }
        DonDatHangDto donDatHangDto = converttoDto(donDatHang);
        return ResponseEntity.ok(donDatHangDto);
    }

    @GetMapping("/thong-tin-dat-hang/{id}")
    @ResponseBody
    public List<Object[]> layThongTinDatHangCuaKhachHang(@PathVariable String id) {
        return donDatHangService.LayThongTinDatHangTheoKhachHang(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addDonDatHang(@Valid @RequestBody DonDatHang donDatHang) {
        donDatHangService.saveDDH(donDatHang);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm đơn đặt hàng thành công!");
    }
}
