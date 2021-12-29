/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.compartidos.beans.datatypes;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Bruno
 */
public class DTperActores extends DTPersonal implements Serializable {
    
    private Date fechaNacimiento;
    private String sexo;
    private String foto;

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public DTperActores(Date fechaNacimiento, String sexo, String foto, int cedula, String nombre) {
        super(cedula, nombre);
       setFechaNacimiento(fechaNacimiento);
       setSexo(sexo);
       setFoto(foto);
    }

    public DTperActores() {
    }

    @Override
    public String toString() {
        return "Actor";
    }

    
    
    
}
