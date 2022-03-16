package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Receituario;
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
@Named(value = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable{
    
    @EJB
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    @EJB
    private MedicoDAO<Medico> medico;
    @EJB
    private PacienteDAO<Paciente> paciente;
    
    private Exame exame;
    private Boolean novoExame;
    private Receituario receituario;
    private Boolean novoReceituario;
    
    public ControleConsulta(){
    
    }
    
    public void imprimeConsultas(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosConsultas", parametros, dao.getListaObjetos());
    }
    
    public void imprimeConsulta(Object id){
        try {
            objeto = dao.localizar(id);
            List<Consulta> list = new ArrayList<>();
            list.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatoriosConsultas", parametros, list);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao imprimir" + Util.getMensagemErro(e));
        }
    }
    
    public void novoExame(){
        exame = new Exame();
        novoExame = true;
    }
    
    public void alterarExame(int index){
        exame = objeto.getExames().get(index);
        novoExame = false;
    }
    
    public void salvarExame(){
        if(novoExame){
            objeto.adicionarExame(exame);
        }
        Util.mensagemInformacao("Exame adicionado com sucesso");
    }
    
    public void removerExame(int index){
        objeto.removerExame(index);
        Util.mensagemInformacao("Exame removido com sucesso");
    }
    
    public void novoReceituario(){
        receituario = new Receituario();
        novoReceituario = true;
    }
    
    public void alterarReceituario(int index){
        receituario = objeto.getReceituarios().get(index);
        novoReceituario = false;
    }
    
    public void salvarReceituario(){
        if(novoReceituario){
            objeto.adicionarReceituario(receituario);
        }
        Util.mensagemInformacao("Receituário adicionado com sucesso");
    }
    
    public void removerReceituario(int index){
        objeto.removerReceituario(index);
        Util.mensagemInformacao("Receituário removido com sucesso");
    }
    
    public String listar(){
        return "/privado/consulta/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Consulta();
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

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public MedicoDAO<Medico> getMedico() {
        return medico;
    }

    public void setMedico(MedicoDAO<Medico> medico) {
        this.medico = medico;
    }

    public PacienteDAO<Paciente> getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDAO<Paciente> paciente) {
        this.paciente = paciente;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Boolean getNovoExame() {
        return novoExame;
    }

    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }

    public Receituario getReceituario() {
        return receituario;
    }

    public void setReceituario(Receituario receituario) {
        this.receituario = receituario;
    }

    public Boolean getNovoReceituario() {
        return novoReceituario;
    }

    public void setNovoReceituario(Boolean novoReceituario) {
        this.novoReceituario = novoReceituario;
    }
    
    

}
