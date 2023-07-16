package br.ufsm.csi.model;

public class Socio {

    private int id;
    private String carteiraClube;
    private Usuario usuario;

    public Socio(int id, Usuario usuario, String carteiraClube) {
        this.setId(id);
        this.setUsuario(usuario);
        this.setCarteiraClube(carteiraClube);
    }

    public Socio (Usuario usuario){
        setUsuario(usuario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarteiraClube() {
        return carteiraClube;
    }

    public void setCarteiraClube(String carteiraClube) {
        this.carteiraClube = carteiraClube;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
