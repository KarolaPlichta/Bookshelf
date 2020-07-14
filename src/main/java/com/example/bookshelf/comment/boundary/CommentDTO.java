package com.example.bookshelf.comment.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public class CommentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @Size(max = 255)
    @NotBlank
    private String author;
    @Size(max = 1000)
    @NotBlank
    private String content;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

}
