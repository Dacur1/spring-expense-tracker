package dev.davidzhou.springexpensetracker.model;

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
    private String paymentMethod;
}
