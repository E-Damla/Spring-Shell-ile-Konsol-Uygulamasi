package com.example.NIA.Calories;

import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NIAformatter {
    private static String[] toRow(NIAItem item) {
        // Verinin doğru şekilde geldiğinden emin ol
        return new String[]{item.name(), item.kcal(), item.weight()};
    }

    public String convertToTable(List<NIAItem> items) {
        // Listeyi tablonun satırlarına dönüştür
        var data = items.stream()
                .map(NIAformatter::toRow)
                .collect(Collectors.toList());

        // Başlık satırını ekle
        data.add(0, new String[]{"name", "kalori", "Adet"});

        // ArrayTableModel kullanarak tabloyu oluştur
        ArrayTableModel tableModel = new ArrayTableModel(data.toArray(Object[][]::new));
        TableBuilder tableBuilder = new TableBuilder(tableModel);

        // Tabloyu formatla ve döndür
        tableBuilder.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
        return tableBuilder.build().render(1000); // Render işlemi ile tabloyu 100 karakter genişliğinde döndür
    }

}