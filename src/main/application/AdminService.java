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
public class AdminService {

    @Autowired
    ConversionRateRepository conversionRateRepository;
    @Autowired
    CounterRepository counterRepository;

    public void updateRate(String from, String to, double newRate){
        ConversionRate conversionRate = conversionRateRepository.findByPair(new ConversionPair(to,from));
        conversionRate.setRate(newRate);
        conversionRate = conversionRateRepository.findByPair(new ConversionPair(from,to));
        conversionRate.setRate(1/newRate);
    }

    public int getCounter(){
        Counter counter = counterRepository.findById(1);
        return counter.getCounter();
    }
}
