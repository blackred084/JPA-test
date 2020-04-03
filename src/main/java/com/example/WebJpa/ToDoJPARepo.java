package com.example.WebJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToDoJPARepo extends JpaRepository<ToDo,Long> {
    List<ToDo> findByTitle(String title);

    @Query("SELECT a FROM ToDo a WHERE a.title = :title ORDER BY a.title")
    List<ToDo> findByAuthorOrderByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM ToDo WHERE title = :title ORDER BY title",  nativeQuery = true)
    List<ToDo> findByTitleAndAuthor(@Param("title") String title);

}
