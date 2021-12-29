/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosProduciones.modelo.logica;

/**
 *
 * @author Bruno
 */
public class FabricaLogica {
    
     public static IlogicaEquipos getLogicaEquipos() {
        return LogicaEquipos.getInstancia();
    }
     
     public static IlogicaPersonal getLogicaPersonal() {
        return LogicaPersonal.getInstancia();
    }
     
     public static IlogicaSpot getLogicaSpot() {
        return LogicaSpot.getInstancia();
    }
     
     public static IlogicaUsuario getLogicaUsuario() {
        return LogicaUsuario.getInstancia();
    }
     
     
    
}
