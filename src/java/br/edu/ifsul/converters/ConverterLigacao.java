/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Ligacao;
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
@FacesConverter(value = "converterLigacao")
public class ConverterLigacao implements Serializable, Converter {

  @PersistenceContext(unitName = "TA-2016-2-6N1-Final-WebPU")
  private EntityManager em;
  
  @Override
  public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
    if (string == null || string.equals("Selecione um registro")) {
      return null;
    }
    return em.find(Ligacao.class, Integer.parseInt(string));
  }

  @Override
  public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    if (o == null) {
      return null;
    }
    Ligacao obj = (Ligacao) o;
    return obj.getId().toString();
  }
  
}
