package com.Proyecto.PPOOII.Interfaces;

import java.util.List;
import org.springframework.data.domain.Pageable;


import com.Proyecto.PPOOII.Entities.Usuario;
import com.Proyecto.PPOOII.Entities.UsuarioPK;

public interface IUsuarioService {

    //METODOS CRUD
	boolean guardar(Usuario usuario);
	
	boolean actualizar(Usuario usuario);
	
	boolean eliminar(UsuarioPK id);
	
	List<Usuario> consultarUsuario(Pageable pageable);
	
	Usuario getUsuarioById(UsuarioPK id);
}
