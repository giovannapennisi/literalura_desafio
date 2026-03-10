package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL) // Relacionamento com a nova tabela
    private Autor autor;

    private String idioma;
    private Double numeroDownloads;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.numeroDownloads = dadosLivro.numeroDownloads();
        if (dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty()) {
            this.idioma = dadosLivro.idiomas().get(0);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Double getNumeroDownloads() { return numeroDownloads; }
    public void setNumeroDownloads(Double numeroDownloads) { this.numeroDownloads = numeroDownloads; }

    @Override
    public String toString() {
        return "---------- LIVRO ----------" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getNome() : "Desconhecido") +
                "\nIdioma: " + idioma +
                "\nDownloads: " + numeroDownloads +
                "\n---------------------------";
    }
}