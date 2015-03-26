package com.store

import grails.plugin.springsecurity.annotation.Secured;

class TestController {

    def index() { }
	@Secured(["ROLE_ADMIN"])
	def adminTest(){
		render "Accessed by Admin"
	}
	@Secured(["ROLE_USER"])
	def userTest(){
		render " Accessed by User"
	}
}
