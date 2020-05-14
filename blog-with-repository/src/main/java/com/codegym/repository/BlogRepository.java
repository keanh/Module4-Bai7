package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    Page<Blog> findAllByNameContaining(String name, Pageable pageable);
    Iterable<Blog> findAllByCategory(Category category);
    List<Blog> findAllByName(String name);
}
