package org.java.advanced.two.module.one.bookstore;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao {
    private final EntityManager em;

    public BookDao(EntityManager em) {
        this.em = em;
    }

    public List<Book> findBooksByParams(List<String> titles) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Book> book = cq.from(Book.class);

        for (String title : titles
        ) {
            Predicate predicate = cb.like(
                    cb.lower(book.get("title")), "%" + title + "%");
            predicates.add(predicate);
        }
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }
}
