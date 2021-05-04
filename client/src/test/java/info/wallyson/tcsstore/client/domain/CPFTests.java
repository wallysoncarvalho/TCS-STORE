package info.wallyson.tcsstore.client.domain;

import info.wallyson.tcsstore.client.exception.InvalidCPFException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CPFTests {

  @Test
  @DisplayName("Should throw exception for invalid CPF entries")
  void shouldThrowExceptionForInvalidCPF() {
    assertThrows(InvalidCPFException.class, () -> new CPF("00000000000"));
    assertThrows(InvalidCPFException.class, () -> new CPF("11111111111"));
    assertThrows(InvalidCPFException.class, () -> new CPF("22222222222"));
    assertThrows(InvalidCPFException.class, () -> new CPF("33333333333"));
    assertThrows(InvalidCPFException.class, () -> new CPF("44444444444"));
    assertThrows(InvalidCPFException.class, () -> new CPF("55555555555"));
    assertThrows(InvalidCPFException.class, () -> new CPF("66666666666"));
    assertThrows(InvalidCPFException.class, () -> new CPF("77777777777"));
    assertThrows(InvalidCPFException.class, () -> new CPF("88888888888"));
    assertThrows(InvalidCPFException.class, () -> new CPF("99999999999"));

    assertThrows(InvalidCPFException.class, () -> new CPF(""), "Empty CPF");
    assertThrows(InvalidCPFException.class, () -> new CPF("24tyhm2klpo"));
    assertThrows(InvalidCPFException.class, () -> new CPF("242"));
    assertThrows(InvalidCPFException.class, () -> new CPF("1232323232323"));
  }

  @Test
  @DisplayName("Should create valid CPF")
  void shouldCreateValidCPF() {
    var cpf = new CPF("55028292000");
    assertEquals("55028292000", cpf.getCpf());
  }

  @Test
  @DisplayName("Should remove non numerical characters from identifier")
  void shouldRemoveNonNumericalCharactersFromId() {
    var cpf1 = new CPF("550.282.920-00");
    var cpf2 = new CPF(".....///550.282.920-00");
    var cpf3 = new CPF("abc550.def282...;920%$#00");

    assertEquals("55028292000", cpf1.getCpf());
    assertEquals("55028292000", cpf2.getCpf());
    assertEquals("55028292000", cpf3.getCpf());
  }
}
