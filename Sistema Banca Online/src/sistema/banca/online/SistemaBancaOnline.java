/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema.banca.online;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class SistemaBancaOnline {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);

    // SALDOS POR DIVISA
    static double EUR = 2000;
    static double USD = 0;
    static double GBP = 0;
    static double CLP = 0;
    static double ARS = 0;
    static double TRY = 0;

    public static void main(String[] args) {

        System.out.println("===== BIENVENIDO AL SISTEMA DE BANCA ONLINE =====");

        if (!login()) {
            System.out.println("Acceso denegado.");
            return;
        }

        int opcion;

        do {
            menu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> verSaldo();
                case 2 -> ingresarDinero();
                case 3 -> retirarDinero();
                case 4 -> transferencia();
                case 5 -> conversorDivisas();
                case 6 -> System.out.println("Gracias por usar el banco. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida, por favor elija nuevamente.");
            }

            if (opcion != 6) {
                System.out.println("\n¿Desea realizar otra operación? (s/n)");
                String r = sc.next();
                if (r.equalsIgnoreCase("n")) opcion = 6;
            }

        } while (opcion != 6);

    }

    // LOGIN
    public static boolean login() {

        for (int i = 0; i < 3; i++) {
            sc.nextLine();
            System.out.print("Usuario: ");
            String user = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            if (login(user, pass)) {
                System.out.println("\n¡Acceso autorizado!\n");
                return true;
            }

            System.out.println("Usuario o contraseña incorrectos. Intento " + (i + 1) + " de 3\n");
        }
        return false;
    }

    public static boolean login(String user, String password) {
        return user.equals("David CT") && password.equals("Mambo123");
    }

    // MENU
    public static void menu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Ver saldos");
        System.out.println("2. Ingresar dinero");
        System.out.println("3. Retirar dinero");
        System.out.println("4. Transferencia");
        System.out.println("5. Conversor de divisas");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    // VER SALDOS
    public static void verSaldo() {
        System.out.println("\n--- Saldos actuales ---");
        System.out.printf("EUR: %.2f%n", EUR);
        System.out.printf("USD: %.2f%n", USD);
        System.out.printf("GBP: %.2f%n", GBP);
        System.out.printf("CLP: %.2f%n", CLP);
        System.out.printf("ARS: %.2f%n", ARS);
        System.out.printf("TRY: %.2f%n", TRY);
    }

    // INGRESAR DINERO
    public static void ingresarDinero() {
        System.out.println("\nSeleccione divisa (1 EUR, 2 USD, 3 GBP, 4 CLP, 5 ARS, 6 TRY): ");
        int moneda = sc.nextInt();
        System.out.print("Cantidad a ingresar: ");
        double cantidad = sc.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Cantidad inválida. Operación cancelada.");
            return;
        }

        switch (moneda) {
            case 1 -> EUR += cantidad;
            case 2 -> USD += cantidad;
            case 3 -> GBP += cantidad;
            case 4 -> CLP += cantidad;
            case 5 -> ARS += cantidad;
            case 6 -> TRY += cantidad;
            default -> {
                System.out.println("Divisa no válida. Operación cancelada.");
                return;
            }
        }

        System.out.println("Ingreso realizado correctamente.");
        verSaldo();
    }

    // RETIRAR DINERO
    public static void retirarDinero() {
        double minimo = 10;
        double maximo = 1000;

        System.out.print("\nCantidad a retirar (mín " + minimo + " - máx " + maximo + " EUR): ");
        double cantidad = sc.nextDouble();

        if (cantidad < minimo || cantidad > maximo) {
            System.out.println("Cantidad fuera de límites. Operación cancelada.");
            return;
        }

        if (cantidad > EUR) {
            System.out.println("Saldo insuficiente. Operación cancelada.");
            return;
        }

        double comision = cantidad * 0.01;
        EUR -= (cantidad + comision);

        System.out.printf("Dinero retirado: %.2f EUR%n", cantidad);
        System.out.printf("Comisión aplicada (1%%): %.2f EUR%n", comision);
        System.out.printf("Saldo actual: %.2f EUR%n", EUR);
    }

    // TRANSFERENCIA
    public static void transferencia() {
        sc.nextLine();
        System.out.print("\nCuenta destino: ");
        String cuenta = sc.nextLine();

        System.out.print("Cantidad a transferir (EUR): ");
        double cantidad = sc.nextDouble();

        if (cantidad <= 0 || cantidad > EUR) {
            System.out.println("Transferencia inválida. Operación cancelada.");
            return;
        }

        EUR -= cantidad;
        System.out.printf("Transferencia realizada: %.2f EUR enviados a %s%n", cantidad, cuenta);
        System.out.printf("Saldo actual: %.2f EUR%n", EUR);
    }

    // CONVERSOR DE DIVISAS
    public static void conversorDivisas() {
        System.out.println("\nSeleccione divisa origen (1 EUR,2 USD,3 GBP,4 CLP,5 ARS,6 TRY): ");
        int origen = sc.nextInt();
        System.out.println("Seleccione divisa destino (1 EUR,2 USD,3 GBP,4 CLP,5 ARS,6 TRY): ");
        int destino = sc.nextInt();
        System.out.print("Cantidad a convertir: ");
        double cantidad = sc.nextDouble();

        double minimo = 5;
        double maximo = 5000;

        if (cantidad < minimo || cantidad > maximo) {
            System.out.println("Cantidad fuera de límites. Operación cancelada.");
            return;
        }

        double resultado = 0;

        // Conversión directa sin ratio
        if (origen == 1 && destino == 2) resultado = cantidad * 1.07;
        if (origen == 2 && destino == 1) resultado = cantidad * 0.93;

        if (origen == 1 && destino == 3) resultado = cantidad * 0.86;
        if (origen == 3 && destino == 1) resultado = cantidad * 1.16;

        if (origen == 1 && destino == 4) resultado = cantidad * 950;
        if (origen == 4 && destino == 1) resultado = cantidad * 0.001;

        if (origen == 1 && destino == 5) resultado = cantidad * 920;
        if (origen == 5 && destino == 1) resultado = cantidad * 0.00108;

        if (origen == 1 && destino == 6) resultado = cantidad * 37;
        if (origen == 6 && destino == 1) resultado = cantidad * 0.027;

        System.out.printf("Resultado del cambio: %.2f%n", resultado);
        System.out.println("Fecha ratio: 01/11/2025");
        System.out.print("¿Confirmar cambio? (s/n): ");
        String c = sc.next();

        if (!c.equalsIgnoreCase("s")) {
            System.out.println("Cambio cancelado.");
            return;
        }

        restarSaldo(origen, cantidad);
        sumarSaldo(destino, resultado);

        System.out.println("Cambio realizado con éxito.");
        verSaldo();
    }

    public static void restarSaldo(int moneda, double cantidad) {
        switch (moneda) {
            case 1 -> EUR -= cantidad;
            case 2 -> USD -= cantidad;
            case 3 -> GBP -= cantidad;
            case 4 -> CLP -= cantidad;
            case 5 -> ARS -= cantidad;
            case 6 -> TRY -= cantidad;
        }
    }

    public static void sumarSaldo(int moneda, double cantidad) {
        switch (moneda) {
            case 1 -> EUR += cantidad;
            case 2 -> USD += cantidad;
            case 3 -> GBP += cantidad;
            case 4 -> CLP += cantidad;
            case 5 -> ARS += cantidad;
            case 6 -> TRY += cantidad;
        }
    }
}

