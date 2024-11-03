package com.crio.starter.data;


import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "memes")
@Getter
@Setter
public class Meme {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String name;

    @NotBlank
    private String caption;

    @NotBlank
    private String url;

    private long createdAt;
    private long lastUpdatedAt;
}
