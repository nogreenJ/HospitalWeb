package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author João
 */
@Named(value = "controleExame")
@ViewScoped
public class ControleExame implements Serializable{
    
    @EJB
    private ExameDAO<Exame> dao;
    private Exame objeto;
    @EJB
    private ConsultaDAO<Consulta> consulta;
    
    public ControleExame(){
    
    }
    
    public String listar(){
        return "/privado/exame/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Exame();
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

    public ExameDAO<Exame> getDao() {
        return dao;
    }

    public void setDao(ExameDAO<Exame> dao) {
        this.dao = dao;
    }

    public Exame getObjeto() {
        return objeto;
    }

    public void setObjeto(Exame objeto) {
        this.objeto = objeto;
    }

    public ConsultaDAO<Consulta> getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDAO<Consulta> consulta) {
        this.consulta = consulta;
    }
}
