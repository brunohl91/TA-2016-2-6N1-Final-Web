/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Ligacao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bruno
 */
@Stateful
public class DAOLigacao<T> extends DAOGenerico<Ligacao> implements Serializable {

  public DAOLigacao () {
    super();
    super.setClassePersistente(Ligacao.class);
  }
  
}
