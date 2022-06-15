
package br.com.carros.bean;

import br.com.carros.entidade.CrudDAO;
import br.com.carros.exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class CrudBEAN<E, D extends CrudDAO>{ //D serve de extensao do CrudDAO
 
    private String estadoTela = "buscar";//insere edita busca
    
    private E entidade;
    private List<E> entidades;
    
    public void novo(){
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }
    
    public void salvar(){
        try {
            getDAO().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMenssagem("Salvo comsucesso!", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBEAN.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMenssagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
                 
    public void editar(E entidade){
        this.entidade = entidade;
        mudarParaEdita();
    }
    
    public void deletar(E entidade){
        try {
            getDAO().deletar(entidade);
            entidades.remove(entidade);
            adicionarMenssagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBEAN.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMenssagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void buscar(){
        if(isBusca() == false){
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDAO().buscar();
            if(entidades == null || entidades.size() < 1){
                adicionarMenssagem("Não temos nada cadastrado", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBEAN.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMenssagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
        
    public void adicionarMenssagem(String menssagem, FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro, menssagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    
    
    
    
    
    
    //Responsavel por criar metodos nas classes bean
    public abstract D getDAO();
    public abstract E criarNovaEntidade();
    
    
    
    
    //Metodos para controle de tela
    public boolean isInsere(){
        return "inserir".equals(estadoTela);
    }
    
    public boolean isEdita(){
        return "editar".equals(estadoTela);
    }
    
    public boolean isBusca(){
        return "buscar".equals(estadoTela);
    }
    
    public void mudarParaInseri(){
        estadoTela="inserir";
    }
    
    public void mudarParaBusca(){
        estadoTela="buscar";
    }
    
    public void mudarParaEdita(){
        estadoTela="editar";
    }
}
