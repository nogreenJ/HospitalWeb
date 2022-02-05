/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
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
        //Definir as ordens possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        //Definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        //Inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}
