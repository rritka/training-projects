package noteWeb.api;

import noteWeb.Mapper;
import noteWeb.api.view.NoteView;
import noteWeb.db.Notebookdb;
import noteWeb.db.Notedb;
import noteWeb.note.Note;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private Notedb notedb;
    private Notebookdb notebookdb;
    private Mapper mapper;

    public NoteController(Notedb noteRepository, Notebookdb notebookRepository, Mapper mapper) {
        this.notedb = noteRepository;
        this.notebookdb = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteView> all() {
        var notes = this.notedb.findAll();

        // map from entity to view model
        var notesView = notes.stream()
                .map(note -> this.mapper.convertToNoteView(note))
                .collect(Collectors.toList());

        return notesView;
    }

    @GetMapping("/byId/{id}")
    public NoteView byId(@PathVariable String id) {
        var note = this.notedb.findById(UUID.fromString(id)).orElse(null);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        var noteView = this.mapper.convertToNoteView(note);

        return noteView;
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteView> byNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();

        var notebook = this.notebookdb.findById(UUID.fromString(notebookId));
        if (notebook.isPresent()) {
            notes = this.notedb.findAllByNotebook(notebook.get());
        }

        // map to note view model
        var notesView = notes.stream()
                .map(note -> this.mapper.convertToNoteView(note))
                .collect(Collectors.toList());

        return notesView;
    }

    @PostMapping
    public Note save(@RequestBody NoteView noteCreateView, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var noteEntity = this.mapper.convertToNoteEntity(noteCreateView);

        // save note instance to db
        this.notedb.save(noteEntity);

        return noteEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.notedb.deleteById(UUID.fromString(id));
    }
}
