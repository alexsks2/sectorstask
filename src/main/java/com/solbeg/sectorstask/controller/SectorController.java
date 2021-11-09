package com.solbeg.sectorstask.controller;

import com.solbeg.sectorstask.entity.Sector;
import com.solbeg.sectorstask.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @Value("${welcome.message}")
    private String message;

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping(value = "/list")
    public String getAll(Model model) {
        model.addAttribute("sectorList", sectorService.getAll());
        return "new-index";
    }

    @RequestMapping("/export")
    public String importFromHtmlFile() {
        try {
            sectorService.importSectorList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "new-index";
    }

    @PostMapping("/saveSector")
    private String saveSector(@ModelAttribute("sector") Sector sector) {
        sectorService.saveSector(sector);
        return "redirect:/list";
    }

}
