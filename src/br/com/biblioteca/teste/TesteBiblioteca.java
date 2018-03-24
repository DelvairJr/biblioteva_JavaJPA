package br.com.biblioteca.teste;

import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Resumo;
import java.util.ArrayList;
import java.util.List;

public class TesteBiblioteca {

    public static void main(String[] args) {

        Livro liv = new Livro();
        liv.setTitulo("Harry Potter e a Pedra Filosofal"); //add titlo ao livro

        Resumo res = new Resumo();
        res.setTexto("Resumo do livro salvo."); //criando um resumo
        liv.setResumo(res); //setando um resumo para o livro
        List<Livro> livros = new ArrayList(); //criando uma lista de livros
        livros.add(liv); //add um livro a lista

        Editora ed = new Editora();
        ed.setNome("Editora Rocco"); //criando uma nova editora

        liv.setEditora(ed); //setando uma editora ao livro

        LivroDAO livDao = new LivroDAO();
       // EditoraDAO edDao = new EditoraDAO();

        // edDao.create(ed); 
        livDao.create(liv);

        System.out.println("Livro: " + liv.getTitulo() + "\n"
                + "Resumo: " + res.getTexto() + "\n"
                + "Editora: " + ed.getNome());
    }

}
