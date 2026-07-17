package com.simahyeon.vintagebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(StoreController.class)
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StoreRepository storeRepository;

    @Test
    void 존재하지_않는_매장_조회시_404와_에러코드를_반환한다() throws Exception {
        // given
        when(storeRepository.findById(9999L)).thenReturn(Optional.empty());

        // when & then
        mockMvc.perform(get("/api/stores/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("RESOURCE_NOT_FOUND"));
    }

    @Test
    void 매장이름_누락시_400과_에러코드를_반환한다() throws Exception {
        // given
        when(storeRepository.save(any(Store.class)))
                .thenThrow(new DataIntegrityViolationException("null value in column \"name\" violates not-null constraint"));

        String requestBody = """
            {
              "address": "테스트 주소"
            }
            """;

        // when & then
        mockMvc.perform(post("/api/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_REQUEST"));
    }
}