package br.com.dao.implementacao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementachion.crud.ImplementacaoCrud;
import br.com.respository.interfaces.RepositoryLogin;

@Repository
public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {
	
	private static final long serialVersionUID = 1L;


	@Override
	public boolean autentico(String login, String senha) throws Exception {
		String sql = "SELECT count(1) as autentica FROM entidade where ent_login  = ? and ent_senha = ? ";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql,new Object[]{login, senha});
		return sqlRowSet.next() ? sqlRowSet.getInt("autentica") > 0 : false;
	}

}
