
package main;
import main.domain.ConversionPair;
import main.domain.ConversionRate;
import main.domain.Counter;
import main.repository.ConversionRateRepository;
import main.repository.CounterRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CurrencyConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApplication.class);
    }



    @Bean
    ApplicationRunner init(ConversionRateRepository repo, CounterRepository counterRepository){
        return args -> {
            counterRepository.save(new Counter());
            String[] currency = {"EUR","SEK","USD","GBP"};
            ConversionRate rate;
            for(int i = 0; i<currency.length;i++){
                for(int j = i; j< currency.length;j++){
                    if(i!=j) {
                        rate = new ConversionRate(new ConversionPair(currency[i], currency[j]));
                        ConversionRate rate2 = new ConversionRate(new ConversionPair(currency[j], currency[i]));
                        rate.setRate(1.1);
                        rate2.setRate(1/1.1);
                        repo.save(rate2);
                        System.out.println(currency[i]+"/"+currency[j]);
                    }else{
                        rate = new ConversionRate(new ConversionPair(currency[i], currency[j]));
                        rate.setRate(1);
                    }
                    repo.save(rate);

                }
            }
        };
    }
}
