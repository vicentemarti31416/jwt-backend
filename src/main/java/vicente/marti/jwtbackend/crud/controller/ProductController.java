package vicente.marti.jwtbackend.crud.controller;

import vicente.marti.jwtbackend.crud.dto.ProductDto;
import vicente.marti.jwtbackend.crud.entity.Product;
import vicente.marti.jwtbackend.crud.service.ProductService;
import vicente.marti.jwtbackend.global.dto.MessageDto;
import vicente.marti.jwtbackend.global.exceptions.AttributeException;
import vicente.marti.jwtbackend.global.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<MessageDto> save(@Valid @RequestBody ProductDto dto) throws AttributeException {
        Product product = productService.save(dto);
        String message = "product " + product.getName() + " has been saved";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto dto) throws ResourceNotFoundException, AttributeException {
        Product product = productService.update(id, dto);
        String message = "product " + product.getName() + " has been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Product product = productService.delete(id);
        String message = "product " + product.getName() + " has been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
