/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Medico;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author João
 */
@Stateful
public class MedicoDAO<TIPO> extends DAOGenerico<Medico> implements Serializable  {
    
    public MedicoDAO(){
        super();
        classePersistente = Medico.class;
        //Definir as ordens possiveis
        listaOrdem.add(new Ordem("crm", "CRM", "="));
        //Definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        //Inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}
