package com.falx.springboot.bootstrap;

import com.falx.springboot.domain.Author;
import com.falx.springboot.domain.Book;
import com.falx.springboot.domain.Publisher;
import com.falx.springboot.repositories.AuthorRepository;
import com.falx.springboot.repositories.BookRepository;
import com.falx.springboot.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher blaise = new Publisher("Blaise Mubangizi", "Kampala, Uganda");
        publisherRepository.save(blaise);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123212");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(blaise);
        blaise.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(blaise);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3323743");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(blaise);
        blaise.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(blaise);

        System.out.println("Started in Bootstrap");
        System.out.println("Books: " + bookRepository.findAll());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publishers: " + publisherRepository.findAll());
        System.out.println("Books published by Blaise: " + blaise.getBooks());
    }
}
