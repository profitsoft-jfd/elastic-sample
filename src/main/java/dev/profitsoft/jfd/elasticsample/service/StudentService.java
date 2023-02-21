package dev.profitsoft.jfd.elasticsample.service;

import dev.profitsoft.jfd.elasticsample.dto.StudentSaveDto;

public interface StudentService {

  String createStudent(StudentSaveDto dto);

  void generateStudents(int count);

}
