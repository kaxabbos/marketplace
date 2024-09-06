package com.marketplace.ordering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {
    List<Ordering> findAllByOwner_Id(Long id);

    List<Ordering> findAllByProduct_Owner_Id(Long id);
}
