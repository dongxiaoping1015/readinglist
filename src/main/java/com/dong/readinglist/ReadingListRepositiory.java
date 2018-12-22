package com.dong.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepositiory extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
