package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.exceptions.MyException;
import com.app.model.ExceptionModel;
import com.app.repository.ExceptionModelRepository;
import com.app.repository.ExceptionModelRepositoryImpl;

import java.time.LocalDateTime;

public class ExceptionModelService {
    private ExceptionModelRepository exceptionModelRepository = new ExceptionModelRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    public void addException(MyException exception) {
        try {
            ExceptionModel exceptionModel = ExceptionModel.builder()
                    .exceptionDateTime(LocalDateTime.now())
                    .exceptionMessage(exception.getMessage())
                    .build();
            exceptionModelRepository.addOrUpdate(exceptionModel);
        } catch (Exception e) {
            throw new MyException("SERVICE, ADD EXCEPTIONMODEL", LocalDateTime.now());
        }
    }
}
