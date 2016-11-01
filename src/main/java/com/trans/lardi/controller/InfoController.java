package com.trans.lardi.controller;

import com.trans.lardi.db.Info;
import com.trans.lardi.service.InfoService;
import com.trans.lardi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private UserService userService;

    @ModelAttribute("newinfo")
    public Info constructUser() {
        return new Info();
    }

    @RequestMapping("/infopage")
    public String showInfo(Model model, @RequestParam(value = "mes", required = false) String message) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("infopage", infoService.getAllInfo(username));
        model.addAttribute("message", message);
        return "infopage";
    }

    @RequestMapping("/newinfo")
    public String addInfo(Model model, @RequestParam(value = "action", required = false) String action,
                           @RequestParam(value = "id", required = false) Long id) {

        if (action == null || action.equals("new")) {
            Info newInfo = new Info();
            model.addAttribute("newinfo", newInfo);
            return "newinfo";
        }
        if (action != null && action.equals("edit") && id != null) {
            Info saveInfo = infoService.getInfo(id);
            model.addAttribute("newinfo", saveInfo);
            return "newinfo";
        }
        return "newinfo";
    }

    @RequestMapping(value = "/newinfo", method = RequestMethod.POST)
    public String newInfo(Model model, @Valid @ModelAttribute("newinfo") Info info, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("errors");
            System.out.println(result);
            return "newinfo";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        info.setUsers_name(username);
        infoService.saveOrUpdate(info);

        return "redirect:/infopage";
    }

    @RequestMapping(value = "/delete")
    public String deleteEntry(Model model, @RequestParam(value = "id", required = true) Long id) {

        String message = null;
        boolean isDeleted = infoService.delete(id);
        if (isDeleted) {
            message = "Success deleted entry";
        } else {
            message = "Can't delete";
        }
        return "redirect:/infopage";
    }

    @RequestMapping("/search")
    public String addEntry(Model model, @RequestParam(value = "pattern", required = false) String pattern) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Info> infopage = infoService.search(pattern, username);
        model.addAttribute("infopage", infopage);
        model.addAttribute("message", "search results: " + infopage.size());
        return "infopage";
    }
}
