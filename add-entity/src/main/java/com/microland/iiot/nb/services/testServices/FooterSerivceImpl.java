package com.microland.iiot.nb.services.testServices;

import org.springframework.stereotype.Service;

@Service
public class FooterSerivceImpl implements FooterService{

	@Override
	public void insertFooter() throws Exception {
		System.out.println("FooterSerivceImpl.insertFooter()");
	}

	@Override
	public String getFooter() throws Exception {
		return "FooterSerivceImpl.getFooter()";
	}

	@Override
	public void deleteFooter() throws Exception {
		System.out.println("FooterSerivceImpl.deleteFooter()");
	}

}
