package info.wallyson.tcsstore.rest.controller.product;

import info.wallyson.tcsstore.product.usecases.CreateProduct;
import info.wallyson.tcsstore.product.usecases.DeleteProduct;
import info.wallyson.tcsstore.product.usecases.GetProduct;
import info.wallyson.tcsstore.product.usecases.UpdateProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
  private final CreateProduct createProduct;
  private final GetProduct getProduct;
  private final UpdateProduct updateProduct;
  private final DeleteProduct deleteProduct;

  public ProductController(
      CreateProduct createProduct,
      GetProduct getProduct,
      UpdateProduct updateProduct,
      DeleteProduct deleteProduct) {
    this.createProduct = createProduct;
    this.getProduct = getProduct;
    this.updateProduct = updateProduct;
    this.deleteProduct = deleteProduct;
  }

  @PostMapping
  public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
    createProduct.createProduct(productRequest.toProduct());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductRequest> getProductById(@PathVariable Long id) {
    var product = getProduct.getProduct(id);
    return ResponseEntity.ok(ProductRequest.fromProduct(product));
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> updateProduct(
      @RequestBody ProductRequest productRequest, @PathVariable Long id) {
    productRequest.setId(id);
    updateProduct.update(productRequest.toProduct());
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    deleteProduct.delete(id);
    return ResponseEntity.status(204).build();
  }
}
