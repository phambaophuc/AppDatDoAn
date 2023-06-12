package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.services.AccountService;
import Api.AppDatDoAn.services.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/don-dat-hang")
public class DonDatHangController {
    @Autowired
    private DonDatHangService donDatHangService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String donDatHang(Model model, Principal principal) {
        Account account = accountService.getAccountByUsername(principal.getName());
        List<DonDatHang> donDatHangs = donDatHangService.getAllDDHByMaCH(account.getMacuahang());

        model.addAttribute("donDatHangs", donDatHangs);
        return "dondathang/don-dat-hang";
    }
}
