package com.sample.wallet;

import com.sample.wallet.Exception.InvalidUserException;
import com.sample.wallet.business.WalletService;
import com.sample.wallet.entity.Transaction;
import com.sample.wallet.entity.Wallet;
import com.sample.wallet.repository.TransactionRepository;
import com.sample.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WalletServiceTests {

	@Mock
	private WalletRepository walletRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private WalletService walletService;

	private Wallet wallet;
	private Transaction transaction;

	@BeforeEach
	public void setup() {
		wallet = new Wallet();
		wallet.setId(1L);
		wallet.setUser_id(1);
		wallet.setBalance(BigDecimal.valueOf(4000));

		transaction = new Transaction();
		transaction.setId(12312312312L);
		transaction.setTimestamp(LocalDateTime.now());
		transaction.setWalletId(1L);
		transaction.setAmount(500);
	}

	@Test
	public void testGetBalance_ValidUserId() throws InvalidUserException {
		when(walletRepository.getBalance(1)).thenReturn(BigDecimal.valueOf(4000));
		BigDecimal balance = walletService.getBalance(1);
		assertEquals(BigDecimal.valueOf(4000), balance);
	}

	@Test
	public void testGetBalance_InvalidUserId(){
		assertThrows(InvalidUserException.class, () -> {
			walletService.changeBalance(99, 100);});
	}

	@Test
	public void testAddMoney_ValidUserIdPositiveAmount() throws InvalidUserException {
		when(walletRepository.getWalletByUser_id(1)).thenReturn(wallet);
		when(transactionRepository.save(any())).thenReturn(transaction);

		long referenceId = walletService.changeBalance(1,500);
		assertEquals(12312312312L, referenceId);
	}

	@Test
	public void testAddMoney_ValidUserIdNegativeAmount() throws InvalidUserException {
		when(walletRepository.getWalletByUser_id(1)).thenReturn(wallet);
		when(transactionRepository.save(any())).thenReturn(transaction);

		long referenceId = walletService.changeBalance(1, -500);
		assertEquals(12312312312L, referenceId);

	}

	@Test
	public void testAddMoney_InvalidUserId() {

		assertThrows(InvalidUserException.class, () -> {
			walletService.changeBalance(1, 500);});	}

	@Test
	public void testAddMoney_ZeroAmount() throws InvalidUserException {
		when(walletRepository.getWalletByUser_id(1)).thenReturn(wallet);
		when(transactionRepository.save(any())).thenReturn(transaction);

		long referenceId = walletService.changeBalance(1, 0);
		assertEquals(12312312312L, referenceId);
	}
}
