package com.exercise.service.impl;

import com.exercise.model.Blog;
import com.exercise.model.Category;
import com.exercise.repository.IBlogRepository;
import com.exercise.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogService implements IBlogService {
    @Autowired
    IBlogRepository blogRepository;
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }


    @Override
    public Page<Blog> findByAuthorContainingAndCategory(Pageable pageable, String author, Category category) {
        return blogRepository.findByAuthorContainingAndCategory(pageable,author,category);
    }

    @Override
    public Page<Blog> findByAuthorContaining(Pageable pageable, String author) {
        return blogRepository.findByAuthorContaining(pageable, author);
    }
}