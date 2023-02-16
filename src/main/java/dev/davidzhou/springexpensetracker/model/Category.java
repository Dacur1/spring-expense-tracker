package dev.davidzhou.springexpensetracker.model;

import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    private ObjectId id;
    @NonNull
    @NotEmpty(message = "A category description is required")
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
