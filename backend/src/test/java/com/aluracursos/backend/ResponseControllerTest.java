package com.aluracursos.backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pruebas para el Controlador de Respuestas")
public class ResponseControllerTest {

    private static final String SOLUCIONES_ENDPOINT = "/respuestas/soluciones";
    private static final String TOPICO_ID_PARAM = "topicId";

    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithMockUser(username = "pedro", roles = {"USER"})
    @DisplayName("Debe devolver 200 OK y una lista al solicitar todas las soluciones")
    void debeListarRespuestasSolucionadasSinFiltro() throws Exception {
        mockMvc.perform(get(SOLUCIONES_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    @Test
    @WithMockUser(username = "pedro", roles = {"USER"}) // o el rol que necesite el endpoint
    @DisplayName("Debe devolver 200 OK y una lista al filtrar soluciones por un tópico existente")
    void debeListarRespuestasSolucionadasFiltradasPorTopico() throws Exception {
        Long topicoId = 1L;

        mockMvc.perform(get(SOLUCIONES_ENDPOINT)
                        .param(TOPICO_ID_PARAM, topicoId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    @WithMockUser(username = "pedro", roles = {"USER"}) // o el rol que necesite el endpoint
    @DisplayName("Debe devolver 200 OK y una lista vacía al filtrar por un tópico inexistente")
    void noDebeListarRespuestasCuandoTopicoIdNoExiste() throws Exception {
        Long topicoIdInexistente = 9999L;

        mockMvc.perform(get(SOLUCIONES_ENDPOINT)
                        .param(TOPICO_ID_PARAM, topicoIdInexistente.toString()))
                .andExpect(status().isOk()) // O puedes esperar 404 si es lo que retorna tu controlador
                .andExpect(jsonPath("$").isEmpty());
    }
}
