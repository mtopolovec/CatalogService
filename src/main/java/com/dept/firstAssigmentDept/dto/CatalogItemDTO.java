package com.dept.firstAssigmentDept.dto;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CatalogItemDTO {
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    @NotNull(message = "Name cannot be null.")
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    @NotNull(message = "Description cannot be null.")
    @Size(min = 1, max = 2000)
    private String description;

    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @NotEmpty
    private List<String> images = new ArrayList<>();

    @NotEmpty
    private List<String> categories = new ArrayList<>();
}
