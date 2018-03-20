package br.com.biblioteca.teste;

import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.model.Editora;

public class TesteBiblioteca {

    public static void main(String[] args) {

        Editora ed = new Editora();

       
        ed.setNome("Editora Abril");

        EditoraDAO dao = new EditoraDAO();

        dao.save(ed);

        System.out.println("Editora salva: " + ed.getId() + "\n Nome: " + ed.getNome());

    }

}
