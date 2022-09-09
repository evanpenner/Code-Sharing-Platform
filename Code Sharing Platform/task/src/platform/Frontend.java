package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class Frontend {
    private String format(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(-6)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Autowired
    private SnippetService snippetService;


    @GetMapping("/code/{id}")
    public String getHtmlCode(Model model, @PathVariable UUID id) {
        Snippet snippet = snippetService.getSnippet(id);
        model.addAttribute("code", snippet.getCode());
        model.addAttribute("date", format(snippet.getCreation()));
        model.addAttribute("time_restriction", snippet.getTimeRestricition() != 0 ? (snippet.getTimeRestricition() - snippet.getCreation().until(Instant.now(), ChronoUnit.SECONDS)) : 0);
        model.addAttribute("views_restriction", snippet.getViewRestriction());
        model.addAttribute("has_view_restriction", snippet.hasViewRestriction);
        return "code";
    }

    @GetMapping("/code/new")
    public String getHTMLNewCode(Model model) {
        return "new";
    }


    public List<Map<String, String>> getLatest() {
        List<Map<String, String>> latest = new ArrayList<>();
        for (Snippet snippet : snippetService.getLatestSnippets()) {
            latest.add(Map.of("code", snippet.getCode(), "date", format(snippet.getCreation())));
        }
        return latest;
    }

    @GetMapping("/code/latest")
    public String getLatestDisplay(Model model) {
        model.addAttribute("latest", getLatest());
        return "latest";
    }
}
