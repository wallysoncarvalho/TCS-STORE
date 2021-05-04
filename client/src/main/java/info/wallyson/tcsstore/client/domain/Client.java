package info.wallyson.tcsstore.client.domain;

public class Client {
  private CPF cpf;
  private String fullName;
  private String phone;
  private String fullAddress;
  private String postalCode;
  private String district;
  private String city;

  public Client(
      CPF cpf,
      String fullName,
      String phone,
      String fullAddress,
      String postalCode,
      String district,
      String city) {
    this.cpf = cpf;
    this.fullName = fullName;
    this.phone = phone;
    this.fullAddress = fullAddress;
    this.postalCode = postalCode;
    this.district = district;
    this.city = city;
  }

  public String getCpfString() {
    return this.cpf.getCpf();
  }

  public CPF getCpf() {
    return cpf;
  }

  public void setCpf(CPF cpf) {
    this.cpf = cpf;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String toString() {
    return "Client{"
        + "cpf="
        + cpf
        + ", fullName='"
        + fullName
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", fullAddress='"
        + fullAddress
        + '\''
        + ", postalCode='"
        + postalCode
        + '\''
        + ", district='"
        + district
        + '\''
        + ", city='"
        + city
        + '\''
        + '}';
  }
}
