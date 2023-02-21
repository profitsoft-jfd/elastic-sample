package dev.profitsoft.jfd.elasticsample.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@RequiredArgsConstructor
public class RestResponse {

  public static final RestResponse OK = RestResponse
      .builder()
      .result("OK")
      .build();

  private final String result;

}
