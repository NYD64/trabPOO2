package br.ufsm.csi.model;

public class Atividade {

    private int id;
    private String nome;
    private String dia;
    private String hora;
    private int idSocio;

    public Atividade() {
    }

    public Atividade(String nome, String dia, String hora, int idSocio) {
        this.nome = nome;
        this.dia = dia;
        this.hora = hora;
        this.idSocio = idSocio;
    }

    public Atividade(int id, String nome, String dia, String hora, int idSocio) {
        this.id = id;
        this.nome = nome;
        this.dia = dia;
        this.hora = hora;
        this.idSocio = idSocio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
}
