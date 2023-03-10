package dev.profitsoft.jfd.elasticsample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Jacksonized
public class StudentSaveDto {

  @NotBlank(message = "name is required")
  private String name;

  @NotBlank(message = "surname is required")
  private String surname;

  private LocalDate birthDate;

  @NotNull(message = "group is required")
  private String group;

  private List<String> phoneNumbers;

  private AddressDto address;

}
