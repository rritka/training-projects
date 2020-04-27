package noteWeb.db;

import noteWeb.note.Note;
import noteWeb.note.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface Notedb extends JpaRepository<Note, UUID> {
        List<Note> findAllByNotebook(Notebook notebook);
        }

