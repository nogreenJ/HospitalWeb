/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author João
 */
@Named(value = "controleMedicamento")
@ViewScoped
public class ControleMedicamento implements Serializable{
    
    @EJB
    private MedicamentoDAO<Medicamento> dao;
    private Medicamento objeto;
    
    public ControleMedicamento(){}
    
    public String listar(){
        return "/privado/medicamento/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Medicamento();
    }
    
    public void alterar(Object id){
       try{
           objeto = dao.getObjectById(id);
       } catch(Exception e){
           Util.mensagemInformacao("Erro ao recuperar objeto" + Util.getMensagemErro(e));
       }
    }
    
    public void excluir(Object id){
       try{
           objeto = dao.getObjectById(id);
           dao.remove(objeto);
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

    public MedicamentoDAO<Medicamento> getDao() {
        return dao;
    }

    public void setDao(MedicamentoDAO<Medicamento> dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }
    
    
}
