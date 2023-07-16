package br.ufsm.csi.model;

public class Instrutor {

    private int id;
    private String cracha;
    private Usuario usuario;

    public Instrutor(int id, Usuario usuario, String cracha) {
        this.setId(id);
        this.setUsuario(usuario);
        this.setCracha(cracha);
    }

    public Instrutor (Usuario usuario){
        setUsuario(usuario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
