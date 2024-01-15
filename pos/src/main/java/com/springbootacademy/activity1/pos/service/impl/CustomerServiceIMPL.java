package com.springbootacademy.activity1.pos.service.impl;

import com.springbootacademy.activity1.pos.dto.CustomerDTO;
import com.springbootacademy.activity1.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.activity1.pos.entity.Customer;
import com.springbootacademy.activity1.pos.exception.NotFoundException;
import com.springbootacademy.activity1.pos.repo.CustomerRepo;
import com.springbootacademy.activity1.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.getContactNumber(),
                customerDTO.isActive()
        );

        customerRepo.save(customer);
        return customerDTO.getCustomerName();

    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer = customerRepo.getById(customerUpdateDTO.getCustomerId());

            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerRepo.save(customer);

            return customerUpdateDTO.getCustomerName() + " updated successfully";

        }else{
            throw new RuntimeException("No data found for that ID");
        }


    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.getContactNumber(),
                    customer.isActive()
            );

            return customerDTO;

        }else{
            throw new RuntimeException("No customer");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();
        if(getAllCustomers.size()>0) {
            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for (Customer customer : getAllCustomers) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getNic(),
                        customer.getContactNumber(),
                        customer.isActive()
                );

                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        }else{
            throw new NotFoundException("No customer found");

        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "deleted successfully "+customerId;

        }else{
            throw new RuntimeException("No customer found for that id");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for(Customer customer: getAllCustomers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.getContactNumber(),
                    customer.isActive()
            );

            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
