
package org.gov.qld.maintenance.request;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.gov.qld.maintenance.security.SecurityCongfigure;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.context.annotation.Import;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RequestController.class)
@Import(SecurityCongfigure.class)
public class RequestControlTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RequestService requestService;

    @Test
    public void processRequestPeritAllTest() throws Exception {

        mvc.perform(get("/maintenance/test/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("fake type")));
    }
    
    @Test
    public void processRequestPeritUserest() throws Exception {
        Request aRequest = new Request("requestA", Request.Priority.LOW, "first request!");
        requestService.saveRequest(aRequest);
        Mockito.when(requestService.getFirstRequest()).thenReturn(aRequest);

        mvc.perform(get("/maintenance/user/1")
        		.with(httpBasic("user", "userpassword")) // Mock authentication
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("requestA")))
                .andExpect(jsonPath("$.priority", is("LOW")))
                .andExpect(jsonPath("$.description", is("first request!")));
    }
   
}
