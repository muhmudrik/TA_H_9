package apap.tk.SIRekrutmenH9.controller;


import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        if(userService.passwordChecker(user.getPassword()) == true){
            userService.addUser(user);
            model.addAttribute("user", user);
            model.addAttribute("pesan", "user berhasil ditambahkan");
        }else{
            model.addAttribute("user", user);
            model.addAttribute("pesan", "password harus mengandung angka dan huruf serta memiliki minimal 8 karakter");
            return "add-user";
        }

        return "home";
    }


//    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
//    public String changePassword(@ModelAttribute UserModel userModel, String sandiLama, String sandiBaru, String confirmSandiBaru, Model model){
//        UserModel usr = userService.getUserByUsername(userModel.getUsername());
//        if (userService.passwordMatch(sandiLama, usr.getPassword())){
//            if (sandiBaru.equals(confirmSandiBaru)){
//                if(userService.passwordChecker(sandiBaru) != false){
//                    usr.setPassword(sandiBaru);
//                    userService.addUser(usr);
//                    // succes
//                    model.addAttribute("pesan2", "sandi berhasil diubah");
//                }else{
//                    // exception handle kurang dari 8 angka
//                    model.addAttribute("pesan2", "sandi minimal 8 angka dan berisi paduan huruf dan angka ");
//                }
//            }else {
//                // exception handle jika sandi baru tidak sama
//                model.addAttribute("pesan2", "sandi tidak sama");
//            }
//        }else {
//            // exception handle jika sandi lama tidak sama
//            model.addAttribute("pesan2", "sandi lama salah");
//        }
//        return "change-sandi";
//    }

}
