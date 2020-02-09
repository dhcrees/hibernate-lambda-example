package hibernate.lambda.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@NamedQueries({
        @NamedQuery(name = "Author.findByName",
                query = "SELECT a FROM Author a WHERE a.name = :name")
})
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
