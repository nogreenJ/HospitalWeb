/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author João
 */
@Stateful
public class PacienteDAO<TIPO> extends DAOGenerico<Paciente> implements Serializable  {
    
    public PacienteDAO(){
        super();
        classePersistente = Paciente.class;
    }
}
