/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Conta;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bruno
 */
@Stateful
public class DAOConta<T> extends DAOGenerico<Conta> implements Serializable {

  public DAOConta () {
    super();
    super.setClassePersistente(Conta.class);
  }
  
  @Override
  public Conta getObjectById(Integer id) throws Exception {
    Conta obj = (Conta) super.getEm().find(super.getClassePersistente(), id);
    obj.getLigacoes().size();
    obj.getServicos().size();
    obj.getUsuarios().size();
    return obj;
  }
  
}
