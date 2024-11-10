package com.m1fonda.banque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1fonda.banque.service.BanqueService;

@RestController
@RequestMapping("/api")
public class BanqueController {

	@Autowired
	BanqueService banqueService;

//save user
//	@PostMapping("/addbanque")
//	public List<Validation> addUserController(@RequestBody Validation validation) {
//		banqueService.addBanques(Validation);
//		banqueService.getAllBanques();
//		return banqueService.getAllBanques();
//	}

////delete user
//	@RequestMapping("/deletebanque/{id}")
//	public List<Banque> deleteUserController(@PathVariable("id") int id) {
//		return banqueService.deleteBanques(id);
//	}
//
////get user
//	@GetMapping("/getbanque/{id}")
//	public Banque getUserController(@PathVariable("id") int id) {
//		return banqueService.getBanques(id);
//	}
//
////get alluser
//	@GetMapping("/getallbanque")
//	public List<Validation> getAllUserController() {
//		return banqueService.getAllBanques();
//	}

}
