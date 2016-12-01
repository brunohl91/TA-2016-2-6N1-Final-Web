/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.DAOConta;
import br.edu.ifsul.dao.DAOLigacao;
import br.edu.ifsul.modelo.Conta;
import br.edu.ifsul.modelo.Ligacao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Bruno
 */
@Named(value = "controleLigacao")
@SessionScoped
public class ControleLigacao implements Serializable {
  
  @EJB
  private DAOLigacao<Ligacao> dao;
  @EJB
  private DAOConta<Conta> daoConta;
  private Ligacao objeto;
  private Boolean editando;

  public ControleLigacao() {
    editando = false;
  }
  
  public String listar () {
    editando = false;
    return "/privado/ligacao/listar?faces-redirect=true";
  }
  
  public void novo () {
    editando = true;
    objeto = new Ligacao ();
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
      
      Util.mensagemInformacao("Objeto removido com sucesso: "+id);
    } catch (Exception e) {
      Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
      e.printStackTrace();
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
  
  public DAOLigacao<Ligacao> getDao() {
    return dao;
  }

  public void setDao(DAOLigacao dao) {
    this.dao = dao;
  }
  
  public DAOConta<Conta> getDaoConta() {
    return daoConta;
  }

  public void setDaoConta(DAOConta daoConta) {
    this.daoConta = daoConta;
  }

  public Ligacao getObjeto() {
    return objeto;
  }

  public void setObjeto(Ligacao objeto) {
    this.objeto = objeto;
  }

  public Boolean getEditando() {
    return editando;
  }

  public void setEditando(Boolean editando) {
    this.editando = editando;
  }
  
}
