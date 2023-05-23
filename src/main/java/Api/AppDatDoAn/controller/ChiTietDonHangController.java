package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.services.ChiTietDonDatHangService;
import Api.AppDatDoAn.services.DonDatHangService;
import Api.AppDatDoAn.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/chi-tiet-don-hang")
public class ChiTietDonHangController {
    @Autowired
    private ChiTietDonDatHangService chiTietDonDatHangService;

    // Tạo chi tiết đơn hàng theo mã sản phẩm và mã đơn hàng
    @PostMapping("/{masanpham}/{madondathang}")
    public ResponseEntity<?> createChiTietDonHang(@PathVariable("masanpham")String masanPham,
                                               @PathVariable("madondathang")String madondathang) {
        chiTietDonDatHangService.createChiTietDDH(masanPham, madondathang, 1L);
        return ResponseEntity.ok("Thêm chi tiết đơn hàng thành công.");
    }

    // Xóa chi tiết đơn hàng theo mã sản phẩm và mã đơn hàng
    @DeleteMapping("/{masanpham}/{madondathang}")
    public ResponseEntity<?> deleteChiTietDonHang(@PathVariable("masanpham")String masanPham,
                                                  @PathVariable("madondathang")String madondathang) {
        chiTietDonDatHangService.deleteChiTietDDH(masanPham, madondathang);
        return ResponseEntity.ok("Xóa chi tiết đơn hàng thành công.");
    }
}
