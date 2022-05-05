package com.example.service;

public interface TxrService {
	boolean transfer(double amount, String fromAcc, String toAcc);
}