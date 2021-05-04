package info.wallyson.tcsstore.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.wallyson.tcsstore.client.ports.ClientRepository;
import info.wallyson.tcsstore.rest.config.ClientBeans;
import info.wallyson.tcsstore.rest.controller.client.ClientController;
import info.wallyson.tcsstore.rest.exception.RestExceptionHandler;
import info.wallyson.tcsstore.rest.util.ClientCreator;
import info.wallyson.tcsstore.rest.util.ClientRequestCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(
    classes = {ClientBeans.class, ClientController.class, RestExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTests {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired private MockMvc mockMvc;
  @MockBean private ClientRepository clientRepository;

  private final String BASE_URL = "/api/v1/client";

  @Test
  @DisplayName("Should request creation of client")
  void shouldRequestClientCreation() throws Exception {
    var client = ClientRequestCreator.create();

    when(clientRepository.existsByCpf(client.getCpf())).thenReturn(false);

    this.mockMvc
        .perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(client)))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Should return bad request when creating client that already exists with same CPF")
  void shouldReturnBadRequestWhenCreatingClientWithSameCPF() throws Exception {
    var client = ClientRequestCreator.create();

    when(clientRepository.existsByCpf(client.getCpf())).thenReturn(true);

    this.mockMvc
        .perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(client)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").exists())
        .andDo(print());
  }

  @Test
  @DisplayName("Should request a client by its CPF")
  void shouldRequestClientByCPF() throws Exception {
    var client = ClientCreator.single();

    when(clientRepository.findById(client.getCpfString())).thenReturn(Optional.of(client));

    this.mockMvc
        .perform(get(BASE_URL + "/{cpf}", client.getCpfString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.cpf").value(client.getCpfString()))
        .andExpect(jsonPath("$.full_name").value(client.getFullName()))
        .andExpect(jsonPath("$.phone").value(client.getPhone()))
        .andDo(print());
  }

  @Test
  @DisplayName("Should return bad request when retrieving client do not exists")
  void shouldReturnBadRequestWhenRetrievingClientDontExists() throws Exception {
    var client = ClientCreator.single();

    when(clientRepository.findById(client.getCpfString())).thenReturn(Optional.empty());

    this.mockMvc
        .perform(get(BASE_URL + "/{cpf}", client.getCpfString()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").exists())
        .andDo(print());
  }

  @Test
  @DisplayName("Should update a client")
  void shouldUpdateClient() throws Exception {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(true);

    this.mockMvc
        .perform(
            put(BASE_URL + "/{cpf}", client.getCpfString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(client)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("Should delete a client by its CPF")
  void shouldDeleteClientByCPF() throws Exception {
    var client = ClientCreator.single();

    when(clientRepository.existsByCpf(client.getCpfString())).thenReturn(true);

    this.mockMvc
        .perform(delete(BASE_URL + "/{cpf}", client.getCpfString()))
        .andExpect(status().isNoContent())
        .andDo(print());
  }
}
