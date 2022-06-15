
package br.com.carros.entidade;

import br.com.carros.exception.ErroSistema;
import java.util.List;

public interface CrudDAO<E> {//E representa Entidade //generics não obrigatorio no implements
    
    public void salvar(E entidade) throws ErroSistema;
    
    public void deletar(E entidade) throws ErroSistema;
    
    public List<E> buscar() throws ErroSistema;
    
}
