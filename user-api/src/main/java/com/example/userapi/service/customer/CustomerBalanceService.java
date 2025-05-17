package com.example.userapi.service.customer;

import com.example.userapi.domain.customer.ChangeBalanceForm;
import com.example.userapi.domain.model.CustomerBalanceHistory;
import com.example.userapi.domain.repository.CustomerBalanceHistoryResipotory;
import com.example.userapi.domain.repository.CustomerRepository;
import com.example.userapi.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.userapi.exception.ErrorCode.NOT_ENOUGH_BALANCE;
import static com.example.userapi.exception.ErrorCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
public class CustomerBalanceService {

    private final CustomerBalanceHistoryResipotory customerBalanceHistoryResipotory;
    private final CustomerRepository customerRepository;

    @Transactional(noRollbackFor = {CustomException.class})
    public CustomerBalanceHistory changeBalance(Long customerId, ChangeBalanceForm form) throws CustomException {
        CustomerBalanceHistory customerBalanceHistory =
                customerBalanceHistoryResipotory.findFirstByCustomer_IdOrderByIdDesc(customerId)
                        .orElse(CustomerBalanceHistory.builder()
                                .changeMoney(0)
                                .currentMoney(0)
                                .customer(customerRepository.findById(customerId)
                                        .orElseThrow(() -> new CustomException(NOT_FOUND_USER)))
                                .build());
        if (customerBalanceHistory.getCurrentMoney() + form.getMoney() < 0) {
            throw new CustomException(NOT_ENOUGH_BALANCE);
        }

        customerBalanceHistory = CustomerBalanceHistory.builder()
                .changeMoney(form.getMoney())
                .currentMoney(customerBalanceHistory.getCurrentMoney() + form.getMoney())
                .description(form.getMessage())
                .fromMessage(form.getFrom())
                .customer(customerBalanceHistory.getCustomer())
                .build();
        customerBalanceHistory.getCustomer().setBalance(customerBalanceHistory.getCurrentMoney());
        return customerBalanceHistory;
    }
}
