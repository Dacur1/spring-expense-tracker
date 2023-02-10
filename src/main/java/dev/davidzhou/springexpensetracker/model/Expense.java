package dev.davidzhou.springexpensetracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Expense {

    @Id
    private ObjectId id;

    private String description;
    @DocumentReference
    private List<Category> categoryIds;
    private double amount;
    private String currency;
    private LocalDate paymentDate;
    private String debtor;
    @DocumentReference
    private PaymentMethod paymentMethodId;
    private String notes;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public void setAmount(String amount) {
        amount = amount.replace(",", ".");
        double doubleAmount = Double.parseDouble(amount);
        BigDecimal bd = BigDecimal.valueOf(doubleAmount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.amount = bd.doubleValue();
    }

}
