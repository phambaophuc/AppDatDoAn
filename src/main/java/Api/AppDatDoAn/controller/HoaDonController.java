package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.dto.HoaDonDto;
import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.entity.HoaDon;
import Api.AppDatDoAn.services.DonDatHangService;
import Api.AppDatDoAn.services.HoaDonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/hoadon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private DonDatHangService donDatHangService;

    private HoaDonDto converttoDto(HoaDon hoaDon) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double formattedNumber = Double.valueOf(decimalFormat.format(hoaDon.getTongtien()));

        HoaDonDto hoaDonDto = new HoaDonDto();
        hoaDonDto.setMahoadon(hoaDon.getMahoadon());
        hoaDonDto.setNgaylap(hoaDon.getNgaylap());
        hoaDonDto.setTongtien(formattedNumber);
        hoaDonDto.setTenkhachhang(donDatHangService.getDDHById(hoaDon.getDonDatHang().getMadondathang()).getKhachhang().getTenkhachhang());

        return hoaDonDto;
    }

    @GetMapping
    @ResponseBody
    public List<HoaDonDto> getAllHoaDon() {
        List<HoaDon> hoaDons = hoaDonService.getAllHoaDon();
        List<HoaDonDto> hoaDonDtos = new ArrayList<>();
        for (HoaDon hoaDon : hoaDons) {
            hoaDonDtos.add(converttoDto(hoaDon));
        }
        return hoaDonDtos;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addHoaDon(@Valid @RequestBody HoaDon hoaDon) {
        DonDatHang donDatHang = donDatHangService.getDDHById(hoaDon.getDonDatHang().getMadondathang());
        hoaDon.setDonDatHang(donDatHang);
        hoaDonService.saveHoaDon(hoaDon);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm hóa đơn thành công!");
    }

    @GetMapping("/thong-ke")
    public ResponseEntity<?> thongKe(@RequestParam("thang") int thang, @RequestParam("nam") int nam) {
        List<HoaDon> danhSachHoaDon = hoaDonService.getAllHoaDon();
        double tongTien = 0;

        for (HoaDon hoaDon : danhSachHoaDon) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(hoaDon.getNgaylap());

            int hoaDonThang = calendar.get(Calendar.MONTH) + 1;
            int hoaDonNam = calendar.get(Calendar.YEAR);

            if (hoaDonThang == thang && hoaDonNam == nam) {
                tongTien += hoaDon.getTongtien();
            }
        }

        return ResponseEntity.ok().body("Tổng tiền của tháng " + thang + " năm " + nam + " là: " + tongTien);
    }
}
