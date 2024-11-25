package com.example.NIA.Calories;

import com.example.NIA.ShellPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@RequiredArgsConstructor
@Command(group = "NIA Commands")
public class NIACommand {
    private final NIApiClient niApiClient;
    private final ShellPrinter printer;
    private final NIAformatter niaFormatter; // NIAformatter'ı enjekte ediyoruz
    @CommandAvailability(provider = "userLoggedInProvider")

    @Command(command = "NIA")
    void NIA(@Option(required = true, shortNames = 'k', longNames = "kal") String kal) {
        try {
            // API'den gelen yanıtı al
            ResponseEntity<NIAResponse> response = niApiClient.getCalories(kal);

            // Yanıttan result() metodunu çağırın
            NIAResponse body = response.getBody();

            // Eğer response ve body.result() null değilse, veriyi tablo formatına çevir
            if (body != null && body.result() != null) {
                String tableOutput = niaFormatter.convertToTable(body.result());
                printer.print(tableOutput); // Tabloyu ekrana yazdır
            } else {
                printer.printError("No results found for " + kal);
            }
        } catch (Exception e) {
            printer.printError("Error occurred while fetching data: " + e.getMessage());
        }

        ResponseEntity<NIAResponse> response = niApiClient.getCalories(kal);
        NIAResponse body = response.getBody();
    }

}