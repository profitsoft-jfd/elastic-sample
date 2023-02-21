package dev.profitsoft.jfd.elasticsample.controller;

import dev.profitsoft.jfd.elasticsample.dto.RestResponse;
import dev.profitsoft.jfd.elasticsample.dto.StudentSaveDto;
import dev.profitsoft.jfd.elasticsample.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestResponse createStudent(@Valid @RequestBody StudentSaveDto dto) {
    String id = studentService.createStudent(dto);
    return new RestResponse(String.valueOf(id));
  }

  @PostMapping("/generate")
  public RestResponse createStudent(@RequestParam("count") int count) {
    studentService.generateStudents(count);
    return RestResponse.OK;
  }

}
