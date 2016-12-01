/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.DAOConta;
import br.edu.ifsul.dao.DAOLigacao;
import br.edu.ifsul.dao.DAOServico;
import br.edu.ifsul.dao.DAOUsuario;
import br.edu.ifsul.modelo.Conta;
import br.edu.ifsul.modelo.Ligacao;
import br.edu.ifsul.modelo.Servico;
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
@Named(value = "controleConta")
@SessionScoped
public class ControleConta implements Serializable {

  @EJB
  private DAOConta<Conta> dao;
  private Conta objeto;
  private Boolean editando;
  @EJB
  private DAOUsuario<Usuario> daoUsuario;
  private Usuario usuario;
  private Boolean editandoUsuario;
  @EJB
  private DAOServico<Servico> daoServico;
  private Servico servico;
  private Boolean editandoServico;
  private Boolean novoServico;
  @EJB
  private DAOLigacao<Ligacao> daoLigacao;
  private Ligacao ligacao;
  private Boolean editandoLigacao;
  private Boolean novaLigacao;

  public String listar () {
    editando = false;
    return "/privado/conta/listar?faces-redirect=true";
  }
  
  public void novo () {
    editando = true;
    objeto = new Conta ();
    editandoUsuario = false;
    editandoServico = false;
    editandoLigacao = false;
  }
  
  public void alterar (Integer id) {
    try {
      objeto = dao.getObjectById(id);
      editando = true;
      editandoUsuario = false;
      editandoServico = false;
      editandoLigacao = false;
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
      editandoUsuario = false;
      editandoServico = false;
      editandoLigacao = false;
      Util.mensagemInformacao("Objeto persistido com sucesso");
    } catch (Exception e) {
      Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
    }
  }

  // Usuário
  public void novoUsuario () {
    editandoUsuario = true;
  }
  
  public void salvarUsuario () {
    if (!objeto.getUsuarios().contains(usuario)) {
      objeto.getUsuarios().add(usuario);
      editandoUsuario = false;
      Util.mensagemInformacao("Usuário adicionado com sucesso");      
    }
    else {
      Util.mensagemErro("Conta já possui esse usuário");
    }
  }
  
  public void excluriUsuario (Usuario obj) {
    objeto.getUsuarios().remove(obj);
    Util.mensagemInformacao("Usuário removido");
  }
  
  // Serviço
  public void novoServico() {
    servico = new Servico();
    editandoServico = true;
    novoServico = true;
  }

  public void salvarServico() {
    if (servico.getId() == null || servico.getId() == 0) {
        if (novoServico){
          objeto.adicionarServico(servico);
        }
    }
    else {
      System.out.println("ID do Serviço não é igual a null " + servico.getId());
    }
    editandoServico = false;
    Util.mensagemInformacao("Serviço persistido com sucesso!");
  }

  public void alterarServico(int index) {
      servico = objeto.getServicos().get(index);
      editandoServico = true;
      novoServico = false;
  }

  public void excluirServico(int index) {
      objeto.removerServico(index);
      Util.mensagemInformacao("Serviço removido com sucesso!");
  }
  
  // Ligação
  public void novaLigacao() {
    ligacao = new Ligacao();
    editandoLigacao = true;
    novaLigacao = true;
  }

  public void salvarLigacao() {
    if (ligacao.getId() == null || ligacao.getId() == 0) {
        if (novaLigacao){
          objeto.adicionarLigacao(ligacao);
        }
    }
    else {
      System.out.println("ID da Ligação não é igual a null " + ligacao.getId());
    }
    editandoLigacao = false;
    Util.mensagemInformacao("Serviço persistido com sucesso!");
  }

  public void alterarLigacao(int index) {
      ligacao = objeto.getLigacoes().get(index);
      editandoLigacao = true;
      novaLigacao = false;
  }

  public void excluirLigacao(int index) {
      objeto.removerLigacao(index);
      Util.mensagemInformacao("Serviço removido com sucesso!");
  }
  
  public DAOConta<Conta> getDao() {
    return dao;
  }

  public void setDao(DAOConta<Conta> dao) {
    this.dao = dao;
  }

  public Conta getObjeto() {
    return objeto;
  }

  public void setObjeto(Conta objeto) {
    this.objeto = objeto;
  }

  public Boolean getEditando() {
    return editando;
  }

  public void setEditando(Boolean editando) {
    this.editando = editando;
  }

  public DAOUsuario<Usuario> getDaoUsuario() {
    return daoUsuario;
  }

  public void setDaoUsuario(DAOUsuario<Usuario> daoUsuario) {
    this.daoUsuario = daoUsuario;
  }

  public DAOServico<Servico> getDaoServico() {
    return daoServico;
  }

  public void setDaoServico(DAOServico<Servico> daoServico) {
    this.daoServico = daoServico;
  }

  public DAOLigacao<Ligacao> getDaoLigacao() {
    return daoLigacao;
  }

  public void setDaoLigacao(DAOLigacao<Ligacao> daoLigacao) {
    this.daoLigacao = daoLigacao;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Boolean getEditandoUsuario() {
    return editandoUsuario;
  }

  public void setEditandoUsuario(Boolean editandoUsuario) {
    this.editandoUsuario = editandoUsuario;
  }

  public Servico getServico() {
    return servico;
  }

  public void setServico(Servico servico) {
    this.servico = servico;
  }

  public Boolean getEditandoServico() {
    return editandoServico;
  }

  public void setEditandoServico(Boolean editandoServico) {
    this.editandoServico = editandoServico;
  }

  public Ligacao getLigacao() {
    return ligacao;
  }

  public void setLigacao(Ligacao ligacao) {
    this.ligacao = ligacao;
  }

  public Boolean getEditandoLigacao() {
    return editandoLigacao;
  }

  public void setEditandoLigacao(Boolean editandoLigacao) {
    this.editandoLigacao = editandoLigacao;
  }

  public Boolean getNovoServico() {
    return novoServico;
  }

  public void setNovoServico(Boolean novoServico) {
    this.novoServico = novoServico;
  }

  public Boolean getNovaLigacao() {
    return novaLigacao;
  }

  public void setNovaLigacao(Boolean novaLigacao) {
    this.novaLigacao = novaLigacao;
  }
  
}
