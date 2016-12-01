/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Bruno
 */
@Stateful
public class DAOServico<T> extends DAOGenerico<Servico> implements Serializable {

  public DAOServico () {
    super();
    super.setClassePersistente(Servico.class);
  }
  
}
