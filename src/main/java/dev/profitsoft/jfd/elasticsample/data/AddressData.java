package dev.profitsoft.jfd.elasticsample.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class AddressData {

  @Field(type = FieldType.Keyword)
  private String country;

  @Field(type = FieldType.Keyword)
  private String town;

  @Field(type = FieldType.Text)
  private String addressString;

}
