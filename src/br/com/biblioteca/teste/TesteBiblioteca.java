package br.com.biblioteca.teste;

import br.com.biblioteca.dao.EditoraDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Resumo;

public class TesteBiblioteca {

    public static void main(String[] args) {

//        Editora ed = new Editora();
//
//       
//        ed.setNome("Editora Abril");
//
//        EditoraDAO dao = new EditoraDAO();
//
//        dao.save(ed);
//
//        System.out.println("Editora salva: " + ed.getId() + "\n Nome: " + ed.getNome());
        
        Livro liv = new Livro();
        liv.setTitulo("Game of Thrones");
        
        Resumo res = new Resumo();
        res.setTexto("Resumo do jogo dos tronos.");
        liv.setResumo(res);
        
        LivroDAO livDao = new LivroDAO();
        livDao.create(liv);
        
        System.out.println("Salvo o Livro: " + liv.getTitulo() + " - " + liv.getResumo());

    }

}
