package mochegov.transact.load;

import mochegov.transact.model.User;
import mochegov.transact.repositories.UserRepository;
import mochegov.transact.services.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileReader;
import java.util.Scanner;

public class DataLoad {
    public static void loadData(AccountService accountService,
                                UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        accountService.createAccount("20202810100000000001");
        accountService.createAccount("40817810500000000002");

        userRepository.save(
                new User("Mochegov", passwordEncoder.encode("123"), "ADMIN"));

        // Загрузка файла
        String path = DataLoad.class.getClassLoader().getResource("data").getPath();
        System.out.println(path);


        /*
        Scanner scanner = new Scanner(new FileReader(+ "/POLICE_DEPARTMENT.TXT"));

        while (scanner.hasNext()) {
            String policeDepartment = scanner.nextLine();
            POLICE_DEPARTMENTS.add(policeDepartment.substring(0, policeDepartment.indexOf("(") - 1));
        }
        scanner.close();
        */

    }
}
