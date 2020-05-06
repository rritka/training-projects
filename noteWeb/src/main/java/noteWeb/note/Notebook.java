package noteWeb.note;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * class created Notebooks of Notes
 */

@Entity
//named 'note_book' to avoid misunderstanding with field notebook in Notebook.java
@Table(name = "note_book")
public class Notebook {

    @Id
    private UUID id;
    @Column
    private String name;

    @OneToMany(mappedBy="notebook")
    private List<Note> notes;


    protected Notebook() {
        this.id = UUID.randomUUID();
        this.notes = new ArrayList<>();
    }

    public Notebook(String name) {
        this();
        this.name = name;
    }

    public Notebook(String id, String name) {
        this();
        if (id != null) {
            this.id = UUID.fromString(id);
        }
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public int getNbOfNotes() {
        return this.notes.size();
    }
}
