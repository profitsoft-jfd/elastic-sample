package dev.profitsoft.jfd.elasticsample.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;

/**
 * See docs here:
 * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.repositories
 */
@Getter
@Setter
@Document(indexName="students")
public class StudentData {

  @Id
  private String id;

  @Field(type = FieldType.Text)
  private String name;

  @Field(type = FieldType.Text)
  private String surname;

  @Field(type = FieldType.Date)
  private Instant birthDate;

  @Field(type = FieldType.Keyword)
  private String group;

  @Field(type = FieldType.Keyword)
  private List<String> phoneNumbers;

  private AddressData address;

  @Field(type = FieldType.Date)
  private Instant lastUpdateTime;
}
