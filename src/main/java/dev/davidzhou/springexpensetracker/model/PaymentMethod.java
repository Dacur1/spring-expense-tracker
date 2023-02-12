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

@Data
@Document(collection = "paymentMethods")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    @Id
    private String id;
    @NonNull
    @NotEmpty(message = "A payment method description is required")
    @Size(min = 1, max = 50, message = "Payment method description must be between 1 and 50 characters")
    @Indexed(unique = true)
    private String paymentMethod;

    public PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
