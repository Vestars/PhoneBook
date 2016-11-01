package com.trans.lardi.controller;

import com.trans.lardi.db.User;
import com.trans.lardi.service.InfoService;
import com.trans.lardi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private InfoService infoService;

    @Autowired
    UserService userService;

    @RequestMapping("/403")
    public String noaccess() {
        return "403";
    }

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String signIn(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signUp(Model model, @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        user.setAuthority("ROLE_USER");
        user.setEnabled(true);
        if (userService.exists(user.getUsername())) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "register";
        }
        try {
            userService.save(user);
        } catch (DuplicateKeyException e) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "register";
        }

        return "redirect:/infopage";
    }
}
