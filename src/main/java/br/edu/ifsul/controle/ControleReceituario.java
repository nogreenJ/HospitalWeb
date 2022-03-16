package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.modelo.Receituario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author João
 */
@Named(value = "controleReceituario")
@ViewScoped
public class ControleReceituario implements Serializable{
    
    @EJB
    private ReceituarioDAO<Receituario> dao;
    private Receituario objeto;
    @EJB
    private ConsultaDAO<Consulta> consulta;
    private Medicamento medicamento;
    private Boolean novoMedicamento;
    
    public ControleReceituario(){
    
    }
    
    public void novoMedicamento(){
        medicamento = new Medicamento();
        novoMedicamento = true;
    }
    
    public void alterarMedicamento(int index){
        List<Medicamento> list = new ArrayList<>(objeto.getMedicamentos());
        medicamento = list.get(index);
        novoMedicamento = false;
    }
    
    public void salvarMedicamento(){
        if(novoMedicamento){
            objeto.adicionarMedicamento(medicamento);
        }
        Util.mensagemInformacao("Medicamento adicionado ou alterado com sucesso!");
    }
    
    public void removerMedicamento(int index){
        objeto.removerMedicamento(index);
        Util.mensagemInformacao("Medicamento removido com sucesso!");
    }
    
    public String listar(){
        return "/privado/receituario/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Receituario();
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

    public ReceituarioDAO<Receituario> getDao() {
        return dao;
    }

    public void setDao(ReceituarioDAO<Receituario> dao) {
        this.dao = dao;
    }

    public Receituario getObjeto() {
        return objeto;
    }

    public void setObjeto(Receituario objeto) {
        this.objeto = objeto;
    }

    public ConsultaDAO<Consulta> getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDAO<Consulta> consulta) {
        this.consulta = consulta;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Boolean getNovoMedicamento() {
        return novoMedicamento;
    }

    public void setNovoMedicamento(Boolean novoMedicamento) {
        this.novoMedicamento = novoMedicamento;
    }
    
    
}
