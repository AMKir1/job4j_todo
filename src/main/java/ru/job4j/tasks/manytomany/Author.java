//package ru.job4j.tasks.manytomany;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Entity
//@Table(name = "authors")
//public class Author {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
////    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Book> books = new ArrayList<>();
//
//    public Author() {
//    }
//
//    public Author(long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public static Author of(String name) {
//        Author author = new Author();
//        author.setName(name);
//        return author;
//    }
//
//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void addBook(Book book) {
//        this.books.add(book);
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Author{"
//                + "id=" + id
//                + ", name='" + name + '\''
//                + '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Author author = (Author) o;
//        return id == author.id &&
//                Objects.equals(name, author.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//}
