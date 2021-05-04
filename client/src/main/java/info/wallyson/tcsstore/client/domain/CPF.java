package info.wallyson.tcsstore.client.domain;

import info.wallyson.tcsstore.client.exception.InvalidCPFException;

import java.util.List;

public class CPF {
  private final String cpf;

  public CPF(String cpf) {
    var cleanedCpf = cpf.replaceAll("[^\\d]", "");
    if (!isIdValid(cleanedCpf)) throw new InvalidCPFException(cleanedCpf);
    this.cpf = cleanedCpf;
  }

  boolean isIdValid(String id) {
    if (!passSimpleCheck(id)) return false;

    char dig10, dig11;
    int sm, i, r, num, peso;

    sm = 0;
    peso = 10;
    for (i = 0; i < 9; i++) {
      num = (int) (id.charAt(i) - 48);
      sm = sm + (num * peso);
      peso = peso - 1;
    }

    r = 11 - (sm % 11);
    if ((r == 10) || (r == 11)) dig10 = '0';
    else dig10 = (char) (r + 48);

    sm = 0;
    peso = 11;
    for (i = 0; i < 10; i++) {
      num = (int) (id.charAt(i) - 48);
      sm = sm + (num * peso);
      peso = peso - 1;
    }

    r = 11 - (sm % 11);
    if ((r == 10) || (r == 11)) dig11 = '0';
    else dig11 = (char) (r + 48);

    return (dig10 == id.charAt(9)) && (dig11 == id.charAt(10));
  }

  private boolean passSimpleCheck(String cpf) {
    List<String> commonInvalidCpf =
        List.of(
            "00000000000",
            "11111111111",
            "22222222222",
            "33333333333",
            "44444444444",
            "55555555555",
            "66666666666",
            "77777777777",
            "88888888888",
            "99999999999");

    return !commonInvalidCpf.contains(cpf) && cpf.length() == 11;
  }

  public String getCpf() {
    return cpf;
  }
}
