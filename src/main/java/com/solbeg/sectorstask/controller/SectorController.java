package com.solbeg.sectorstask.controller;

import com.solbeg.sectorstask.entity.User;
import com.solbeg.sectorstask.service.SectorService;
import com.solbeg.sectorstask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class SectorController {

    private final SectorService sectorService;
    private final UserService userService;

    @Autowired
    public SectorController(SectorService sectorService, UserService userService) {
        this.sectorService = sectorService;
        this.userService = userService;
    }

    @GetMapping(value = { "/", "/index" })
    public String index() {
        return "index";
    }

    @GetMapping("/emptylist")
    public String emptyList() {
        return "index2";
    }

    @GetMapping(value = "/list")
    public String getAll(Model model, @RequestParam(value = "name",required = false) String name) {
        model.addAttribute("sectorList", sectorService.getAll());
        User user = new User();
        if (name != null) {
            user = userService.getUser(name);
        }
        model.addAttribute("user", user);

        return "index3";
    }

    @RequestMapping("/export")
    public String importFromHtmlFile() {
        try {
            sectorService.importSectorList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sectorList", sectorService.getAll());
            return "index3";
        }

        userService.processUser(user);
        return "redirect:/list";
    }

}
