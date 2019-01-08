package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.PaymentDto;
import com.app.exceptions.MyException;
import com.app.model.EPayment;
import com.app.model.Payment;
import com.app.repository.PaymentRepository;
import com.app.repository.PaymentRepositoryImpl;

import java.time.LocalDateTime;

public class PaymentService {
    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    public void addPayment(PaymentDto paymentDto) {
        try {
            Payment payment = modelMapper.fromPaymentDtoToPayment(paymentDto);

            if (isPayment(payment.getEPayment())) {
                throw new MyException("PAYMENT ALREADY IN DB", LocalDateTime.now());
            }

            paymentRepository.addOrUpdate(payment);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("SERVICE, ADD PAYMENT", LocalDateTime.now());
        }
    }

    private boolean isPayment(EPayment ePayment) {
        return paymentRepository.findByName(ePayment).isPresent();
    }

}
