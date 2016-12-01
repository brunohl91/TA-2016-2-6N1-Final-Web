/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bruno
 */
public class DAOGenerico<T> implements Serializable {
  
  @PersistenceContext(unitName = "TA-2016-2-6N1-Final-WebPU")
  private EntityManager em;
  private List<T> listaObjetos;
  private List<T> listaTodos;
  private Class classePersistente;
  private String ordem = "id";
  private String filtro = "";
  private Integer maximoObjetos = 4;
  private Integer posicaoAtual = 0;
  private Integer totalObjetos = 0;

  public DAOGenerico () {
  }
  
  public void persist ( T obj ) throws Exception {
    em.persist(obj);
  }
  
  public void merge ( T obj ) throws Exception {
    em.merge(obj);
  }
  
  public void remove ( T obj ) throws Exception {
    obj = em.merge(obj);
    em.remove(obj);
  }
  
  public T getObjectById(Integer id) throws Exception {
    return (T) em.find(classePersistente, id);
  }
  
  public List<T> getListaObjetos() {
    String jpql = "from " + classePersistente.getSimpleName();
    String where = "";
    filtro = filtro.replaceAll("[';-]", "");
    if (filtro.length() > 0) {
      if (ordem.equals("id")) {
        try {
          Integer.parseInt(filtro);
          where += " where  " + ordem + " = '" + filtro + "' ";
        } catch (Exception e) {
        }
      }
      else {
        where += " where upper(" + ordem + ") like '%" + filtro.toUpperCase() + "%' ";
      }
    }
    jpql += where;
    jpql += " order by " + ordem;
    totalObjetos = em.createQuery("select id from " + classePersistente.getSimpleName() + where + " order by " + ordem).getResultList().size();
    return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
  }
  
  public void primeiro () {
    posicaoAtual = 0;
  }
  
  public void anterior () {
    posicaoAtual -= maximoObjetos;
    if (posicaoAtual < 0) {
      posicaoAtual = 0;
    }
  }
  
  public void proximo () {
    if (posicaoAtual + maximoObjetos < totalObjetos) {
      posicaoAtual += maximoObjetos;
    }
  }
  
  public void ultimo () {
    int resto = totalObjetos % maximoObjetos;
    if (resto > 0) {
      posicaoAtual = totalObjetos - resto;
    }
    else {
      posicaoAtual = totalObjetos - maximoObjetos;
    }
  }
  
  public String getMensagemNavegacao () {
    int ate = posicaoAtual + maximoObjetos;
    if (ate > totalObjetos) {
      ate = totalObjetos;
    }
    return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
  }

  public void setListaObjetos(List<T> listaObjetos) {
    this.listaObjetos = listaObjetos;
  }

  public List<T> getListaTodos() {
    String jpql = "from " + classePersistente.getSimpleName();
    return em.createQuery(jpql).getResultList();
  }

  public void setListaTodos(List<T> listaTodos) {
    this.listaTodos = listaTodos;
  }

  public EntityManager getEm() {
    return em;
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }

  public Class getClassePersistente() {
    return classePersistente;
  }

  public void setClassePersistente(Class classePersistente) {
    this.classePersistente = classePersistente;
  }

  public String getOrdem() {
    return ordem;
  }

  public void setOrdem(String ordem) {
    this.ordem = ordem;
  }

  public String getFiltro() {
    return filtro;
  }

  public void setFiltro(String filtro) {
    this.filtro = filtro;
  }

  public Integer getMaximoObjetos() {
    return maximoObjetos;
  }

  public void setMaximoObjetos(Integer maximoObjetos) {
    this.maximoObjetos = maximoObjetos;
  }

  public Integer getPosicaoAtual() {
    return posicaoAtual;
  }

  public void setPosicaoAtual(Integer posicaoAtual) {
    this.posicaoAtual = posicaoAtual;
  }

  public Integer getTotalObjetos() {
    return totalObjetos;
  }

  public void setTotalObjetos(Integer totalObjetos) {
    this.totalObjetos = totalObjetos;
  }
  
}