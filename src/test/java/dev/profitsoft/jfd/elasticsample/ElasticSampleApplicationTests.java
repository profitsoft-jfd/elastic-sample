package dev.profitsoft.jfd.elasticsample;

import dev.profitsoft.jfd.elasticsample.config.TestElasticsearchConfiguration;
import dev.profitsoft.jfd.elasticsample.data.StudentData;
import dev.profitsoft.jfd.elasticsample.dto.StudentSaveDto;
import dev.profitsoft.jfd.elasticsample.service.StudentService;
import dev.profitsoft.jfd.elasticsample.service.StudentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {ElasticSampleApplication.class, TestElasticsearchConfiguration.class})
class ElasticSampleApplicationTests {

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  @Autowired
  private StudentService studentService;

  @BeforeEach
  public void beforeEach() {
    elasticsearchOperations.indexOps(StudentData.class).createMapping();
  }

  @AfterEach
  public void afterEach() {
    elasticsearchOperations.indexOps(StudentData.class).delete();
  }

  @Test
  void testCreateStudent() {
    StudentSaveDto student = StudentSaveDto.builder()
        .name("Taras")
        .surname("Shevchenko")
        .group("Group-1")
        .phoneNumbers(List.of("380501234567", "380501112233"))
        .build();
    String studentId = studentService.createStudent(student);

    StudentData loaded = elasticsearchOperations.get(studentId, StudentData.class);
    assertThat(loaded.getName()).isEqualTo(student.getName());
    assertThat(loaded.getSurname()).isEqualTo(student.getSurname());
    assertThat(loaded.getGroup()).isEqualTo(student.getGroup());
    assertThat(loaded.getPhoneNumbers()).isEqualTo(student.getPhoneNumbers());
  }

  @Test
  void testGenerateStudents() {
    int count = StudentServiceImpl.BATCH_SIZE * 2 + 5;
    studentService.generateStudents(count);

    long actualCount = elasticsearchOperations.count(Query.findAll(), StudentData.class);
    assertThat(actualCount).isEqualTo(count);
  }

}
