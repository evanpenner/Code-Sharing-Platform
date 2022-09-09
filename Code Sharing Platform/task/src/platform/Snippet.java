package platform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Snippet implements Comparable<Snippet> {
    @Id
    private UUID id;
    private String code;
    @CreatedDate
    private Instant creation;
    boolean hasViewRestriction;
    int viewRestriction;
    int timeRestricition;

    public Snippet(String code) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.creation = Instant.now();
        this.viewRestriction = 0;
        this.timeRestricition = 0;
    }

    public Snippet(String code, int viewRestriction, int timeRestricition) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.creation = Instant.now();
        this.viewRestriction = viewRestriction;
        this.timeRestricition = timeRestricition;
        if (viewRestriction != 0)
            hasViewRestriction = true;
        else hasViewRestriction = false;
    }

    @Override
    public int compareTo(Snippet snippet) {
        return snippet.getCreation().compareTo(this.getCreation());
        //return this.getCreation().compareTo(snippet.getCreation());
    }

}
