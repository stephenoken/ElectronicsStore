package com.store

class Manufacturer {
	String mName
	static hasMany = [items:StockItem]
    static constraints = {
    }
}
