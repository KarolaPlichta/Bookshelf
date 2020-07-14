package com.example.bookshelf.comment.boundary;

import javax.validation.constraints.Size;

public class CommentContentRequest {

    @Size(max = 1000)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
