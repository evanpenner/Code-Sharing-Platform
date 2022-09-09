package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, UUID> {
List<Snippet> findAll();
}
