import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        GeneradorDeArchivo generador = new GeneradorDeArchivo();

        while (true) {
            mostrarMenu();
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 0) {
                System.out.println("👋 Saliendo del programa. ¡Hasta luego!");
                break;
            }

            String codigoDestino = obtenerCodigoMoneda(opcion);
            if (codigoDestino.isEmpty()) {
                System.out.println("❌ Opción no válida.");
                continue;
            }

            System.out.print("💰 Ingrese la cantidad en USD que desea convertir: ");
            double cantidad = Double.parseDouble(scanner.nextLine());

            try {
                Moneda moneda = consulta.buscarMoneda("USD", codigoDestino);
                double total = cantidad * moneda.tasaConversion();
                System.out.printf("✅ %.2f USD equivale a %.2f %s%n", cantidad, total, codigoDestino);
                generador.guardarJson(moneda);
            } catch (RuntimeException | IOException e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }

            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║      🌍 Bienvenido al Conversor de Monedas     ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1 - ARS - Peso argentino                      ║");
        System.out.println("║ 2 - BOB - Boliviano                           ║");
        System.out.println("║ 3 - BRL - Real brasileño                      ║");
        System.out.println("║ 4 - CLP - Peso chileno                        ║");
        System.out.println("║ 5 - COP - Peso colombiano                     ║");
        System.out.println("║ 6 - USD - Dólar estadounidense                ║");
        System.out.println("║ 0 - Salir                                     ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("👉 Ingrese una opción: ");
    }

    public static String obtenerCodigoMoneda(int opcion) {
        return switch (opcion) {
            case 1 -> "ARS";
            case 2 -> "BOB";
            case 3 -> "BRL";
            case 4 -> "CLP";
            case 5 -> "COP";
            case 6 -> "USD";
            default -> "";
        };
    }
}
