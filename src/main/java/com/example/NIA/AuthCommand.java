package com.example.NIA;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.command.annotation.Command;

@RequiredArgsConstructor
@Command(group = "Auth Command")
public class AuthCommand {

    private final AuthenticationManager authenticationManager;
    private final ShellPrinter shellPrinter;
    private final ShellReader shellReader;

    @Command(command = "login")
    public void login() {
        // Kullanıcıdan username ve password alınır.
        var username = shellReader.readLine("username");
        var password = shellReader.readLinePassword("password");
        // Kullanıcı bilgileri ile kimlik doğrulaması yapılır.
        Authentication result =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));
        // Başarılı kimlik doğrulamasından sonra güvenlik bağlamına kimlik eklenir.
        SecurityContextHolder.getContext().setAuthentication(result);
        // Başarı mesajı yazdırılır.
        shellPrinter.printSuccess("successfully authenticated");
    }

    @Command(command = "logout")
    public void logout() {
        // Güvenlik bağlamını temizler (oturumdan çıkış)
        SecurityContextHolder.clearContext();
        // Başarı mesajı yazdırılır
        shellPrinter.printSuccess("successfully logged out");
    }
}
