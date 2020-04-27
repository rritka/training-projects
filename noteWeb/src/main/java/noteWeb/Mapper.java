package noteWeb;

//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.ObjectCodec;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.node.TextNode;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import noteWeb.api.view.NoteView;
import noteWeb.api.view.NotebookView;
import noteWeb.db.Notebookdb;
import noteWeb.note.Note;
import noteWeb.note.Notebook;
import org.springframework.stereotype.Component;

//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class Mapper {
    private Notebookdb notebookdb;

    public Mapper(Notebookdb notebookdb) {
        this.notebookdb = notebookdb;
    }

    public NoteView convertToNoteView(Note entity) {
        var viewModel = new NoteView();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(entity.getId().toString());
        viewModel.setLastModifiedOn(entity.getLastModifiedOn());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNotebook().getId().toString());

        return viewModel;
    }

    public Note convertToNoteEntity(NoteView viewModel) {
        var notebook = this.notebookdb.findById(UUID.fromString(viewModel.getNotebookId())).get();
        var entity = new Note(viewModel.getId(), viewModel.getTitle(), viewModel.getText(), notebook);

        return entity;
    }

    public NotebookView convertToNotebookView(Notebook entity) {
        var viewModel = new NotebookView();
        viewModel.setId(entity.getId().toString());
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNotes().size());

        return viewModel;
    }

    public Notebook convertToNotebookEntity(NotebookView viewModel) {
        var entity = new Notebook(viewModel.getId(), viewModel.getName());

        return entity;
    }
}
