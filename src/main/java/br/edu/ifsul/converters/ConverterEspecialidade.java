package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author João
 */
@Named(value = "converterEspecialidade")
@RequestScoped
public class ConverterEspecialidade implements Serializable, Converter {

    @PersistenceContext(unitName = "HospitalWebPU")
    protected EntityManager em;    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Especialidade.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Especialidade obj = (Especialidade) t;
        return obj.getId().toString();
    }

}