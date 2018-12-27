package eu.stefanangelov.chatbot.botservice.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * UserMessage
 */
@Data
@NoArgsConstructor
@Validated
public class UserMessage {
  @JsonProperty("messageId")
  @NotNull
  @NotEmpty
  private String messageId = null;

  @JsonProperty("userIdentificator")
  @NotNull
  @NotEmpty
  private String userIdentificator = null;

  @JsonProperty("created")
  @NotNull
  private Date created = null;

  @JsonProperty("content")
  @NotNull
  @NotEmpty
  private String content = null;
}

