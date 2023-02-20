package dev.davidzhou.springexpensetracker.repository;

import dev.davidzhou.springexpensetracker.model.PaymentMethod;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, ObjectId> {

    //Give me the same methods as in categoryRepository class
    Optional<PaymentMethod> findFirstByPaymentMethodNameEqualsIgnoreCase(String paymentMethodName);
    List<PaymentMethod> findAllByPaymentMethodNameContainingIgnoreCaseOrderByPaymentMethodNameAsc(String charSequence);

}
