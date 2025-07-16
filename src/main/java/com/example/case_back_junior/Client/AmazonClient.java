package com.example.case_back_junior.Client;

import com.example.case_back_junior.Model.Entity.Livro;
import com.example.case_back_junior.Service.LivroService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class AmazonClient {
    @Autowired
    private LivroService livroService;

    public Livro importarApiAmazon(String url) throws IOException, ParseException {
        Connection connection = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/115.0.0.0 Safari/537.36")
                .header("Accept-Language", "pt-BR,pt;q=0.9")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .timeout(10000)
                .followRedirects(true);

        Document doc = connection.get();

        String titulo = doc.getElementById("productTitle").text();
        String preco = doc.selectFirst(".a-size-base.a-color-price.a-color-price").text();
        String precoTexto = preco.replace("R$", "").trim();
        NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
        BigDecimal precoDecimal = new BigDecimal(format.parse(precoTexto).toString());
        String dataPublicacao = doc.selectFirst("#rpi-attribute-book_details-publication_date .rpi-attribute-value").text();
        String isbn10 = doc.selectFirst("#rpi-attribute-book_details-isbn10 .rpi-attribute-value").text();

    Livro livro = new Livro();
    livro.setTitulo(titulo);
    livro.setPreco(precoDecimal);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("pt", "BR"));
        LocalDate data = LocalDate.parse(dataPublicacao, formatter);
    livro.setAnoPublicacao(data.getYear());
    livro.setIsbn(isbn10);
    return livro;
    }

}