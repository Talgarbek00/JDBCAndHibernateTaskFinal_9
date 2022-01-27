package peaksoft;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        boolean trueFols = true;
        informations();
        while (trueFols) {
            String a = scanner.nextLine();
            if (a.equals("CREATE")) {
                userService.createUsersTable();
            } else if (a.equals("DROP TABLE")) {
                userService.dropUsersTable();
            } else if (a.equals("CLEAN TABLE")) {
                userService.cleanUsersTable();
            } else if (a.equals("REMOVE BY ID")) {
                System.out.print("press id:");
                try {
                    int b = scanner.nextInt();
                userService.removeUserById(b);
                }catch (InputMismatchException e) {
                    System.err.println("вы ввели неверный id");
                }
            } else if (a.equals("GET ALL")) {
                System.out.println(userService.getAllUsers());
            } else if (a.equals("ADD USER")) {
                System.out.println("user толтуруу эрежеси! \n1) press name\n2) press last name\n3) press age");
                userService.saveUser(scanner.nextLine(), scanner.nextLine(), scanner.nextByte());
            } else if (a.equals("X")) {
                trueFols = false;
            } else if(a.equals("")){
                informations();
            }else {
                System.out.println(" ");
            }
        }
    }
    public static void informations(){
        System.out.println("------------------------------------");
        System.out.println("CREATE       - таблица тузуу        |");
        System.out.println("DROP TABLE   - таблица очуруу       |");
        System.out.println("CLEAN TABLE  - таблица тазалоо      |");
        System.out.println("REMOVE BY ID - id менен очуруу      |");
        System.out.println("GET ALL      - баардыгын алуу       |");
        System.out.println("ADD USER     - user кошуу           |");
        System.out.println("X            - программадан чыгуу   |");
        System.out.println("-------------------------------------");
    }
}
