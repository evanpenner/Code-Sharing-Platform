package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {
    @Autowired
    private SnippetService snippetService;

    private String format(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(-6)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @PostMapping("/api/code/new")
    public Map<String, String> changeCode(HttpServletResponse response, @RequestBody Map<String, Object> body) {
        response.setContentType("application/json");
        return Map.of("id", snippetService.putSnippet(new Snippet((String) body.get("code"), (int) body.getOrDefault("views", 0), (int) body.getOrDefault("time", 0))).toString());
    }

    @GetMapping("/api/code/latest")
    public List<Map<String, Object>> getLatest(HttpServletResponse response) {
        response.setContentType("application/json");
        List<Map<String, Object>> latest = new ArrayList<>();
        for (Snippet snippet : snippetService.getLatestSnippets()) {
            latest.add(Map.of("code", snippet.getCode(), "date", format(snippet.getCreation()), "time", snippet.getTimeRestricition(), "views", snippet.getViewRestriction()));
        }
        return latest;
    }

    @GetMapping("/api/code/{id}")
    public Map<String, Object> getCode(HttpServletResponse response, @PathVariable UUID id) {
        response.setContentType("application/json");
        Snippet snippet = snippetService.getSnippet(id);
        long a = snippet.getTimeRestricition() != 0 ? (snippet.getTimeRestricition() - snippet.getCreation().until(Instant.now(), ChronoUnit.SECONDS)) : 0;
        HashMap<String, Object> map = new HashMap();
        map.put("code", snippet.getCode());
        map.put("date", format(snippet.getCreation()));

        map.put("time", a);
        map.put("views", snippet.getViewRestriction());
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }


}
