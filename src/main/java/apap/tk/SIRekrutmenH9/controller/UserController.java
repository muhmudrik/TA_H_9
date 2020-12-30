package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.RoleModel;
import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.BaseResponse2;
import apap.tk.SIRekrutmenH9.rest.PegawaiData;
import apap.tk.SIRekrutmenH9.service.PegawaiRestService;
import apap.tk.SIRekrutmenH9.service.RoleService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PegawaiRestService pegawaiRestService;

    @RequestMapping("/add")
    public String addUserPage(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "add-user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute PegawaiData pegawaiData,
                                @RequestParam("password") String password,
                                RedirectAttributes redirect){
        UserModel pengguna = new UserModel();
        RoleModel jasa = roleService.findRoleById(pegawaiData.getRoleId());
        pengguna.setUsername(pegawaiData.getUsername());
        pengguna.setPassword(password);
        pengguna.setRole(jasa);
        userService.addUser(pengguna);
        pegawaiRestService.addPegawaiData(pegawaiData);
        return "redirect:/user/add";
    }


    @RequestMapping(value = "/profile")
    private String profile(Model model) throws WebClientException {
        UserModel pengguna = userService.getUserByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());
        RoleModel jasa = roleService.findRoleById(
                pengguna
                        .getRole()
                        .getId());
        try{
            BaseResponse2 bsRespone = pegawaiRestService.getPegawaiData(pengguna.getUsername());
            PegawaiData pegawai = bsRespone.getResult();
            model.addAttribute("isTrue", true);
            model.addAttribute("pegawai", pegawai);
        }catch (WebClientException webClientException){ }
        model.addAttribute("username", pengguna.getUsername());
        model.addAttribute("role", jasa.getRole());
        model.addAttribute("dateTime", LocalDateTime.now());

        return "profile-user";
    }




}
