package noteWeb.note;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * class create one note
 */
@Entity
@Table(name = "note")
public class Note {

    @Id
    private UUID id;
    @Column
    private String title;
    @Column
    private String text;

    @Column(name = "last_modified_on")
    private Date lastModifiedOn;


    @ManyToOne
    @JoinColumn(name="notebook_id")
    private Notebook notebook;

    protected Note() {
        //its better to generate id inside db, since db knows which of ids has already been generated
        this.id = UUID.randomUUID();
        this.lastModifiedOn = new Date();
    }

    public Note(String title, String text, Notebook notebook) {
        this();
        this.title = title;
        this.text = text;
        this.notebook = notebook;
    }

    public Note(String id, String title, String text, Notebook notebook) {
        this(title, text, notebook);
        if (id != null) {
            this.id = UUID.fromString(id);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public String getNotebookId() {
        return this.notebook.getId().toString();
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
}
