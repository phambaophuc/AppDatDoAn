package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.services.CuaHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/cua-hang")
public class CuaHangController {
    @Autowired
    private CuaHangService cuaHangService;

    @GetMapping
    public String cuaHang(Model model) {
        List<CuaHang> cuaHangs = cuaHangService.getAllCuaHang();
        model.addAttribute("cuaHangs", cuaHangs);
        return "cuahang/cua-hang";
    }

    @GetMapping("/them-cua-hang")
    public String themCuaHang(Model model) {
        model.addAttribute("newCuaHang", new CuaHang());
        return "cuahang/them-cua-hang";
    }
    @PostMapping("/them-cua-hang")
    public String themCuaHang(@Valid @ModelAttribute("newCuaHang") CuaHang cuaHang,
                              BindingResult result, Model model) {
        if (result.hasErrors())
        {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors)
            {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "cuahang/them-cua-hang";
        }
        cuaHangService.saveCuaHang(cuaHang);
        return "redirect:/cua-hang";
    }

    @GetMapping("/sua-cua-hang/{macuahang}")
    public String suaCuaHang(@PathVariable("macuahang")String macuahang, Model model) {
        CuaHang cuaHang = cuaHangService.getCuaHangById(macuahang);
        model.addAttribute("editCuaHang", cuaHang);
        return "cuahang/sua-cua-hang";
    }
    @PostMapping("/sua-cua-hang")
    public String suaCuaHang(@Valid @ModelAttribute("editCuaHang") CuaHang cuaHang) {
        cuaHangService.updateCuaHang(cuaHang);
        return "redirect:/cua-hang";
    }

    @GetMapping("/xoa-cua-hang/{macuahang}")
    public String xoaCuaHang(@PathVariable("macuahang")String maCuaHang) {
        cuaHangService.removeCuaHang(maCuaHang);
        return "redirect:/cua-hang";
    }
}
