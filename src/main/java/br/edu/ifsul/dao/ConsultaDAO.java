/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Consulta;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author João
 */
@Stateful
public class ConsultaDAO<TIPO> extends DAOGenerico<Consulta> implements Serializable  {
    
    public ConsultaDAO(){
        super();
        classePersistente = Consulta.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("preConsulta", "Pré-Consulta", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem); 
    }
    
    @Override
    public Consulta localizar(Object id) throws Exception{
        Consulta obj = em.find(Consulta.class, id);
        obj.getExames().size();
        obj.getReceituarios().size();
        return obj;
    }
}
