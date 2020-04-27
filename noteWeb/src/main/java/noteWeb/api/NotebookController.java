package noteWeb.api;

import noteWeb.Mapper;
import noteWeb.api.view.NotebookView;
import noteWeb.db.Notebookdb;
import noteWeb.note.Notebook;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {
    private Notebookdb notebookdb;
    private Mapper mapper;

    public NotebookController(Notebookdb notebookdb, Mapper mapper) {
        this.notebookdb = notebookdb;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Notebook> all() {
        var allCategories = this.notebookdb.findAll();
        return allCategories;
    }

    @PostMapping
    public Notebook save(@RequestBody NotebookView notebookView,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        var notebookEntity = this.mapper.convertToNotebookEntity(notebookView);

        // save notebookEntity instance to db
        this.notebookdb.save(notebookEntity);

        return notebookEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.notebookdb.deleteById(UUID.fromString(id));
    }
}
