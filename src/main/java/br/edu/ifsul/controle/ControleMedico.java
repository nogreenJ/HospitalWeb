/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author João
 */
@Named(value = "controleMedico")
@ViewScoped
public class ControleMedico implements Serializable{
    
    @EJB
    private MedicoDAO<Medico> dao;
    private Medico objeto;
    @EJB
    private EspecialidadeDAO<Especialidade> especialidade;
    
    public ControleMedico(){
    
    }
    
    public void imprimeMedicos(){
       HashMap parametros = new HashMap();
       UtilRelatorios.imprimeRelatorio("relatoriosMedicos", parametros, dao.getListaTodos());
    }
    
    public void imprimeMedico(Object id){
       try{
           objeto = dao.localizar(id);
           List<Medico> lista = new ArrayList<>();
           lista.add(objeto);
           HashMap parametros = new HashMap();
           UtilRelatorios.imprimeRelatorio("relatorioMedico", parametros, lista);
       } catch(Exception e){
           Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
       }
    }
    
    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medico();
    }
    
    public void alterar(Object id){
       try{
           objeto = dao.localizar(id);
       } catch(Exception e){
           Util.mensagemInformacao("Erro ao recuperar objeto" + Util.getMensagemErro(e));
       }
    }
    
    public void excluir(Object id){
       try{
           objeto = dao.localizar(id);
           dao.remover(objeto);
           Util.mensagemInformacao("Objeto removido com sucesso");
       } catch(Exception e){
           Util.mensagemInformacao("Erro ao remover objeto" + Util.getMensagemErro(e));
       }
    }
    
    public void salvar(){
       try{
           if(objeto.getId() == null){
               dao.persist(objeto);
           } else {
               dao.merge(objeto);
           }
           Util.mensagemInformacao("Objeto persistido com sucesso");
       } catch(Exception e){
           Util.mensagemInformacao("Erro ao persistir objeto" + Util.getMensagemErro(e));
       }
    }

    public MedicoDAO<Medico> getDao() {
        return dao;
    }

    public void setDao(MedicoDAO<Medico> dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO<Especialidade> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeDAO<Especialidade> especialidade) {
        this.especialidade = especialidade;
    }
    
    
}
