package com.example.WebJpa;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Runner implements ApplicationRunner {
    ToDoRepository toDoRepository;
    ToDoJPARepo toDoJPARepo;
    ToDoPagingRepo toDoPagingRepo;

    @Autowired
    public Runner(ToDoRepository toDoRepository, ToDoJPARepo toDoJPARepo, ToDoPagingRepo toDoPagingRepo) {
        this.toDoRepository = toDoRepository;
        this.toDoJPARepo = toDoJPARepo;
        this.toDoPagingRepo = toDoPagingRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Fairy fairy = Fairy.create();
            Company company = fairy.company();
            Person person = fairy.person();
            ToDo toDo = new ToDo(company.getName(), person.getFirstName());
            toDoRepository.save(toDo);
        }
        System.out.println(toDoPagingRepo.findAll(Sort.by(Sort.Direction.DESC, "author")));
        System.out.println(toDoPagingRepo.findAll(Sort.by(Sort.Order.asc("author"), Sort.Order.desc("title"))));
        System.out.println(toDoPagingRepo.findAll(Sort.by("author")));
        List<Long> list = Arrays.asList(90L, 53L);
        System.out.println(toDoJPARepo.findAllById(list));
        System.out.println(toDoJPARepo.findById(1L));
        Page<ToDo> page = toDoPagingRepo.findAll(PageRequest.of(3, 5));
        System.out.println(page.getContent());
        System.out.println(page);
        Page<ToDo> page1 = toDoPagingRepo.findAll(PageRequest.of(2, 10, Sort.by("title")));
        System.out.println(page1);
        System.out.println(page1.getContent());
        Page<ToDo> page2 = toDoPagingRepo.findAll(PageRequest.of(2, 10, Sort.Direction.DESC, "title"));
        System.out.println(page2);
        System.out.println(page2.getContent());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getSize());
        System.out.println(toDoJPARepo.findByTitle("Vitae"));
        System.out.println(toDoJPARepo.findByAuthorOrderByTitle("Terson"));
//        System.out.println(toDoJPARepo.findByTitleAndAuthor("Robutenia"));
    }
}
