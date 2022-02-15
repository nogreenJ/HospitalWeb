package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
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
    
    public ControleConsulta(){
    
    }
    
    public String listar(){
        return "/privado/consulta/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Consulta();
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
}
