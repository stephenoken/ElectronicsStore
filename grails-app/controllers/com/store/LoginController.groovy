package com.store

import grails.plugin.springsecurity.annotation.Secured;

class LoginController {

	@Secured("permitAll")
    def index() { 
		render view:"auth.gsp"
	}
	
	
}
