package com.example.WebJpa;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ToDoPagingRepo extends PagingAndSortingRepository<ToDo,Long> {
}
