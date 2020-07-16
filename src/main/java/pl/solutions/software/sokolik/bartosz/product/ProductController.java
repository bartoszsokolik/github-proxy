package pl.solutions.software.sokolik.bartosz.product;

import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getAll() {
    return productService.getAll();
  }

  @PostMapping
  public void modify(@RequestBody Product product) {
    if (product.getId() != null) {
      productService.update(product);
      return;
    }
    productService.create(product);
  }
}
