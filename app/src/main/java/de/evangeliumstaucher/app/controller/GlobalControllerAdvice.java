package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    QuizService quizService;

    @ModelAttribute("currentPath")
    public String getCurrentPath(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // Sicherheitsnetz: Wenn wir auf der Fehlerseite sind, wollen wir beim Login
        // nicht dorthin zurückkehren, sondern zur Startseite.
        if (uri == null || "/error".equals(uri)) {
            return "/";
        }

        String queryString = request.getQueryString();
        return (queryString != null) ? uri + "?" + queryString : uri;
    }

    @ModelAttribute("hostname")
    public String globalHostname() {
        return quizService.getHostname();
    }
}