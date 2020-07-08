package pl.solutions.software.sokolik.bartosz.product;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Product create(Product product) {
    log.info("create");
    return productRepository.save(product);
  }

  public void update(Product product) {
    log.info("update");
    productRepository.findById(product.getId())
//        .map(p -> new Product(p.getId(), p.getName()));
        .ifPresent(p -> p.setName(product.getName()));
  }

  public List<Product> getAll() {
    return productRepository.findAll();
  }
}
