
package main;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.domain.ConversionPair;
import main.domain.ConversionRate;
import main.domain.Counter;
import main.repository.ConversionRateRepository;
import main.repository.CounterRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
                        double r = getConversionRate(currency[i],currency[j]);
                        rate.setRate(r);
                        rate2.setRate(1/r);
                        repo.save(rate2);
                        System.out.println(currency[i]+"/"+currency[j]);
                    }else{
                        rate = new ConversionRate(new ConversionPair(currency[i], currency[j]));
                        rate.setRate(1);
                    }
                    repo.save(rate);

                }
            }
            System.out.println("Done");

        };
    }


    double getConversionRate(String to, String from){
        double rate = 0;
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base="+from+"&symbols="+to);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonObject root = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
            JsonObject rates = root.get("rates").getAsJsonObject();
            rate = rates.get(to).getAsDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
