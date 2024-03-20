package de.nikoconsulting.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.nikoconsulting.demo.model.GenericEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void whenReadAll_thenStatusIsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/entity/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenReadOne_thenStatusIsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/entity/findby/" + 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("entity_1"));
    }

    @Test
    public void whenReadFive_thenStatusIsNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/entity/findby/" + 5))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void whenCreateNew_thenStatusIsCreated() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v3/entity")
                        .content(asJsonString(new GenericEntity(5L, "test_entity_5")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void whenCreateSame_thenStatusIsCreated() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v3/entity")
                        .content(asJsonString(new GenericEntity(4L, "test_entity_4")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void whenDelete_thenStatusIsNotImpl() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v3/entity/" + 1))
                .andExpect(MockMvcResultMatchers.status().isNotImplemented());
    }

}
