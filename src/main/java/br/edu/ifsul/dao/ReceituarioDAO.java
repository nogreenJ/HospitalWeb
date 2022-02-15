/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Receituario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author João
 */
@Stateful
public class ReceituarioDAO<TIPO> extends DAOGenerico<Receituario> implements Serializable  {
    
    public ReceituarioDAO(){
        super();
        classePersistente = Receituario.class;
        //Definir as ordens possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("posologia", "Posologia", "like"));
        //Definir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        //Inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    public Receituario getObjectByID(Object id) throws Exception {
        Receituario obj = em.find(Receituario.class, id);
        //Uso para evitar lazyinitializationexception
        obj.getMedicamentos().size();
        return obj;
    }
}
