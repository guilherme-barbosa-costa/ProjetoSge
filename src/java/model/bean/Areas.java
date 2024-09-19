
package model.bean;


public class Areas {
    
    private int id_area;
    private String nome;

    public Areas() {
    }

    public Areas(int id_area, String nome) {
        this.id_area = id_area;
        this.nome = nome;
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
        
}
