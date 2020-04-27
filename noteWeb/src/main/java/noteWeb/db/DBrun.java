package noteWeb.db;

import noteWeb.note.Note;
import noteWeb.note.Notebook;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Component
@ConditionalOnProperty(name = "noteWeb.db.recreate", havingValue = "true")
public class DBrun implements CommandLineRunner {
    private Notebookdb notebookdb;
    private Notedb notedb;

    public DBrun(Notebookdb notebookRepository,
                 Notedb noteRepository) {
        this.notebookdb = notebookRepository;
        this.notedb = noteRepository;
    }


    @Override
    public void run(String... args) {
        // Remove all existing entities
        this.notebookdb.deleteAll();
        this.notedb.deleteAll();


        // Save a default notebook
        var defaultNotebook = new Notebook("Default");
        this.notebookdb.save(defaultNotebook);

        var quotesNotebook = new Notebook("Quotes");
        this.notebookdb.save(quotesNotebook);

        // Save the welcome note
        var note = new Note("Hello", "Welcome to Note It", defaultNotebook);
        this.notedb.save(note);

        // Save a quote note
        var quoteNote = new Note("Latin Quote", "Carpe Diem", quotesNotebook);
        this.notedb.save(quoteNote);

        System.out.println("Initialized database");
    }
}
