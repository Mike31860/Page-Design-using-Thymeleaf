package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.TsscAdmin;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.GameRepository;

@Service
public class AdminServiceImpt implements AdminService {

	@Autowired
	private AdminRepository admin;


	@Autowired
	public AdminServiceImpt(AdminRepository admin) {

		this.admin = admin;
	
	}
	
	
	@Override
	public TsscAdmin agregar(TsscAdmin nuevo) {
		
		admin.save(nuevo);
		return nuevo;
	}

	@Override
	public void eliminar(TsscAdmin eliminar) {
		admin.delete(eliminar);
		
	}

	@Override
	public TsscAdmin editar(TsscAdmin editar) {
		
		return admin.save(editar);
	}
	
	
	
	

}
