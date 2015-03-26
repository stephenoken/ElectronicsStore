package com.store

class Category {
	String cName
	static hasMany=[items:StockItem]
    static constraints = {
    }
}
