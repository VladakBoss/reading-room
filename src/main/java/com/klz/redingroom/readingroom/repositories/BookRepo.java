package com.klz.redingroom.readingroom.repositories;

import com.klz.redingroom.readingroom.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {

}
