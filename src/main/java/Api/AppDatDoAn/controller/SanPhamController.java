package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.services.CuaHangService;
import Api.AppDatDoAn.services.LoaiSanPhamService;
import Api.AppDatDoAn.services.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private CuaHangService cuaHangService;

    @GetMapping
    public String sanPham(Model model) {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        model.addAttribute("sanPhams", sanPhams);
        return "sanpham/san-pham";
    }

    @GetMapping("/them-san-pham")
    public String themSanPham(Model model) {
        model.addAttribute("newSanPham", new SanPham());
        model.addAttribute("loaiSanPhams", loaiSanPhamService.getAll());
        model.addAttribute("cuaHangs", cuaHangService.getAllCuaHang());
        return "sanpham/them-san-pham";
    }
    @PostMapping("/them-san-pham")
    public String themSanPham(@Valid @ModelAttribute("newSanPham") SanPham sanPham,
                              BindingResult result, Model model) {
        if (result.hasErrors())
        {
            model.addAttribute("loaisanphams", loaiSanPhamService.getAll());
            model.addAttribute("cuaHangs", cuaHangService.getAllCuaHang());
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors)
            {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "sanpham/them-san-pham";
        }
        sanPhamService.saveSanPham(sanPham);
        return "redirect:/san-pham";
    }

    @GetMapping("/sua-san-pham/{masanpham}")
    public String suaSanPham(@PathVariable("masanpham")String maSanPham, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(maSanPham);
        model.addAttribute("editSanPham", sanPham);
        model.addAttribute("loaiSanPhams", loaiSanPhamService.getAll());
        model.addAttribute("cuaHangs", cuaHangService.getAllCuaHang());
        return "sanpham/sua-san-pham";
    }
    @PostMapping("/sua-san-pham")
    public String suaSanPham(@Valid @ModelAttribute("editSanPham")SanPham sanPham) {
        sanPhamService.updateSanPham(sanPham);
        return "redirect:/san-pham";
    }
}
