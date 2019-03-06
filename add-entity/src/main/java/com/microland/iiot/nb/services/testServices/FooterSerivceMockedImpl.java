package com.microland.iiot.nb.services.testServices;

import org.springframework.stereotype.Service;

@Service
public class FooterSerivceMockedImpl implements FooterServiceMocked{

	@Override
	public void insertFooter() throws Exception {
		System.out.println("FooterSerivceImpl.insertFooter()");
	}

	@Override
	public String getFooter() throws Exception {
		System.out.println("FooterSerivceImpl.getFooter()");
		return "String";
	}

	@Override
	public void deleteFooter() throws Exception {
		System.out.println("FooterSerivceImpl.deleteFooter()");
	}

}
