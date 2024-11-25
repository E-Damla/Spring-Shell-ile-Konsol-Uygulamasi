package com.example.NIA;

import org.jline.reader.LineReader;

public class ShellReader {
    private LineReader lineReader; //satır bazında giriş almak için kullanılan nesne.

    public ShellReader(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    public String readLine(String displayText) { //readLine metodu, kullanıcıdan standart bir giriş almak için,
        // displayText: Kullanıcıya bir prompt (görsel ipucu) gösterilmesi için kullanılan metin.
        return lineReader.readLine(displayText + ": ");
    }

    public String readLinePassword(String displayText) { //readLinePassword metodu, kullanıcıdan parola alır,paralo girişi sırasında * görünür.
        return lineReader.readLine(displayText + ": ", '*');
    }
}
