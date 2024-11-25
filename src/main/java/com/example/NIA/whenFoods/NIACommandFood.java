package com.example.NIA.whenFoods;

import com.example.NIA.ShellPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@RequiredArgsConstructor
@Command(group = "NIA Commands Food")
public class NIACommandFood {
    private final NIApiClientFood niApiClientFood;
    private final ShellPrinter printer;
    private final NIAFormatterFood niaFormatterFood; // NIAformatter'ı enjekte ediyoruz

    @CommandAvailability(provider = "userLoggedInProvider")
    @Command(command = "Nia")
    void Nia(@Option(required = true, shortNames = 'm', longNames = "mev") String ay) {
        try {
            // API'den gelen yanıtı al
            ResponseEntity<NiaResponse> response = niApiClientFood.getFood(ay);

            // Yanıttan result() metodunu çağır
            NiaResponse body = response.getBody();

            // Eğer response ve body.result() null değilse, veriyi tablo formatına çevir
            if (body != null && body.result() != null) {
                String tableOutput = niaFormatterFood.convertToTable(body.result());
                printer.print(tableOutput); // Tabloyu ekrana yazdır
            } else {
                printer.printError("No results found for " + ay);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hata mesajlarını gösterir.
        }
    }
}