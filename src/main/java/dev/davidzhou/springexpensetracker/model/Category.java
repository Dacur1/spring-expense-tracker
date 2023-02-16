package dev.davidzhou.springexpensetracker.model;

import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private String id;
    @NonNull
    @NotEmpty(message = "A category description is required")
    @Size(min = 1, max = 50, message = "Category description must be between 1 and 50 characters")
    private String category;

    public Category(String category) {
        this.category = category;
    }

}
