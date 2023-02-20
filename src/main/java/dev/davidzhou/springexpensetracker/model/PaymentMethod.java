package dev.davidzhou.springexpensetracker.model;

import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
    private String paymentMethodName;

    public PaymentMethod(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
