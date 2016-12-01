/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.DAOConta;
import br.edu.ifsul.dao.DAOServico;
import br.edu.ifsul.modelo.Conta;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Bruno
 */
@Named(value = "controleServico")
@SessionScoped
public class ControleServico implements Serializable {
  
  @EJB
  private DAOServico<Servico> dao;
  @EJB
  private DAOConta<Conta> daoConta;
  private Servico objeto;
  private Boolean editando;

  public ControleServico() {
    editando = false;
  }
  
  public String listar () {
    editando = false;
    return "/privado/servico/listar?faces-redirect=true";
  }
  
  public void novo () {
    editando = true;
    objeto = new Servico ();
  }
  
  public void alterar (Integer id) {
    try {
      objeto = dao.getObjectById(id);
      editando = true;
    } catch (Exception e) {
      Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
    }
  }
  
  public void remover (Integer id) {
    try {
      objeto = dao.getObjectById(id);
      dao.remove(objeto);
      Util.mensagemInformacao("Objeto removido com sucesso");
    } catch (Exception e) {
      Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
    }
  }
  
  public void salvar () {
    try {
      if (objeto.getId() == null) {
        dao.persist(objeto);
      }
      else {
        dao.merge(objeto);
      }
      editando = false;
      Util.mensagemInformacao("Objeto persistido com sucesso");
    } catch (Exception e) {
      Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
    }
  }
  
  public DAOServico<Servico> getDao() {
    return dao;
  }

  public void setDao(DAOServico dao) {
    this.dao = dao;
  }
  
  public DAOConta<Conta> getDaoConta() {
    return daoConta;
  }

  public void setDaoConta(DAOConta daoConta) {
    this.daoConta = daoConta;
  }

  public Servico getObjeto() {
    return objeto;
  }

  public void setObjeto(Servico objeto) {
    this.objeto = objeto;
  }

  public Boolean getEditando() {
    return editando;
  }

  public void setEditando(Boolean editando) {
    this.editando = editando;
  }
  
}
