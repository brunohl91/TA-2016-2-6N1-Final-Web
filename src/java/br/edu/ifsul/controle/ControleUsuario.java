/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.DAOUsuario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Bruno
 */
@Named(value = "controleUsuario")
@SessionScoped
public class ControleUsuario implements Serializable {

  @EJB
  private DAOUsuario<Usuario> dao;
  private Usuario objeto;
  private Boolean editando;

  public String listar () {
    editando = false;
    return "/privado/usuario/listar?faces-redirect=true";
  }
  
  public void novo () {
    editando = true;
    objeto = new Usuario ();
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
  
  public DAOUsuario<Usuario> getDao() {
    return dao;
  }

  public void setDao(DAOUsuario<Usuario> daoUsuario) {
    this.dao = daoUsuario;
  }

  public Usuario getObjeto() {
    return objeto;
  }

  public void setObjeto(Usuario objeto) {
    this.objeto = objeto;
  }

  public Boolean getEditando() {
    return editando;
  }

  public void setEditando(Boolean editando) {
    this.editando = editando;
  }
  
}
