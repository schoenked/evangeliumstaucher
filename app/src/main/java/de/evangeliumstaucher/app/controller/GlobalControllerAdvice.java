package de.evangeliumstaucher.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

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
}