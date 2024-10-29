package cz.library.store.security.infrastructure.usecase.login;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoginService loginService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void givenValidTokenRequestData_whenLogin_thenReturnOkResponseWithToken()
      throws Exception {
    TokenRequestData requestData = new TokenRequestData("validUsername", "ValidPassword123!");
    String token = "token";

    when(loginService.login(requestData))
        .thenReturn(ResponseEntity.ok(new TokenResponseData(token)));

    // when
    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestData)))

    // then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").value(token));

    verify(loginService, times(1)).login(requestData);
   }

}
