package ru.otus.dz6.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dz6.domain.Author;
import ru.otus.dz6.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations npjdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations npjdbc) {
        this.npjdbc = npjdbc;
    }

    @Override
    public void insert(Book book) {

//        int authorId = authorDao.getByName(authorName);
//        if (authorId == 0) {
//            authorDao.insert(new Author(1,authorName));
//            authorId = authorDao.getByName(authorName);
//        }

        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("name", book.getName());
//        params.put("idAuthor", book.getIdAuthor());
//        params.put("idGenre", book.getIdGenre());
        params.put("idAuthor", 1);
        params.put("idGenre", 1);
        npjdbc.update("insert into books ( `name`, `author_id`, `genre_id`) " +
                        "values ( :name, :idAuthor, :idGenre)", params);
    }

    @Override
    public List<Book> getAll(){
//        return npjdbc.query("select * from books", new BookMapper());
        return npjdbc.query("select books.id, books.name, authors.name, genres.name  from books " +
                "join authors on books.author_id = authors.id " +
                "join genres on books.genre_id = genres.id", new BookMapper());

    }

    @Override
    public int count() {
        final HashMap<String, Object> params = new HashMap<>(1);
        return npjdbc.queryForObject("select count(*) from books", params, Integer.class);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("books.id");
            String name = resultSet.getString("books.name");
//            int idAuthor = resultSet.getInt("books.author_id");
//            int idGenre = resultSet.getInt("books.genre_id");
            String author = resultSet.getString("authors.name");
            String genre = resultSet.getString("genres.name");
            return new Book(id, name, author, genre);
//            return new Book(id, name, idAuthor, idGenre, author);

        }
    }
}
