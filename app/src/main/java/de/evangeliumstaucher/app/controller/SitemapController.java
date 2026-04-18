package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.viewmodel.QuizModel;
import de.evangeliumstaucher.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SitemapController {

    @Autowired
    private GameRepository gameRepository;

    @Value("${myHostname}")
    private String hostname;

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String createSitemap() {
        StringBuilder out = new StringBuilder();

        out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

        // 1. Statische Routen
        appendUrl(out, hostname + "/", "weekly", "1.0", null);
        appendUrl(out, hostname + "/quiz/create", "monthly", "0.8", null);
        appendUrl(out, hostname + "/about", "monthly", "0.8", null);

        // 2. Dynamische Routen (Datenbank)
        gameRepository.findAll().forEach(gameEntity -> {
            String url = hostname + QuizModel.getUrl(gameEntity.getId());
            String lastMod = gameEntity.getCreatedAt() != null ? gameEntity.getCreatedAt().toString() : null;

            appendUrl(out, url, "monthly", "0.1", lastMod);
        });

        out.append("</urlset>");
        return out.toString();
    }

    /**
     * Kompakte Hilfsmethode ohne Leerzeichen und Zeilenumbrüche.
     */
    private void appendUrl(StringBuilder sb, String loc, String changefreq, String priority, String lastmod) {
        sb.append("<url>");
        sb.append("<loc>").append(loc).append("</loc>");
        sb.append("<changefreq>").append(changefreq).append("</changefreq>");
        sb.append("<priority>").append(priority).append("</priority>");

        if (lastmod != null && !lastmod.isBlank()) {
            sb.append("<lastmod>").append(lastmod).append("</lastmod>");
        }

        sb.append("</url>");
    }
}