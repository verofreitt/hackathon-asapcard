package com.asap.ingestaodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asap.ingestaodb.model.Installment;
import com.asap.ingestaodb.model.Transaction;
import com.asap.ingestaodb.repository.InstallmentRepository;

@Service
public class InstallmentService {

	 @Autowired
	  private InstallmentRepository installmentRepository;
	  
		public void addNewInstallment(Installment installment) {
			installmentRepository.save(installment);
		}
		
		
		//valor do pagamento amount
		//numero de parcelas num
		public void addInstallments(Transaction transaction, int num, double amount) {
			double installmentValue;
			installmentValue = amount / num;
			for(int i = 1; i <= num; i++) {
				Installment installment = new Installment();
				installment.setTransaction(transaction);
				installment.setInstallmentNumber(i);
				installment.setValue(installmentValue);
                
				this.addNewInstallment(installment);
			}
		}
}
