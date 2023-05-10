package Api.AppDatDoAn.apicontroller;

import Api.AppDatDoAn.dto.CuaHangDto;
import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.services.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
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
        cuaHangDto.setMacuahang(cuahang.getMacuahang());
        cuaHangDto.setTencuahang(cuahang.getTencuahang());
        cuaHangDto.setHinhanh(cuahang.getHinhanh());
        cuaHangDto.setDiachi(cuahang.getDiachi());
        cuaHangDto.setTinhtrang(cuahang.getTinhtrang());
        cuaHangDto.setSodienthoai(cuahang.getSodienthoai());
        cuaHangDto.setLuotdanhgia(cuahang.getLuotdanhgia());
        cuaHangDto.setChatluong(cuahang.getChatluong());
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
            return ResponseEntity.badRequest().body("Cửa hàng không tồn tại.");
        }
        CuaHangDto cuaHangDto = converttoDto(cuahang);
        return ResponseEntity.status(200).body(cuaHangDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addCuaHang(@RequestBody CuaHang cuahang, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        } else {
            cuaHangService.saveCuaHang(cuahang);
            return ResponseEntity.ok(cuahang);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editCuaHang(@PathVariable String id, @RequestBody CuaHang cuahang) {
        Optional<CuaHang> cuahangOptional = Optional.ofNullable(cuaHangService.getCuaHangById(id));

        if (!cuahangOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Cửa hàng không tồn tại.");
        }

        cuaHangService.updateCuaHang(id, cuahang);

        return ResponseEntity.ok("Đã cập nhật.");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCuaHang(@PathVariable String id) {
        cuaHangService.removeCuaHang(id);
        return ResponseEntity.ok("Đã xóa.");
    }
}
