package eu.stefanangelov.chatbot.botservice.to;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserMessage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-26T20:27:12.373Z[GMT]")

public class UserMessage   {
  @JsonProperty("messageId")
  private String messageId = null;

  @JsonProperty("userIdentificator")
  private String userIdentificator = null;

  @JsonProperty("created")
  private OffsetDateTime created = null;

  @JsonProperty("content")
  private String content = null;

  public UserMessage messageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  /**
   * Get messageId
   * @return messageId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public UserMessage userIdentificator(String userIdentificator) {
    this.userIdentificator = userIdentificator;
    return this;
  }

  /**
   * Get userIdentificator
   * @return userIdentificator
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUserIdentificator() {
    return userIdentificator;
  }

  public void setUserIdentificator(String userIdentificator) {
    this.userIdentificator = userIdentificator;
  }

  public UserMessage created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public UserMessage content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserMessage userMessage = (UserMessage) o;
    return Objects.equals(this.messageId, userMessage.messageId) &&
        Objects.equals(this.userIdentificator, userMessage.userIdentificator) &&
        Objects.equals(this.created, userMessage.created) &&
        Objects.equals(this.content, userMessage.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId, userIdentificator, created, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserMessage {\n");
    
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    userIdentificator: ").append(toIndentedString(userIdentificator)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

