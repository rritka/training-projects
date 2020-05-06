DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS note_book;


CREATE TABLE note_book (
   id VARCHAR(250)  PRIMARY KEY,
   name VARCHAR(250),
 );

CREATE TABLE note (
  id VARCHAR(250)  PRIMARY KEY,
  tittle VARCHAR(250),
  text VARCHAR(250),
  notebook_id VARCHAR(250),
  last_modified_on date,
  FOREIGN KEY (`notebook_id`) REFERENCES `note_book` (`id`)
);

INSERT INTO note_book (id, name) VALUES
  ('id1', 'notebook1');
INSERT INTO note (id, tittle, text, notebook_id) VALUES
  ('idNote1', 'note1', 'Don''t give up, Ryta', 'id1'),
  ('idNote2','note2', 'Learn Java and train your brain!', 'id1');