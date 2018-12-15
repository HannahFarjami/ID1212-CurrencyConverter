package main.repository;

import main.domain.ConversionPair;
import main.domain.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ConversionRateRepository extends JpaRepository <ConversionRate, ConversionPair> {

    ConversionRate findByPair(ConversionPair pair);
}
