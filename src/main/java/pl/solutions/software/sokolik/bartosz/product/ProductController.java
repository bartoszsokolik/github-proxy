package pl.solutions.software.sokolik.bartosz.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
