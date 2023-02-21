package dev.profitsoft.jfd.elasticsample.service;

import dev.profitsoft.jfd.elasticsample.dto.StudentSaveDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class StudentGenerator {

  private Random random = new Random();

  public Stream<StudentSaveDto> generateStudents(int count) {
    return IntStream.rangeClosed(1, count)
        .mapToObj(i -> generateStudent());
  }

  public StudentSaveDto generateStudent() {
    return StudentSaveDto.builder()
        .name(FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size())))
        .surname(LAST_NAMES.get(random.nextInt(LAST_NAMES.size())))
        .group(GROUPS.get(random.nextInt(GROUPS.size())))
        .birthDate(LocalDate.of(2005, 01, 01)
            .plusDays(random.nextInt(2000) - 1000))
        .build();
  }

  public static final List<String> GROUPS = List.of("GROUP-1",
      "GROUP-2",
      "GROUP-3",
      "GROUP-4",
      "GROUP-5");

  public static final List<String> FIRST_NAMES = List.of(
      "John",
      "Jane",
      "Bob",
      "Samantha",
      "Michael",
      "Emily",
      "Tom",
      "David",
      "Jessica",
      "Patrick",
      "Kimberly",
      "Matthew",
      "Karen",
      "Ryan",
      "Laura",
      "Brandon",
      "Melissa",
      "Tyler",
      "Brianna",
      "Nicholas"
  );

  public static final List<String> LAST_NAMES = List.of(
      "Smith",
      "Doe",
      "Johnson",
      "Brown",
      "Davis",
      "Williams",
      "Wilson",
      "Martin",
      "Lee",
      "Garcia",
      "Anderson",
      "Taylor",
      "Brown",
      "Miller",
      "Jones",
      "Thomas",
      "Hernandez",
      "Davis",
      "Gonzalez",
      "Moore"
  );

}
