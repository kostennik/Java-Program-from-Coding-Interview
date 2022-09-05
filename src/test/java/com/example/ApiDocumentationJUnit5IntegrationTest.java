package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class ApiDocumentationJUnit5IntegrationTest {
    private static final String CLIENT_ID = "clientOne";
    private static final String CLIENT_SECRET = "secret";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String USERNAME = "myUsername";
    private static final String PASSWORD = "myPassword";
    private String accessToken;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .addFilter(springSecurityFilterChain)
                .build();

        //get Access Token
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", USERNAME);
        params.add("password", PASSWORD);

        ResultActions result = mockMvc.perform(post("/oauth/token")
                        .params(params)
                        .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                        .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));


        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        accessToken = jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void getToken() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", USERNAME);
        params.add("password", PASSWORD);

        this.mockMvc.perform(post("/oauth/token")
                        .params(params)
                        .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                        .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE));

    }

    @Test
    public void loadCsv() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "gps_pos.csv",
                MediaType.TEXT_PLAIN_VALUE,
                """
                position_id,latitude,longitude
                8e0b1e1a-e290-4344-8fa1-39de81c9d265,53.0,20.0
                65d6375e-b641-4791-ac74-8a84222b2ce9,53.1,20.1
                537b9506-e923-4c99-a8f4-1d02e4079408,53.2,20.2
                62ae4317-a2a3-4d74-997c-921932ff918f,53.3,20.3
                2884f5f9-e902-41a6-bc7d-e3569922025b,53.4,20.4
                """
                        .getBytes()
        );
        this.mockMvc.perform(multipart("/loadCsv")
                        .file(file)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    public void getCarsByLocation() throws Exception {
        this.mockMvc.perform(get("/getCars/{latitude}/{longitude}/{distance}", 53.90, 20.88, 100)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
