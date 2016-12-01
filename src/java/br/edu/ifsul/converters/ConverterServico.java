/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bruno
 */
@FacesConverter(value = "converterServico")
public class ConverterServico implements Serializable, Converter {

  @PersistenceContext(unitName = "TA-2016-2-6N1-Final-WebPU")
  private EntityManager em;
  
  @Override
  public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
    if (string == null || string.equals("Selecione um registro")) {
      return null;
    }
    return em.find(Servico.class, Integer.parseInt(string));
  }

  @Override
  public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    if (o == null) {
      return null;
    }
    Servico obj = (Servico) o;
    return obj.getId().toString();
  }
  
}
