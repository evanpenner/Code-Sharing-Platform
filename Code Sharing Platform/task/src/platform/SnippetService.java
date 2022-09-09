package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class SnippetService {
    @Autowired
    private SnippetRepository snippetRepo;

    public SnippetService() {

    }

    public Snippet checkSnippet(Snippet snippet) {
        if (snippet == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (snippet.getTimeRestricition() != 0) {
            if ((snippet.getTimeRestricition() - snippet.getCreation().until(Instant.now(), ChronoUnit.SECONDS)) <= 0) {
                snippetRepo.delete(snippet);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        if (snippet.viewRestriction != 0) {
            snippet.viewRestriction--;
            if (snippet.viewRestriction == 0) {
                //snippetRepo.delete(snippet);
                snippetRepo.delete(snippet);
                return snippet;
                //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            snippetRepo.save(snippet);
        }
        return snippet;
    }

    public Snippet getSnippet(UUID id) {
        return checkSnippet(snippetRepo.findById(id).orElse(null));
    }

    public List<Snippet> getLatestSnippets() {
        List<Snippet> snips = snippetRepo.findAll();
        snips.removeIf(s -> s.getViewRestriction() != 0 || s.getTimeRestricition() != 0);
        return snips.stream().sorted().limit(10).toList();
    }

    public UUID putSnippet(Snippet snippet) {
        return snippetRepo.save(snippet).getId();
    }
}
