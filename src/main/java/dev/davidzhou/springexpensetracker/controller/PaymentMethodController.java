package dev.davidzhou.springexpensetracker.controller;

import dev.davidzhou.springexpensetracker.model.PaymentMethod;
import dev.davidzhou.springexpensetracker.service.PaymentMethodService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodController {

    //Implement this class same as CategoryController

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.getAllPaymentMethods());
    }
    @GetMapping("/{paymentMethodName}")
    public ResponseEntity<PaymentMethod> getSinglePaymentMethodByName(@PathVariable String paymentMethodName) {
        return paymentMethodService.getPaymentMethodByPaymentMethodName(paymentMethodName);
    }
    @GetMapping("/search/{charSequence}")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethodsByChar(@PathVariable String charSequence) {
        return paymentMethodService.getAllPaymentMethodsByChar(charSequence);
    }
    @PostMapping
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody Map<String, String> payload) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentMethodService.addSinglePaymentMethod(
                        payload.get("paymentMethodName")));
    }
    @PutMapping("/update/{paymentMethodName}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(
            @PathVariable String paymentMethodName,
            @RequestBody Map<String, String> payload) {

        String newPaymentMethodName = payload.get("newPaymentMethodName");
        return paymentMethodService.updatePaymentMethod(paymentMethodName, newPaymentMethodName);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable ObjectId id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
