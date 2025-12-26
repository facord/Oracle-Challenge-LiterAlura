package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_livro")
public class PessoaLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoalivroId;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livros livro;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoas pessoa;
    @Enumerated(EnumType.STRING)
    private PapelPessoa papel;

    public Long getPessoalivroId() {
        return pessoalivroId;
    }

    public void setPessoalivroId(Long pessoalivroId) {
        this.pessoalivroId = pessoalivroId;
    }

    public Livros getLivro() {
        return livro;
    }

    public void setLivro(Livros livro) {
        this.livro = livro;
    }

    public Pessoas getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }

    public PapelPessoa getPapel() {
        return papel;
    }

    public void setPapel(PapelPessoa papel) {
        this.papel = papel;
    }

    @Override
    public String toString() {
        return "PessoaLivro{" +
                "pessoalivroId=" + pessoalivroId +
                ", livro=" + livro +
                ", pessoa=" + pessoa +
                ", papel=" + papel +
                '}';
    }
}
