package io.swagger;

//import io.swagger.dao.TransactionRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;

import org.threeten.bp.OffsetDateTime;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... arg0) throws Exception {

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(5L,OffsetDateTime.now(),"NL01INHO00000000001","NL01INHO00000000002",100.00,"Money for car",1L);
        transactions.add(transaction1);
        //Long id = transaction1.getId();
        //System.out.println(id);

        transactions.forEach(transactionRepository::save);
        transactionRepository.findAll().forEach(System.out::println);

        //transactionRepository.save(transaction1);

        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
