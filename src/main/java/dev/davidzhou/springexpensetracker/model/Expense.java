package dev.davidzhou.springexpensetracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UniqueElements;
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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Expense {

    @Id
    private ObjectId id;

    @NotEmpty(message = "Description is required")
    private String description;
    @DocumentReference
    @Min(value = 1, message = "At least one category is required")
    @UniqueElements(message = "Categories must be unique")
    private List<Category> categoryIds;

    @Min(value = 1, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @Size(min = 3, max = 3, message = "Currency must be exactly 3 characters")
    private String currency;

    @NonNull
    @NotEmpty(message = "Payment date is required")
    private LocalDate paymentDate;
    @NonNull
    @NotEmpty(message = "Debtor is required")
    @Max(value = 50, message = "Debtor must be less than 50 characters")
    private String debtor;
    @DocumentReference
    @NonNull
    @NotEmpty(message = "Payment method is required")
    private PaymentMethod paymentMethodId;

    @Nullable
    private String notes;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public Expense(String description,
                   List<Category> categoryIds,
                   String amount,
                   String currency,
                   LocalDate paymentDate,
                   String debtor,
                   PaymentMethod paymentMethodId) {
        this.description = description;
        this.categoryIds = categoryIds;
        this.setAmount(amount);
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.debtor = debtor;
        this.paymentMethodId = paymentMethodId;
    }

    public Expense(String description,
                   List<Category> categoryIds,
                   String amount,
                   String currency,
                   LocalDate paymentDate,
                   String debtor,
                   PaymentMethod paymentMethodId,
                   String notes) {
        this.description = description;
        this.categoryIds = categoryIds;
        this.setAmount(amount);
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.debtor = debtor;
        this.paymentMethodId = paymentMethodId;
        this.notes = notes;
    }

    public void setAmount(String amount) {
        amount = amount.replace(",", ".");
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
    }

}
