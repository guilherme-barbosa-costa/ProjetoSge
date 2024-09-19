
package model.bean;

import java.sql.Date;


public class Professores {
    
    private int id_professor, id_area;
    private String nome, matricula, senha, cpf, imagem;
    private Date admissao;
    
    public Professores() {
    }

    public Professores(int id_professor, int id_area, String nome, String matricula, String senha, String cpf, String imagem, Date admissao) {
        this.id_professor = id_professor;
        this.id_area = id_area;
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        this.cpf = cpf;
        this.imagem = imagem;
        this.admissao = admissao;
    }
  
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }
    
    
}
