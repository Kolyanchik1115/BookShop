package com.application.bookstore.repository.impl;

import com.application.bookstore.exception.DataProcessingException;
import com.application.bookstore.exception.EntityNotFoundException;
import com.application.bookstore.model.Book;
import com.application.bookstore.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class BookRepositoryImpl implements BookRepository {
    private static final String SAVING_FAILURE_MESSAGE = "Can't save book {%s} to database!";
    private static final String FIND_BY_ID_FAILURE_MESSAGE = "Can't get book with id %d!";
    private static final String FIND_ALL_FAILURE_MESSAGE = "Can't find all books from DB";

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(String.format(SAVING_FAILURE_MESSAGE, book), ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format(FIND_BY_ID_FAILURE_MESSAGE, id), e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("FROM Book b", Book.class).getResultList();
        } catch (Exception ex) {
            throw new EntityNotFoundException(FIND_ALL_FAILURE_MESSAGE, ex);
        }
    }

}
