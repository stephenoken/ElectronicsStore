package com.store

class StockItem {
	String title
	byte[] filePayload
	static belongsTo = [category:Category, manufacturer:Manufacturer]
    static constraints = {
		filePayload(nullable:true, maxSize:100000000)
    }
}
