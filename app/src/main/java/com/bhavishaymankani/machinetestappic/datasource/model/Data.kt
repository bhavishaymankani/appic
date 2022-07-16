package com.bhavishaymankani.machinetestappic.datasource.model

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("filterData")
	val filterData: List<FilterDataItem?>? = null,

	@field:SerializedName("errorCode")
	val errorCode: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class BrandNameListItem(

	@field:SerializedName("locationNameList")
	val locationNameList: List<LocationNameListItem?>? = null,

	@field:SerializedName("brandName")
	val brandName: String? = null
)

data class MerchantNumberItem(

	@field:SerializedName("mid")
	val mid: String? = null,

	@field:SerializedName("outletNumber")
	val outletNumber: List<String?>? = null
)

data class HierarchyItem(

	@field:SerializedName("brandNameList")
	val brandNameList: List<BrandNameListItem?>? = null,

	@field:SerializedName("accountNumber")
	val accountNumber: String? = null
)

data class LocationNameListItem(

	@field:SerializedName("merchantNumber")
	val merchantNumber: List<MerchantNumberItem?>? = null,

	@field:SerializedName("locationName")
	val locationName: String? = null
)

data class FilterDataItem(

	@field:SerializedName("Cif")
	val cif: String? = null,

	@field:SerializedName("companyName")
	val companyName: String? = null,

	@field:SerializedName("hierarchy")
	val hierarchy: List<HierarchyItem?>? = null
)
