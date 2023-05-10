package Api.AppDatDoAn.apicontroller;

import Api.AppDatDoAn.dto.DonDatHangDto;
import Api.AppDatDoAn.dto.KhachHangDto;
import Api.AppDatDoAn.dto.SanPhamDto;
import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.entity.KhachHang;
import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.reponsitory.ISanPhamReponsitory;
import Api.AppDatDoAn.services.DonDatHangService;
import Api.AppDatDoAn.services.KhachHangService;
import Api.AppDatDoAn.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        donDatHangDto.setTinhtrang(donDatHangDto.getTinhtrang());
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

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addDonDatHang(@RequestBody DonDatHang donDatHang, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        } else {
            donDatHangService.saveDDH(donDatHang);
            return ResponseEntity.ok(donDatHang);
        }
    }
}
