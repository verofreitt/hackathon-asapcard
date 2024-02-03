package com.asap.hackathonasap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asap.hackathonasap.model.Installment;

public interface InstallmentRepository extends JpaRepository<Installment, String>{

}
