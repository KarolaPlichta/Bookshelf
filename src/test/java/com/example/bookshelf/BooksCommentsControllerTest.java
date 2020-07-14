package com.example.bookshelf;

import com.example.bookshelf.comment.boundary.CommentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BooksCommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestObject testObject;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnPaginatedComments() throws Exception {
        var bookId = testObject.existingBookWithComments().getId();

        this.mockMvc.perform(get("/book/" + bookId + "/comment")
                .queryParam("pageNumber", "0")
                .queryParam("elementsOnSite", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"totalPages\":2")))
                .andExpect(content().string(containsString("\"totalElements\":3")))
                .andExpect(content().string(containsString("\"pageNumber\":0")))
                .andExpect(content().string(containsString("\"elements\":")));
    }

    @Test
    void shouldAddCommentToBook() throws Exception {
        var bookId = testObject.existingBook().getId();
        var requestBody = commentDTOAsJson();

        this.mockMvc.perform(post("/book/" + bookId + "/comment")
                .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"id\"")))
                .andExpect(content().string(containsString("\"author\":\"New Author\"")))
                .andExpect(content().string(containsString("\"content\":\"New Content\"")))
                .andExpect(content().string(containsString("\"creationTime\"")));

    }

    private String commentDTOAsJson() throws JsonProcessingException {
        var commentDTO = new CommentDTO();
        commentDTO.setAuthor("New Author");
        commentDTO.setContent("New Content");
        return objectMapper.writeValueAsString(commentDTO);
    }
}
