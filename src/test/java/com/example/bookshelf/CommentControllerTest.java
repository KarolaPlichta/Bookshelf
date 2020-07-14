package com.example.bookshelf;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestObject testObject;

    @Test
    void shouldReturnCommentWhenGetById() throws Exception {
        var commentId = testObject.existingComment().getId();

        this.mockMvc.perform(get("/comment/" + commentId))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"id\":\"" + commentId + "\"," +
                        "\"author\":\"Some author\"," +
                        "\"content\":\"Content of the comment\"," +
                        "\"creationTime\":\"2020-02-20T12:21:00\"" +
                        "}")));
    }

    @Test
    void shouldReturnNotFoundWhenGetByNotExistingId() throws Exception {
        this.mockMvc.perform(get("/comment/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldChangeCommentsContent() throws Exception {
        var commentId = testObject.existingComment().getId();

        this.mockMvc.perform(patch("/comment/" + commentId)
                .content("{" +
                        "\"content\": \"New comment content\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{" +
                        "\"id\":\"" + commentId + "\"," +
                        "\"author\":\"Some author\"," +
                        "\"content\":\"New comment content\"," +
                        "\"creationTime\":\"2020-02-20T12:21:00\"" +
                        "}")));
    }

    @Test
    void shouldReturnNotFoundWhenChangeContentOfNotExistingId() throws Exception {
        this.mockMvc.perform(patch("/comment/" + UUID.randomUUID())
                .content("{" +
                        "\"content\": \"New comment content\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenNoContentGiven() throws Exception {
        this.mockMvc.perform(patch("/comment/" + UUID.randomUUID()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenContentIsTooLong() throws Exception {
        var commentId = testObject.existingComment().getId();
        this.mockMvc.perform(patch("/comment/" + commentId)

                .content("{" +
                        "\"content\": \"Some very loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooon looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong content\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNoContentWhenDelete() throws Exception {
        var commentId = testObject.existingComment().getId();
        this.mockMvc.perform(delete("/comment/" + commentId))
                .andExpect(status().isNoContent());
    }


}
