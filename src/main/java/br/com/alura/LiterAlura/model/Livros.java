package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long livroId;
    // ID vindo da API
    private Integer id;
    @Column(unique = true, columnDefinition = "TEXT")
    private String titulo;
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<PessoaLivro> pessoas = new ArrayList<>();
    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<String> resumos;
    @ElementCollection
    private List<String> assuntos;
    @ElementCollection
    private List<String> estantes;
    @ElementCollection
    private List<String> idiomas;
    @Column(columnDefinition = "TEXT")
    private Boolean copyright;
    @Column(columnDefinition = "TEXT")
    private String tipoMidia;
    @ElementCollection
    @CollectionTable(name = "livro_formatos", joinColumns = @JoinColumn(name = "livro_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "url")
    private Map<String, String> formatos;
    private Integer totalDownloads;

    public List<PessoaLivro> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaLivro> pessoas) {
        this.pessoas = pessoas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getResumos() {
        return resumos;
    }

    public void setResumos(List<String> resumos) {
        this.resumos = resumos;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<String> assuntos) {
        this.assuntos = assuntos;
    }

    public List<String> getEstantes() {
        return estantes;
    }

    public void setEstantes(List<String> estantes) {
        this.estantes = estantes;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }

    public Map<String, String> getFormatos() {
        return formatos;
    }

    public void setFormatos(Map<String, String> formatos) {
        this.formatos = formatos;
    }

    public Integer getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(Integer totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    @Override
    public String toString() {
        return "Livros{" +
                "livroId=" + livroId +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", pessoas=" + pessoas +
                ", resumos=" + resumos +
                ", assuntos=" + assuntos +
                ", estantes=" + estantes +
                ", idiomas=" + idiomas +
                ", copyright=" + copyright +
                ", tipoMidia='" + tipoMidia + '\'' +
                ", formatos=" + formatos +
                ", totalDownloads=" + totalDownloads +
                '}';
    }
}

