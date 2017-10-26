/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Configuracion {
    
    private String nombre;
    private String dificultad;
    private String modeloNave;
    private String modeloLuna;

    public Configuracion() {
    }
    
    

    public Configuracion(String nombre, String dificultad, String modeloNave, String modeloLuna) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.modeloNave = modeloNave;
        this.modeloLuna = modeloLuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getModeloNave() {
        return modeloNave;
    }

    public void setModeloNave(String modeloNave) {
        this.modeloNave = modeloNave;
    }

    public String getModeloLuna() {
        return modeloLuna;
    }

    public void setModeloLuna(String modeloLuna) {
        this.modeloLuna = modeloLuna;
    }
    
    
}
