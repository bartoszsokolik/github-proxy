package pl.solutions.software.sokolik.bartosz.product;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

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
    return List.ofAll(productRepository.findAll());
  }
}
