package dev.davidzhou.springexpensetracker.service;

import dev.davidzhou.springexpensetracker.model.PaymentMethod;
import dev.davidzhou.springexpensetracker.repository.PaymentMethodRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    //Give me the same methods as in CategoryService
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod addSinglePaymentMethod(String paymentMethodName){
        return paymentMethodRepository.insert(new PaymentMethod(paymentMethodName));
    }

    public ResponseEntity<PaymentMethod> updatePaymentMethod(String paymentMethodName, String newPaymentMethodName) {
        PaymentMethod paymentMethod = paymentMethodRepository.findFirstByPaymentMethodNameEqualsIgnoreCase(paymentMethodName)
                .orElse(null);

        if (paymentMethod == null) {
            return null;
        }

        if (paymentMethod.getPaymentMethodName().equals(newPaymentMethodName)) {
            return null;
        }

        paymentMethod.setPaymentMethodName(newPaymentMethodName);
        paymentMethodRepository.save(paymentMethod);

        return ResponseEntity.ok(paymentMethod);
    }

    public ResponseEntity<PaymentMethod> getPaymentMethodByPaymentMethodName(String paymentMethodName) {
        PaymentMethod paymentMethod = paymentMethodRepository.findFirstByPaymentMethodNameEqualsIgnoreCase(paymentMethodName)
                .orElse(null);

        if (paymentMethod == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(paymentMethod);
    }
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethodsByChar(String charSequence) {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAllByPaymentMethodNameContainingIgnoreCaseOrderByPaymentMethodNameAsc(charSequence);

        if (paymentMethods.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(paymentMethods);
    }

    public void deletePaymentMethod(ObjectId id) {
        paymentMethodRepository.deleteById(id);
    }

}
