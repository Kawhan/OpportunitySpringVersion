package br.ufpb.dcx.lab1v1.entity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Comentario extends Usuario  {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id_comentario;
    private String texto;
    private Boolean visivel;
    private ZonedDateTime timeStamp;

    public Comentario(String texto, String nome, String email, String senha) {
        super(nome, email, senha);
        this.id_comentario = count.incrementAndGet();
        this.texto = texto;
        this.visivel = true;
        this.timeStamp = ZonedDateTime.now();
    }

    public String getComentarioTexto() {
        return this.texto;
    }

    public Integer getComentarioId() {
        return this.id_comentario;
    }

    public void visivel() {
        this.visivel = true;
    }

    public void invisivel() {
        this.visivel = false;
    }

    public Boolean isVisivel() {
        Boolean visivel = this.visivel;
        return visivel;
    }


    public String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = timeStamp.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Comentario comentario = (Comentario) obj;
        return Objects.equals(texto, comentario.texto) &&
                Objects.equals(visivel, comentario.visivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_comentario);
    }
}