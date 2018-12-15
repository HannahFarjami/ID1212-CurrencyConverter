package main.application;

import main.domain.ConversionPair;
import main.domain.ConversionRate;
import main.repository.ConversionRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class AdminService {

    @Autowired
    ConversionRateRepository conversionRateRepository;

    public void updateRate(String from, String to, double newRate){
        ConversionRate conversionRate = conversionRateRepository.findByPair(new ConversionPair(to,from));
        conversionRate.setRate(newRate);
        conversionRate = conversionRateRepository.findByPair(new ConversionPair(from,to));
        conversionRate.setRate(1/newRate);
    }
}
