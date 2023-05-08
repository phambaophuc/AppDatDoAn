package Api.AppDatDoAn.apicontroller;

import Api.AppDatDoAn.dto.CuaHangDto;
import Api.AppDatDoAn.entity.Cuahang;
import Api.AppDatDoAn.services.CuaHangService;
import org.apache.commons.lang3.StringUtils;
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

    private CuaHangDto converttoDto(Cuahang cuahang) {
        CuaHangDto cuaHangDto = new CuaHangDto();
        cuaHangDto.setMacuahang(cuahang.getMacuahang());
        cuaHangDto.setTencuahang(cuahang.getTencuahang());
        cuaHangDto.setHinhanh(cuahang.getHinhanh());
        cuaHangDto.setDiachi(cuahang.getDiachi());
        cuaHangDto.setTinhtrang(cuahang.getTinhtrang());
        cuaHangDto.setSodienthoai(cuahang.getSodienthoai());
        cuaHangDto.setLuotdanhgia(cuahang.getLuotdanhgia());
        cuaHangDto.setLuotmua(cuahang.getLuotmua());
        cuaHangDto.setChatluong(cuahang.getChatluong());
        cuaHangDto.setGiomocua(cuahang.getGiomocua());
        cuaHangDto.setGiodongcua(cuahang.getGiodongcua());
        cuaHangDto.setTinhtrang(cuahang.getTinhtrang());
        return cuaHangDto;
    }

    @GetMapping
    @ResponseBody
    public List<CuaHangDto> getAllCuaHang() {
        List<Cuahang> cuahangs = cuaHangService.getAllCuaHang();
        List<CuaHangDto> cuaHangDtos = new ArrayList<>();
        for (Cuahang cuahang : cuahangs) {
            cuaHangDtos.add(converttoDto(cuahang));
        }
        return cuaHangDtos;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable String id) {
        Cuahang cuahang = cuaHangService.getCuaHangById(id);
        if (cuahang == null) {
            return ResponseEntity.badRequest().body("Cửa hàng không tồn tại.");
        }
        CuaHangDto cuaHangDto = converttoDto(cuahang);
        return ResponseEntity.status(200).body(cuaHangDto);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addCuaHang(@RequestBody Cuahang cuahang, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        } else {
            cuaHangService.saveCuaHang(cuahang);
            return ResponseEntity.ok(cuahang);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editCuaHang(@PathVariable String id, @RequestBody Cuahang cuahang) {
        Optional<Cuahang> cuahangOptional = Optional.ofNullable(cuaHangService.getCuaHangById(id));

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
