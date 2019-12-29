package eu.stefanangelov.chatbot.botservice.to;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;


/**
 * UserMessage
 */
@ToString(exclude = "created" )
@Data
@NoArgsConstructor
@Validated
public class UserMessage implements Serializable {

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

