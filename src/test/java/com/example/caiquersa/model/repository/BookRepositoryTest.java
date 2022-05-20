package com.example.caiquersa.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import api.model.Entity.Book;
import api.model.repository.BookRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	BookRepository repository;
	
	
	@Test
	@DisplayName("Deve retormar verdadeiro quando existir um livro na base com isbn informado")
	public void returnTrueWhenIsbnExists() {
//		cenario
		String isbn = "123";
		Book book = Book.builder().title("Aventuras").author("fulano").isbn(isbn).build();
		entityManager.persist(book);
//		execução
		boolean exists = repository.existsByIsbn(isbn);
//		Verificação
		assertThat(exists).isTrue();
	}
	
	@Test
	@DisplayName("Deve retormar falso quando não existir um livro na base com isbn informado")
	public void returnFalseWhenIsbnDoesntExists() {
//		cenario
		String isbn = "123";
		
//		execução
		boolean exists = repository.existsByIsbn(isbn);
//		Verificação
		assertThat(exists).isFalse();
	}
	
}
