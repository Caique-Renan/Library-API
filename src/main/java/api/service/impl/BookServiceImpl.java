package api.service.impl;

import org.springframework.stereotype.Service;

import api.exception.BusinessException;
import api.model.Entity.Book;
import api.model.repository.BookRepository;
import api.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	private BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public Book save(Book book) {
		if(repository.existsByIsbn(book.getIsbn()) ) {
			throw new BusinessException("Isbn já cadastrado.");
		}
		return repository.save(book);
	}

}
