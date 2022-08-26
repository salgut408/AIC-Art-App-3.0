package com.salgut.android.aicartapp.models

import com.google.gson.annotations.SerializedName


data class Pagination (

  @SerializedName("total"        ) var total       : Int? = null,
  @SerializedName("limit"        ) var limit       : Int? = null,
  @SerializedName("offset"       ) var offset      : Int? = null,
  @SerializedName("total_pages"  ) var totalPages  : Int? = null,
  @SerializedName("current_page" ) var currentPage : Int? = null

)