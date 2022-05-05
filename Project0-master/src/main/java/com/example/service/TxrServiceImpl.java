package com.example.service;


import org.apache.log4j.Logger;

import com.example.entity.Account;

import com.example.repository.AccountRepository;

public class TxrServiceImpl implements TxrService {

	private static Logger logger = Logger.getLogger("txr-system");

	private AccountRepository accountRepository;

	public TxrServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public boolean transfer(double amount, String fromAcc, String toAcc) {

		logger.info("txr intiated...");

		Account fromAccount = accountRepository.load(fromAcc);

		if (fromAccount == null) {
			logger.error("account not found " + fromAcc);
			throw new AccountNotFoundException(fromAcc);
		}

		Account toAccount = accountRepository.load(toAcc);

		if (toAccount == null) {
			logger.error("account not found " + toAcc);
			throw new AccountNotFoundException(toAcc);
		}

		if (fromAccount.getBalance() < amount) {
			logger.error("no enough balance " + fromAccount.getBalance());
			throw new AccountBalanceException(fromAccount.getBalance());
		}

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		accountRepository.update(fromAccount);
		accountRepository.update(toAccount);



		logger.info("txr success...");
		boolean t=true;
		return t;
	}

}