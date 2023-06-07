package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.services.CuaHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
}
