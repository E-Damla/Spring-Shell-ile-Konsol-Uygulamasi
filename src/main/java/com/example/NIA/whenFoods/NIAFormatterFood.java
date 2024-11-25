package com.example.NIA.whenFoods;

import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class NIAFormatterFood {

    public String convertToTable(Map<String, List<String>> result) {
        // Başlık satırı
        List<String[]> data = new ArrayList<>();

        // Başlıklar
        data.add(new String[]{"mevsim_meyve", "mevsim_sebze", "her_zaman_sebze"});

        // Her kategori altındaki öğeler
        int maxSize = Math.max(
                getCategorySize(result, "mevsim_meyve"),
                Math.max(
                        getCategorySize(result, "mevsim_sebze"),
                        getCategorySize(result, "her_zaman_sebze")
                )
        );

        // 3 kategorinin her biri için, eksik yerleri null ile doldur.
        for (int i = 0; i < maxSize; i++) {
            String[] row = new String[3];

            // "mevsim_meyve" kategorisi verisini ekle.
            row[0] = getItemFromCategory(result, "mevsim_meyve", i);

            // "mevsim_sebze" kategorisi verisini ekle.
            row[1] = getItemFromCategory(result, "mevsim_sebze", i);

            // "her_zaman_sebze" kategorisi verisini ekle.
            row[2] = getItemFromCategory(result, "her_zaman_sebze", i);

            data.add(row);
        }

        // ArrayTableModel kullanarak tabloyu oluştur.
        ArrayTableModel tableModel = new ArrayTableModel(data.toArray(Object[][]::new));
        TableBuilder tableBuilder = new TableBuilder(tableModel);

        // Tabloyu formatla ve döndür.
        tableBuilder.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
        return tableBuilder.build().render(1000); // Render işlemi ile tabloyu 1000 karakter genişliğinde döndür
    }

    // Kategorideki öğe sayısını döndüren yardımcı metod
    private int getCategorySize(Map<String, List<String>> result, String category) {
        List<String> items = result.get(category);
        return (items != null) ? items.size() : 0;
    }

    // Verilen kategori ve sıradaki öğeyi döndüren yardımcı metod
    private String getItemFromCategory(Map<String, List<String>> result, String category, int index) {
        List<String> items = result.get(category);
        if (items != null && index < items.size()) {
            return items.get(index);
        }
        return ""; // Eğer kategori boşsa, boş bir string döndür.
    }
}