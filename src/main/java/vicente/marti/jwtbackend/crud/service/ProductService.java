package vicente.marti.jwtbackend.crud.service;

import vicente.marti.jwtbackend.crud.dto.ProductDto;
import vicente.marti.jwtbackend.crud.entity.Product;
import vicente.marti.jwtbackend.crud.repository.ProductRepository;
import vicente.marti.jwtbackend.global.exceptions.AttributeException;
import vicente.marti.jwtbackend.global.exceptions.ResourceNotFoundException;
import vicente.marti.jwtbackend.global.utils.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("not found"));
    }

    public Product save(ProductDto dto) throws AttributeException {
        if(productRepository.existsByName(dto.getName()))
            throw new AttributeException("name already in use");
        Product product = new Product(dto.getName(), dto.getPrice());
        return productRepository.save(product);
    }

    public Product update(Long id, ProductDto dto) throws ResourceNotFoundException, AttributeException {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("not found"));
        if(productRepository.existsByName(dto.getName()) && productRepository.findByName(dto.getName()).get().getId() != id)
            throw new AttributeException("name already in use");
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return productRepository.save(product);
    }

    public Product delete(Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("not found"));;
        productRepository.delete(product);
        return product;
    }

}
