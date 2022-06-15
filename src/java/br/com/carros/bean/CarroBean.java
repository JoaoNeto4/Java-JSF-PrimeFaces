
package br.com.carros.bean;

import br.com.carros.entidade.Carro;
import br.com.carros.entidade.CarroDAO;
import br.com.carros.entidade.CrudDAO;
import br.com.carros.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/*
A partir da versão 2.3 o @ManagedBean foi substituído por  @Named do pacote javax.inject.Named 
e o @SessionScope do pacote javax.enterprise.context.SessionScoped. 
Também é necessário importar a lib javax.enterprise.cdi-api, 
*/

@SessionScoped
@ManagedBean(name = "carroBean")
public class CarroBean extends CrudBEAN<Carro, CarroDAO>{

    private CarroDAO carroDAO;
    
    @Override
    public CarroDAO getDAO() {
        if(carroDAO == null){
            carroDAO = new CarroDAO();
        }
        return carroDAO;
    }

    @Override
    public Carro criarNovaEntidade() {
        return new Carro();
    }


    
    
    
    
    
    
    
    /*apos criar a classe CrudBean aula8
    private Carro carro = new Carro();
    private List<Carro> carros = new ArrayList<>();
    private CarroDAO dao = new CarroDAO();
    
    public void adicionar(){
        try {
            //carros.add(carro);
            dao.salvar(carro);
            carro = new Carro();
            adicionarMenssagem("Salvo!", "Carro salvo com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMenssagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void listar(){
        try {
            carros = dao.buscar();
            if(carros==null || carros.size()==0){
                adicionarMenssagem("Nenhum dado encontrado!", "Sua busca não retornou nenhum carro!", FacesMessage.SEVERITY_INFO);
            }
        } catch (ErroSistema ex) {
            adicionarMenssagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Carro c){
        carro = c;
        System.out.println("eitaaaa: "+c.getModelo());
    }
    
    public void deletar(Carro c){
        try {
            CarroDAO carro = new CarroDAO();
            carro.deletar(c.getId());
            adicionarMenssagem("Deletado!", "Carro deletado com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMenssagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void adicionarMenssagem(String sumario, String detalhe, FacesMessage.Severity ex){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMsg = new FacesMessage(ex, sumario, detalhe);
        context.addMessage(null, facesMsg);
    }
    
    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
    
    */
}
