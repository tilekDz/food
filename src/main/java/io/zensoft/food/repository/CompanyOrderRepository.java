package io.zensoft.food.repository;

import io.zensoft.food.enums.CompanyOrderStatus;
import io.zensoft.food.model.CompanyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CompanyOrderRepository extends JpaRepository<CompanyOrder, Long>,
        PagingAndSortingRepository<CompanyOrder, Long> {
    Optional<CompanyOrder> findByOrderStatus(CompanyOrderStatus orderStatus);

    CompanyOrder getById(Long id);
}
