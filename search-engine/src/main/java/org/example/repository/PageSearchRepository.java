package org.example.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.lucene.search.Query;
import org.example.model.Page;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class PageSearchRepository {

	@Autowired
	private EntityManagerFactory factory;

	@Value("${query.max.result}")
	private int maxResult;

	private FullTextEntityManager fullTextEntityManager;
	private EntityManager entityManager;

	@PostConstruct
	public void initalize() throws InterruptedException {
		entityManager = factory.createEntityManager();
		fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().startAndWait();
	}

	@Transactional(TxType.REQUIRES_NEW)
	public List<Page> findPagesByTags(String queryString) {
		// entityManager.getTransaction().begin();
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Page.class).get();
		Query luceneQuery = queryBuilder.keyword().onFields("tags").matching(queryString.replaceAll("_", " ")).createQuery();
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Page.class);
		@SuppressWarnings("unchecked")
		List<Page> result = jpaQuery.setMaxResults(maxResult).getResultList();
		// entityManager.getTransaction().commit();
		return result;
	}

	@PreDestroy
	public void destroy() {
		entityManager.close();
		factory.close();
	}
}
