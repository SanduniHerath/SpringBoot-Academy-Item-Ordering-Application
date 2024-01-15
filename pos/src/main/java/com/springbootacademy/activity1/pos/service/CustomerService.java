package com.springbootacademy.activity1.pos.service;

import com.springbootacademy.activity1.pos.dto.CustomerDTO;
import com.springbootacademy.activity1.pos.dto.request.CustomerUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int customerId);

    List<CustomerDTO> getAllCustomersByActiveState(boolean activeState);
}
