package com.example.example

import com.google.gson.annotations.SerializedName


data class ArtResponse (

  @SerializedName("preference" ) var preference : String?         = null,
  @SerializedName("pagination" ) var pagination : Pagination?     = Pagination(),
  @SerializedName("data"       ) var artworkObject       : List<ArtworkObject> = listOf<ArtworkObject>(),
  @SerializedName("info"       ) var info       : Info?           = Info(),
  @SerializedName("config"     ) var config     : Config?         = Config()

)