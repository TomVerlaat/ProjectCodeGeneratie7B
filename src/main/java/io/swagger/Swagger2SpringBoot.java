package io.swagger;

//import io.swagger.dao.TransactionRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

    //private TransactionRepository transactionRepository;

    @Override
    public void run(String... arg0) throws Exception {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(50.00)
        );

        /*
        transactions.forEach(transactionRepository::save);
        transactionRepository.findAll().forEach(System.out::println);
         */


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
