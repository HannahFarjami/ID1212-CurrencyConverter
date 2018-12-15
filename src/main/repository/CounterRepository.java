package main.repository;

import main.domain.ConversionPair;
import main.domain.ConversionRate;
import main.domain.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CounterRepository extends JpaRepository<Counter, Integer> {

    Counter findById(int id);
}
