package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.services.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/don-dat-hang")
public class DonDatHangController {
    @Autowired
    private DonDatHangService donDatHangService;

    @GetMapping
    public String donDatHang(Model model) {
        List<DonDatHang> donDatHangs = donDatHangService.getAllDDH();
        model.addAttribute("donDatHangs", donDatHangs);
        return "dondathang/don-dat-hang";
    }
}
