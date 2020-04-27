package noteWeb.db;

import noteWeb.note.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Notebookdb extends JpaRepository<Notebook, UUID> {
}
