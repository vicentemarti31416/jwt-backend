package vicente.marti.jwtbackend.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDto {

    @NotBlank(message = "product name is mandatory")
    private String name;
    @Min(value = 1, message = "product price is mandatory")
    private int price;

    public ProductDto() {
    }

    public ProductDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
