package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.repo.service.Library;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller()
@Slf4j
public class ErrorController extends BaseController {

    public ErrorController(Library library) {
        super(library);
    }

    @GetMapping("/error/403")
    public RedirectView http403(Model m, RedirectAttributes redirectAttrs) {
        m.addAttribute("warning", "Dein Benutzer ist nicht zur Nutzung freigegeben.");
        RedirectView v = new RedirectView("/?warning=" + URLEncoder.encode("Dein Benutzer ist nicht zur Nutzung freigegeben.", StandardCharsets.UTF_8));
        return v;
    }

    @GetMapping("/error")
    public String error(Model m) {
        return "error.html";
    }
}
