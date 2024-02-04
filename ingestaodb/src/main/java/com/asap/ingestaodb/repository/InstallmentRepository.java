package com.asap.ingestaodb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asap.ingestaodb.model.Installment;

public interface InstallmentRepository extends JpaRepository<Installment, UUID>{

}
