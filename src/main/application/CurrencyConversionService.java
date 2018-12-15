package main.application;

import main.domain.ConversionPair;
import main.domain.ConversionRate;
import main.domain.Counter;
import main.repository.ConversionRateRepository;
import main.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class CurrencyConversionService {

    @Autowired
    private ConversionRateRepository conversionRateRepository;
    @Autowired
    private CounterRepository counterRepository;


    public double convert(String from, String to, double amount){
        ConversionRate conversionRate = conversionRateRepository.findByPair(new ConversionPair(to,from));
        Counter counter = counterRepository.findById(1);
        counter.setCounter(counter.getCounter()+1);
        return conversionRate.convert(amount);
    }
}
