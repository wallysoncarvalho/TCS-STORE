package info.wallyson.tcsstore.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.wallyson.tcsstore.product.ports.ProductRepository;
import info.wallyson.tcsstore.rest.config.ProductBeans;
import info.wallyson.tcsstore.rest.controller.product.ProductController;
import info.wallyson.tcsstore.rest.exception.RestExceptionHandler;
import info.wallyson.tcsstore.rest.util.ProductRequestCreator;
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
    classes = {ProductController.class, ProductBeans.class, RestExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired private MockMvc mockMvc;
  @MockBean private ProductRepository productRepository;
  private final String BASE_URL = "/api/v1/product";

  @Test
  @DisplayName("Should request the creation of a product")
  void shouldRequestProductCreation() throws Exception {
    var product = ProductRequestCreator.create();

    this.mockMvc
        .perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(product)))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Should request a product by its ID")
  void shouldRequestProductByID() throws Exception {
    var product = ProductRequestCreator.create();

    when(productRepository.findById(1L)).thenReturn(Optional.of(product.toProduct()));

    this.mockMvc
        .perform(get(BASE_URL + "/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(product.getName()))
        .andExpect(jsonPath("$.description").value(product.getDescription()))
        .andExpect(jsonPath("$.priceUnit").isNumber())
        .andDo(print());
  }

  @Test
  @DisplayName("Should update a product")
  void shouldUpdateProduct() throws Exception {
    var product = ProductRequestCreator.create();

    when(productRepository.existsById(product.getId())).thenReturn(true);

    this.mockMvc
        .perform(
            put(BASE_URL + "/{id}", product.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(product)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("Should delete a product")
  void shouldDeleteProduct() throws Exception {
    var product = ProductRequestCreator.create();

    when(productRepository.existsById(product.getId())).thenReturn(true);

    this.mockMvc
        .perform(
            delete(BASE_URL + "/{id}", product.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(product)))
        .andExpect(status().isNoContent())
        .andDo(print());
  }
}
