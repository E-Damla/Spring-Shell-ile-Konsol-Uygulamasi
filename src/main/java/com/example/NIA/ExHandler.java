package com.example.NIA;

import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;

import java.util.stream.Collectors;

public class ExHandler implements CommandExceptionResolver {
    // 'CommandExceptionResolver' arayüzünü implement ederek, komutlar sırasında oluşan hataları işler.
    @Override
    public CommandHandlingResult resolve(Exception ex) {
        // 'resolve' metodu, herhangi bir komut işleme sırasında meydana gelen hatayı çözümlemek için kullanılır.
        // 'ex' parametresi, meydana gelen istisnadır. (Exception).
        if (ex instanceof ParameterValidationException) {
            // Eğer hata türü 'ParameterValidationException' ise (yani komut parametreleri doğrulama hatası içeriyorsa),
            // o zaman hata mesajını özel bir şekilde işlenir.
            return CommandHandlingResult.of(
                    // Hata mesajını döndürmek için 'CommandHandlingResult' kullanılır.
                    // Hata mesajı, doğrulama ihlalleri hakkında bilgi verecek şekilde formatlanır.
                    ((ParameterValidationException) ex) // 'ex' parametresini 'ParameterValidationException' türüne cast edilir.
                            .getConstraintViolations().stream()// 'getConstraintViolations' metodu, ihlalleri bir liste olarak döndürür. Bu listeyi bir akışa (stream) dönüştürür.
                            .map(x -> x.getPropertyPath() + " " + x.getMessage()) // Her bir ihlal için, parametrenin yolunu ve hata mesajını birleştirir.
                            .collect(Collectors.joining(". "))
                            + '\n');
        }
        return CommandHandlingResult.of(ex.getMessage() + "\n", 1);
    }
}
