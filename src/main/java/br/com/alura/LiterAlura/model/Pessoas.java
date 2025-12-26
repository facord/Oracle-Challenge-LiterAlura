package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoaId;
    @Column(nullable = false, unique = true)
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @OneToMany(mappedBy = "pessoa")
    private List<PessoaLivro> participacoes;
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
    private List<PessoaLivro> livros = new ArrayList<>();

    public List<PessoaLivro> getLivros() {
        return livros;
    }

    public void setLivros(List<PessoaLivro> livros) {
        this.livros = livros;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<PessoaLivro> getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(List<PessoaLivro> participacoes) {
        this.participacoes = participacoes;
    }

    @Override
    public String toString() {
        return "Pessoas{" +
                "pessoaId=" + pessoaId +
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                ", participacoes=" + participacoes +
                ", livros=" + livros +
                '}';
    }

}