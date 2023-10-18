package central.vehiculos.Entidad;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Vehiculo implements Serializable {
    @Id
    private String placa;
    private int modelo;
    private long precio;
    private String Color;



    @Transient
    private Long ValorSeguro;


    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public int getModelo() {
        return modelo;
    }
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }
    public long getPrecio() {
        return precio;
    }
    public void setPrecio(long precio) {
        this.precio = precio;
    }
    public Long getValorSeguro() {
        return ValorSeguro;
    }
    public String getColor() {return Color;}
    public void setColor(String Color){this.Color=Color;}


    public Vehiculo(String placa, int modelo, long precio, String Color) {
        this.placa = placa;
        this.modelo = modelo;
        this.precio = precio;
        this.Color = Color;
    }


    public Vehiculo(String placa, int modelo, long precio, Long valorSeguro, String Color) {
        this.placa = placa;
        this.modelo = modelo;
        this.precio = precio;
        ValorSeguro = valorSeguro;
    }
    public Long consultarSeguro() {
        int antiguedad = 2023 - this.modelo;
        double porcentajeAntiguedad = antiguedad * 0.002;
        this.ValorSeguro = (long) (this.precio * (0.1 + porcentajeAntiguedad));
        return this.ValorSeguro;
    }
    public Vehiculo() {
    }
   
}