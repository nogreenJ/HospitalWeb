/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import java.io.Serializable;
import br.edu.ifsul.modelo.Especialidade;
import javax.ejb.Stateful;

/**
 *
 * @author João
 */
@Stateful
public class EspecialidadeDAO<TIPO> extends DAOGenerico<Especialidade> implements Serializable{
    
    public EspecialidadeDAO(){
        super();
        classePersistente = Especialidade.class;
    }
}
