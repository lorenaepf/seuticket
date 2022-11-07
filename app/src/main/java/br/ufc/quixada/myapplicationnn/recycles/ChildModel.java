package br.ufc.quixada.myapplicationnn.recycles;

public class ChildModel {
    private  int image;
    private String texto1;

    public ChildModel(int image, String texto1) {
        this.image = image;
        this.texto1 = texto1;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }
}