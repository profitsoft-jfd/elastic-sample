package dev.profitsoft.jfd.elasticsample.repository;

import dev.profitsoft.jfd.elasticsample.data.StudentData;
import org.springframework.data.repository.CrudRepository;

/**
 * See docs here:
 * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.query-methods.criterions
 */
public interface StudentRepository extends CrudRepository<StudentData, String> {

}
