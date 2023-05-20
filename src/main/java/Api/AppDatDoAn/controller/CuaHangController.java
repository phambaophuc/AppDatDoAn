package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.dto.CuaHangDto;
import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.services.CuaHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cuahang")
public class CuaHangController {
    @Autowired
    private CuaHangService cuaHangService;

    private CuaHangDto converttoDto(CuaHang cuahang) {
        CuaHangDto cuaHangDto = new CuaHangDto();
        String chatluong = String.format("%.1f", cuahang.getChatluong());
        cuaHangDto.setMacuahang(cuahang.getMacuahang());
        cuaHangDto.setTencuahang(cuahang.getTencuahang());
        cuaHangDto.setHinhanh(cuahang.getHinhanh());
        cuaHangDto.setDiachi(cuahang.getDiachi());
        cuaHangDto.setTinhtrang(cuahang.getTinhtrang());
        cuaHangDto.setSodienthoai(cuahang.getSodienthoai());
        cuaHangDto.setLuotdanhgia(cuahang.getLuotdanhgia());
        cuaHangDto.setChatluong(Double.parseDouble(chatluong));
        cuaHangDto.setGiomocua(cuahang.getGiomocua());
        cuaHangDto.setGiodongcua(cuahang.getGiodongcua());
        cuaHangDto.setTinhtrang(cuahang.getTinhtrang());
        return cuaHangDto;
    }

    @GetMapping
    @ResponseBody
    public List<CuaHangDto> getAllCuaHang() {
        List<CuaHang> cuahangs = cuaHangService.getAllCuaHang();
        List<CuaHangDto> cuaHangDtos = new ArrayList<>();
        for (CuaHang cuahang : cuahangs) {
            cuaHangDtos.add(converttoDto(cuahang));
        }
        return cuaHangDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable String id) {
        CuaHang cuahang = cuaHangService.getCuaHangById(id);
        if (cuahang == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy cửa hàng có mã là: " + id);
        }
        CuaHangDto cuaHangDto = converttoDto(cuahang);
        return ResponseEntity.status(200).body(cuaHangDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addCuaHang(@Valid @RequestBody CuaHang cuahang) {
        cuaHangService.saveCuaHang(cuahang);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm cửa hàng thành công!");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editCuaHang(@PathVariable String id, @RequestBody CuaHang cuahang) {
        Optional<CuaHang> cuahangOptional = Optional.ofNullable(cuaHangService.getCuaHangById(id));

        if (!cuahangOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Không tìm thấy cửa hàng có mã là: " + id);
        }

        cuaHangService.updateCuaHang(id, cuahang);
        return ResponseEntity.ok("Đã cập nhật cửa hàng.");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCuaHang(@PathVariable String id) {
        CuaHang cuaHang = cuaHangService.getCuaHangById(id);
        if (cuaHang == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy cửa hàng có mã là: " + id);
        }
        cuaHangService.removeCuaHang(id);
        return ResponseEntity.ok("Đã xóa cửa hàng này.");
    }


    // Chức năng đánh giá cửa hàng
    @PostMapping("/{macuahang}/danhgia")
    @ResponseBody
    public ResponseEntity<?> danhgiacuahang(@PathVariable String macuahang,
                                            @RequestParam("chatluong") int diemDanhGia) {
        Optional<CuaHang> optionalCuaHang = Optional.ofNullable(cuaHangService.getCuaHangById(macuahang));
        if (!optionalCuaHang.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        CuaHang cuaHang = optionalCuaHang.get();
        long luotDanhGiaMoi = cuaHang.getLuotdanhgia() + 1;
        double chatLuongMoi = (cuaHang.getChatluong() * cuaHang.getLuotdanhgia() + diemDanhGia) / luotDanhGiaMoi;


        cuaHang.setLuotdanhgia(luotDanhGiaMoi);
        cuaHang.setChatluong(chatLuongMoi);

        cuaHangService.saveCuaHang(cuaHang);
        return ResponseEntity.ok("Bạn đã đánh giá cửa hàng " + cuaHang.getTencuahang() + " " + diemDanhGia + " sao. Xin cảm ơn");
    }
}
