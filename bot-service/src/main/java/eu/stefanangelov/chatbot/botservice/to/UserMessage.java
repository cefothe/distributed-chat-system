package eu.stefanangelov.chatbot.botservice.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


/**
 * UserMessage
 */
@Data
@NoArgsConstructor
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-26T20:27:12.373Z[GMT]")

public class UserMessage   {
  @JsonProperty("messageId")
  private String messageId = null;

  @JsonProperty("userIdentificator")
  private String userIdentificator = null;

  @JsonProperty("created")
  private Date created = null;

  @JsonProperty("content")
  private String content = null;
}

