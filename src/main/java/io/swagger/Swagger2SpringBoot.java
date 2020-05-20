package io.swagger;

//import io.swagger.dao.TransactionRepository;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
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
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... arg0) throws Exception {

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(OffsetDateTime.now(),"NL01INHO00000000001","NL01INHO00000000002",100.00,"Money for Fiat Multipla",1L);
        Transaction transaction2 = new Transaction(OffsetDateTime.now(),"NL01INHO00000000002","NL01INHO00000000001",175.00,"Nieuwe Game",2L);
        transactions.add(transaction1);
        transactions.add(transaction2);
        //Long id = transaction1.getId();
        //System.out.println(id);

        transactions.forEach(transactionRepository::save);
        transactionRepository.findAll().forEach(System.out::println);

        //transactionRepository.save(transaction1);


        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account(500, Account.CurrencyEnum.EUR, "NL01INHO00000000001", Account.TypeEnum.CURRENT,1);
        Account account2 = new Account(600, Account.CurrencyEnum.EUR, "NL01INHO03119837070", Account.TypeEnum.SAVINGS,2);
        accounts.add(account1);
        accounts.add(account2);

        accounts.forEach(accountRepository::save);
        accountRepository.findAll().forEach(System.out::println);

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
