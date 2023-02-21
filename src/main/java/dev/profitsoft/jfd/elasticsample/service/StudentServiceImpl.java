package dev.profitsoft.jfd.elasticsample.service;

import dev.profitsoft.jfd.elasticsample.data.AddressData;
import dev.profitsoft.jfd.elasticsample.data.StudentData;
import dev.profitsoft.jfd.elasticsample.dto.AddressDto;
import dev.profitsoft.jfd.elasticsample.dto.StudentSaveDto;
import dev.profitsoft.jfd.elasticsample.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  public static final int BATCH_SIZE = 20;
  private final StudentRepository studentRepository;

  private final StudentGenerator studentGenerator;

  @Override
  public String createStudent(StudentSaveDto dto) {
    validateStudent(dto);
    StudentData data = convertToNewStudentData(dto);
    StudentData saved = studentRepository.save(data);
    return saved.getId();
  }

  @Override
  public void generateStudents(int count) {
    log.info("Started generating {} students", count);
    for (int i = 0; i < count; i += BATCH_SIZE) {
      int batchSize = Math.min(BATCH_SIZE, count - i);
      List<StudentData> students = studentGenerator.generateStudents(batchSize)
          .map(this::convertToNewStudentData)
              .toList();
      studentRepository.saveAll(students);
      log.info("Saved {} students from {}", i + batchSize, count);
    }
  }

  private static void validateStudent(StudentSaveDto dto) {
    if (dto.getBirthDate() != null && dto.getBirthDate().isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("birthDate should be before now");
    }
  }

  private StudentData convertToNewStudentData(StudentSaveDto dto) {
    StudentData data = new StudentData();
    updateDataFromDto(data, dto);
    return data;
  }

  private void updateDataFromDto(StudentData data, StudentSaveDto dto) {
    data.setName(dto.getName());
    data.setSurname(dto.getSurname());
    data.setBirthDate(toInstant(dto.getBirthDate()));
    data.setGroup(dto.getGroup());
    data.setPhoneNumbers(dto.getPhoneNumbers());
    data.setAddress(convertAddress(dto.getAddress()));
    data.setLastUpdateTime(Instant.now());
  }

  private static AddressData convertAddress(AddressDto dto) {
    if (dto == null) {
      return null;
    }
    AddressData result = new AddressData();
    result.setCountry(dto.getCountry());
    result.setTown(dto.getTown());
    result.setAddressString(dto.getAddressString());
    return result;
  }

  private static Instant toInstant(LocalDate value) {
    if (value == null) {
      return null;
    }
    return value.atStartOfDay().toInstant(ZoneOffset.UTC);
  }

}
