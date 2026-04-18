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
    GameRepository gameRepository;
    @Value("${myHostname}")
    private String hostname;

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String createSitemap() {
        String baseUrl = hostname;

        // Hier würdest du idealerweise deine öffentlichen URLs aus der Datenbank laden
        // und per StringBuilder oder Schleife zusammenbauen.

        StringBuilder out = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        gameRepository.findAll().forEach(gameEntity -> {

            out.append("   <url>\n")
                    .append("      <loc>")
                    .append(baseUrl)
                    .append(QuizModel.getUrl(gameEntity.getId()))
                    .append("</loc>\n")
                    .append("      <changefreq>monthly</changefreq>\n")
                    .append("      <priority>0.1</priority>\n")
                    .append("      <lastmod>")
                    .append(gameEntity.getCreatedAt().toString())
                    .append("</lastmod>\n")
                    .append("   </url>\n");
        });
        out.append("   <url>\n")
                .append("      <loc>")
                .append(baseUrl)
                .append("/</loc>\n")
                .append("      <changefreq>weekly</changefreq>\n")
                .append("      <priority>1.0</priority>\n")
                .append("   </url>\n")
                .append("   <url>\n")
                .append("      <loc>")
                .append(baseUrl)
                .append("/quiz/create</loc>\n")
                .append("      <changefreq>monthly</changefreq>\n")
                .append("      <priority>0.8</priority>\n")
                .append("   </url>\n")

                .append("   <url>\n")
                .append("      <loc>")
                .append(baseUrl)
                .append("/about</loc>\n")
                .append("      <changefreq>monthly</changefreq>\n")
                .append("      <priority>0.8</priority>\n")
                .append("   </url>\n")
                .append("   <url>\n")
                .append("      <loc>")
                .append(baseUrl)
                .append("/about</loc>\n")
                .append("      <changefreq>monthly</changefreq>\n")
                .append("      <priority>0.8</priority>\n")
                .append("   </url>\n")
                .append("</urlset>");
        return out.toString();
    }
}